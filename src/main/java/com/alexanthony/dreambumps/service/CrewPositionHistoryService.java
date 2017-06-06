package com.alexanthony.dreambumps.service;

import com.alexanthony.dreambumps.config.Constants;
import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.domain.CrewPositionHistory;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import com.alexanthony.dreambumps.repository.CrewPositionHistoryRepository;
import com.alexanthony.dreambumps.repository.CrewRepository;
import com.alexanthony.dreambumps.service.dto.CrewPositionHistoryDTO;
import com.alexanthony.dreambumps.service.mapper.CrewPositionHistoryMapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing CrewPositionHistory.
 */
@Service
@Transactional
public class CrewPositionHistoryService {

  private final Logger log = LoggerFactory.getLogger(CrewPositionHistoryService.class);

  private final CrewPositionHistoryRepository crewPositionHistoryRepository;

  private final CrewPositionHistoryMapper crewPositionHistoryMapper;
  private final CrewRepository crewRepository;
  private final UserCrewRacingHistoryService userCrewRacingHistoryService;
  private final CrewPriceHistoryService crewPriceHistoryService;

  public CrewPositionHistoryService(
    CrewPositionHistoryRepository crewPositionHistoryRepository,
    CrewPositionHistoryMapper crewPositionHistoryMapper,
    CrewRepository crewRepository,
    UserCrewRacingHistoryService userCrewRacingHistoryService,
    CrewPriceHistoryService crewPriceHistoryService) {
    this.crewPositionHistoryRepository = crewPositionHistoryRepository;
    this.crewPositionHistoryMapper = crewPositionHistoryMapper;
    this.crewRepository = crewRepository;
    this.userCrewRacingHistoryService = userCrewRacingHistoryService;
    this.crewPriceHistoryService = crewPriceHistoryService;
  }

  /**
   * Save a crewPositionHistory.
   *
   * @param crewPositionHistory
   *          the entity to save
   * @return the persisted entity
   */
  public CrewPositionHistory save(CrewPositionHistory crewPositionHistory) {
    log.debug("Request to save CrewPositionHistory : {}", crewPositionHistory);
    CrewPositionHistory result = crewPositionHistoryRepository.save(crewPositionHistory);
    return result;
  }

  public CrewPositionHistoryDTO save(CrewPositionHistoryDTO crewPositionHistoryDTO) {
    CrewPositionHistory crewPositionHistory = save(crewPositionHistoryMapper.crewPositionHistoryDTOToCrewPositionHistory(crewPositionHistoryDTO));
    return crewPositionHistoryMapper.crewPositionHistoryToCrewPositionHistoryDTO(crewPositionHistory);
  }

  /**
   * Get all the crewPositionHistories.
   *
   * @return the list of entities
   */
  @Transactional(readOnly = true)
  public List<CrewPositionHistoryDTO> findAll() {
    log.debug("Request to get all CrewPositionHistories");
    List<CrewPositionHistory> result = crewPositionHistoryRepository.findAll();

    return result.stream().map(crewPositionHistoryMapper::crewPositionHistoryToCrewPositionHistoryDTO).collect(Collectors.toList());
  }

  /**
   * Get one crewPositionHistory by id.
   *
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Transactional(readOnly = true)
  public CrewPositionHistoryDTO findOne(Long id) {
    log.debug("Request to get CrewPositionHistory : {}", id);
    CrewPositionHistory crewPositionHistory = crewPositionHistoryRepository.findOne(id);
    return crewPositionHistoryMapper.crewPositionHistoryToCrewPositionHistoryDTO(crewPositionHistory);
  }

  /**
   * Delete the crewPositionHistory by id.
   *
   * @param id
   *          the id of the entity
   */
  public void delete(Long id) {
    log.debug("Request to delete CrewPositionHistory : {}", id);
    crewPositionHistoryRepository.delete(id);
  }

  public List<CrewPositionHistoryDTO> findAllByCrew(Long crewId) {
    log.debug("Request to get CrewPositionHistory for : {}", crewId);
    return crewPositionHistoryRepository.findByCrewId(crewId).stream().map(crewPositionHistoryMapper::crewPositionHistoryToCrewPositionHistoryDTO).collect(Collectors.toList());
  }

  public List<CrewPositionHistoryDTO> saveFullBumpSet(List<CrewPositionHistoryDTO> crewPositionHistoryDTOs) {
    if (crewPositionHistoryDTOs.isEmpty()) {
      return crewPositionHistoryDTOs;
    }
    List<CrewPositionHistory> crewPositionHistories = crewPositionHistoryMapper.crewPositionHistoryDTOsToCrewPositionHistorys(crewPositionHistoryDTOs);
    crewPositionHistories.forEach(crewPositionHistory -> crewPositionHistory.setCrew(crewRepository.findOne(crewPositionHistory.getCrew().getId())));
    Sex firstSex = crewPositionHistories.get(0).getCrew().getSex();
    Integer firstDay = crewPositionHistories.get(0).getDay();

    if (crewPositionHistories.stream().anyMatch(a -> a.getCrew().getSex() != firstSex)) {
      // Error handling
      return null;
    }
    if (crewPositionHistories.stream().anyMatch(a -> a.getDay() != firstDay)) {
      // Error handling
      return null;
    }

    Integer numberOfCrews;
    if (firstSex == Sex.male) {
      numberOfCrews = Constants.MENS_CREWS;
    } else {
      numberOfCrews = Constants.WOMENS_CREWS;
    }

    if (false && crewPositionHistories.size() != numberOfCrews) {
      return null;
    }

    crewPositionHistories = crewPositionHistories.stream().map(this::setBumpsAndDividend).collect(Collectors.toList());

    crewPositionHistories = crewPositionHistoryRepository.save(crewPositionHistories);

    userCrewRacingHistoryService.updateCrewsForNewBumps(crewPositionHistories);

    return crewPositionHistoryMapper.crewPositionHistorysToCrewPositionHistoryDTOs(crewPositionHistories);
  }

  private CrewPositionHistory setBumpsAndDividend(CrewPositionHistory crewPositionHistory) {
    CrewPositionHistory lastPosition = crewPositionHistoryRepository.findFirstByCrewAndDay(crewPositionHistory.getCrew(), crewPositionHistory.getDay() - 1);
    Integer startPosition = lastPosition.getPosition();
    Integer endPosition = crewPositionHistory.getPosition();
    Integer bumps = startPosition - endPosition;
    crewPositionHistory.setBumps(bumps);

    if (startPosition == 1 && endPosition == 1 && crewPositionHistory.getDay() >= 4) {
      // headship rowover
      crewPositionHistory.setDividend(Constants.HEADSHIP_MULTIPLIER.multiply(calculateRowOverDividendForPosition(startPosition)));
    } else if (bumps == 0) {
      // rowover
      crewPositionHistory.setDividend(calculateRowOverDividendForPosition(startPosition));
    } else if (bumps < 0) {
      //bumped
      crewPositionHistory.setDividend(BigDecimal.ZERO);
    } else {
      // bump
      if (endPosition == 1 && crewPositionHistory.getDay() >= 4) {
        crewPositionHistory.setDividend(calculateBumpDividend(startPosition, bumps).add(Constants.HEADSHIP_MULTIPLIER.subtract(BigDecimal.ONE).multiply(calculateRowOverDividendForPosition(startPosition))));
      } else if (isPositionSandwichBoat(startPosition, crewPositionHistory.getCrew().getSex())) {
        crewPositionHistory.setDividend(calculateBumpDividend(startPosition, bumps).add(calculateRowOverDividendForPosition(startPosition)));
      } else {
        crewPositionHistory.setDividend(calculateBumpDividend(startPosition, bumps));
      }
    }
    return crewPositionHistory;
  }

  private boolean isPositionSandwichBoat(Integer startPosition, Sex sex) {
    if (startPosition.equals(Constants.getNumberOfCrewsForSex(sex))) {
      return false;
    }
    return startPosition % Constants.CREWS_PER_DIVISION == 1;
  }

  private BigDecimal calculateStartPriceForPosition(Integer position) {
    return Constants.THREE_HUNDRED.subtract(Constants.MULTIPLIER.multiply(new BigDecimal(Math.log(position))));
  }

  private BigDecimal calculateRowOverDividendForPosition(Integer position) {
    return Constants.ROWOVER_DIVIDEND.multiply(calculateStartPriceForPosition(position));
  }

  private BigDecimal calculateBumpDividend(Integer startPosition, Integer bumps) {
    return calculateRowOverDividendForPosition(startPosition).multiply(Constants.BUMP_MULTIPLIER).multiply(new BigDecimal(bumps));
  }

  public List<CrewPositionHistoryDTO> updateFullBumps(List<CrewPositionHistoryDTO> crewPositionHistoryDTOs) {
    if (crewPositionHistoryDTOs.isEmpty()) {
      return crewPositionHistoryDTOs;
    }
    // Check all have the same day
    Integer day = crewPositionHistoryDTOs.get(0).getDay();
    if (crewPositionHistoryDTOs.stream().filter(bump -> bump.getDay() != day).findFirst().isPresent()) {
      // throw an error
    }

    List<CrewPositionHistory> bumps = new ArrayList<>();
    for (CrewPositionHistoryDTO bumpDTO: crewPositionHistoryDTOs) {
      Crew crew = crewRepository.findOne(bumpDTO.getCrewId());
      CrewPositionHistory bump = crewPositionHistoryRepository.findFirstByCrewAndDay(crew, day);
      bump.setPosition(bumpDTO.getPosition());
      bump = setBumpsAndDividend(bump);
//      bump = crewPositionHistoryRepository.save(bump);
      bumps.add(bump);
    }
    bumps = crewPositionHistoryRepository.save(bumps);

    userCrewRacingHistoryService.updateCrewsForUpdatedBumps(bumps);

    return crewPositionHistoryMapper.crewPositionHistorysToCrewPositionHistoryDTOs(bumps);
  }

  public boolean getAllCrewsHaveBumps(Integer day) {
    // Assume db integrity has worked, so we're ok if we have the same number
    List<CrewPositionHistory> bumps = crewPositionHistoryRepository.findByDay(day);
    List<Crew> crews = crewRepository.findAll();
    return bumps.size() == crews.size();
  }

  public List<CrewPositionHistoryDTO> parseStartingPositionsFromAnu(String url) throws IOException {
    Document entriesPage = Jsoup.connect(url).get();
    Element bodyTableTbody = entriesPage.getElementsByTag("table").get(1).child(0);

    List<Crew> crews = crewRepository.findAll();
    List<CrewPositionHistory> positionHistories = new ArrayList<>();

    for (Element divisionTable: bodyTableTbody.getElementsByTag("table")) {
      Elements rows = divisionTable.getElementsByTag("tr");
      if (rows.size() > 0) {
        String divisionName = rows.get(0).text();
        System.out.println(divisionName);
        Sex sex;
        if (divisionName.startsWith("Men")) {
          sex = Sex.male;
        } else {
          sex = Sex.female;
        }

        String divNameWithoutTime = divisionName.split(" \\(")[0].trim();
        String[] divNameComponents = divNameWithoutTime.split(" ");
        String divNumber = divNameComponents[divNameComponents.length-1];
        int divNum = parseNumeral(divNumber);

        if (sex == Sex.male && divNum >= 6) {
          continue;
        }
        if (sex == Sex.female && divNum >= 5) {
          continue;
        }

        for (int i = 1; i < rows.size(); i++) {
          String crewName = rows.get(i).getElementsByTag("a").text();
          int overallPosition = (divNum - 1)* Constants.CREWS_PER_DIVISION+i;
          System.out.println(Integer.toString(i) +"("+Integer.toString(overallPosition)+")"+ ": " + crewName);
          CrewPositionHistory crewPositionHistory = new CrewPositionHistory();
          crewPositionHistory.day(0).dividend(BigDecimal.ZERO).bumps(0).position(overallPosition);
          Crew crew = findCrew(crewName, sex, crews);
          if (crew != null) {
            crewPositionHistory.setCrew(crew);
            positionHistories.add(crewPositionHistory);
            crew.setPrice(calculateStartPriceForPosition(overallPosition));
            crewRepository.save(crew);
            crewPriceHistoryService.createForCrew(crew);
          }
        }
      }
    }
    positionHistories = crewPositionHistoryRepository.save(positionHistories);
    return crewPositionHistoryMapper.crewPositionHistorysToCrewPositionHistoryDTOs(positionHistories);
  }

  // HAX - get rowing on crews in
  public List<CrewPositionHistoryDTO> uploadStatic() {

    List<Crew> crews = crewRepository.findAll();
    List<CrewPositionHistory> positionHistories = new ArrayList<>();
    addCrew("Christ Church III", Sex.male, 6, 1, crews, positionHistories);
    addCrew("St Anne's II", Sex.male, 6, 2, crews, positionHistories);
    addCrew("Somerville II", Sex.male, 6, 3, crews, positionHistories);
    addCrew("Wadham III", Sex.male, 6, 4, crews, positionHistories);
    addCrew("Keble III", Sex.male, 6, 5, crews, positionHistories);
    addCrew("Oriel III", Sex.male, 6, 6, crews, positionHistories);
    addCrew("St Hugh's II", Sex.male, 6, 7, crews, positionHistories);
    addCrew("New College III", Sex.male, 6, 8, crews, positionHistories);
    addCrew("Balliol III", Sex.male, 6, 9, crews, positionHistories);
    addCrew("Pembroke IV", Sex.male, 6, 10, crews, positionHistories);
    addCrew("Linacre II", Sex.male, 6, 11, crews, positionHistories);
    addCrew("St Antony's II", Sex.male, 6, 12, crews, positionHistories);
    addCrew("Jesus IV", Sex.male, 6, 13, crews, positionHistories);

    addCrew("Merton III", Sex.male, 7, 1, crews, positionHistories);
    addCrew("St Hilda's II", Sex.male, 7, 2, crews, positionHistories);
    addCrew("St Catherine's III", Sex.male, 7, 3, crews, positionHistories);
    addCrew("Balliol IV", Sex.male, 7, 4, crews, positionHistories);
    addCrew("Corpus Christi II", Sex.male, 7, 5, crews, positionHistories);
    addCrew("Keble IV", Sex.male, 7, 6, crews, positionHistories);
    addCrew("Lincoln III", Sex.male, 7, 7, crews, positionHistories);
    addCrew("Corpus Christi III", Sex.male, 7, 8, crews, positionHistories);
    addCrew("St Antony's III", Sex.male, 7, 9, crews, positionHistories);
    addCrew("Magdalen III", Sex.male, 7, 10, crews, positionHistories);
    addCrew("St Hilda's III", Sex.male, 7, 11, crews, positionHistories);
    addCrew("Oriel IV", Sex.male, 7, 12, crews, positionHistories);
    addCrew("Somerville III", Sex.male, 7, 13, crews, positionHistories);
    addCrew("Queen's III", Sex.male, 7, 14, crews, positionHistories);

    addCrew("St Hilda's II", Sex.female, 4, 12, crews, positionHistories);
    addCrew("Brasenose II", Sex.female, 4, 13, crews, positionHistories);

    addCrew("Jesus II", Sex.female, 5, 1, crews, positionHistories);
    addCrew("St Hugh's II", Sex.female, 5, 2, crews, positionHistories);
    addCrew("L.M.H. II", Sex.female, 5, 3, crews, positionHistories);
    addCrew("Wolfson III", Sex.female, 5, 4, crews, positionHistories);
    addCrew("Mansfield II", Sex.female, 5, 5, crews, positionHistories);
    addCrew("Keble II", Sex.female, 5, 6, crews, positionHistories);
    addCrew("Somerville II", Sex.female, 5, 7, crews, positionHistories);
    addCrew("Pembroke III", Sex.female, 5, 8, crews, positionHistories);
    addCrew("St Peter's II", Sex.female, 5, 9, crews, positionHistories);
    addCrew("Magdalen III", Sex.female, 5, 10, crews, positionHistories);
    addCrew("Oriel III", Sex.female, 5, 11, crews, positionHistories);
    addCrew("St Antony's II", Sex.female, 5, 12, crews, positionHistories);
    addCrew("Exeter II", Sex.female, 5, 13, crews, positionHistories);

    addCrew("Wadham III", Sex.female, 6, 1, crews, positionHistories);
    addCrew("Balliol III", Sex.female, 6, 2, crews, positionHistories);
    addCrew("St Anne's II", Sex.female, 6, 3, crews, positionHistories);
    addCrew("Pembroke IV", Sex.female, 6, 4, crews, positionHistories);
    addCrew("St Catherine's II", Sex.female, 6, 5, crews, positionHistories);
    addCrew("Corpus Christi II", Sex.female, 6, 6, crews, positionHistories);
    addCrew("L.M.H. III", Sex.female, 6, 7, crews, positionHistories);
    addCrew("Corpus Christi III", Sex.female, 6, 8, crews, positionHistories);
    addCrew("Keble III", Sex.female, 6, 9, crews, positionHistories);
    addCrew("Worcester III", Sex.female, 6, 10, crews, positionHistories);
    addCrew("Jesus III", Sex.female, 6, 11, crews, positionHistories);
    addCrew("Somerville III", Sex.female, 6, 12, crews, positionHistories);
    addCrew("S.E.H. II", Sex.female, 6, 13, crews, positionHistories);

    positionHistories = crewPositionHistoryRepository.save(positionHistories);
    return crewPositionHistoryMapper.crewPositionHistorysToCrewPositionHistoryDTOs(positionHistories);
  }

  private void addCrew(String crewName, Sex sex, int divNum, int position, List<Crew> crews, List<CrewPositionHistory> positionHistories) {
    int overallPosition = (divNum - 1)* Constants.CREWS_PER_DIVISION+position;
    CrewPositionHistory crewPositionHistory = new CrewPositionHistory();
    crewPositionHistory.day(0).dividend(BigDecimal.ZERO).bumps(0).position(overallPosition);
    Crew crew = findCrew(crewName, sex, crews);
    if (crew != null) {
      crewPositionHistory.setCrew(crew);
      positionHistories.add(crewPositionHistory);
      crew.setPrice(calculateStartPriceForPosition(overallPosition));
      crewRepository.save(crew);
      crewPriceHistoryService.createForCrew(crew);
    }
  }

  private Crew findCrew(String crewNameFromAnu, Sex sex, List<Crew> allCrews) {
    String[] crewNameComponents = crewNameFromAnu.split(" ");
    String crewNumberString = crewNameComponents[crewNameComponents.length-1];
    int crewNumber = parseNumeral(crewNumberString);
    if (crewNumber == 0) {
      crewNumber = 1;
    }

    for (Crew crew: allCrews) {
      if (crew.getSex() == sex && crew.getName().endsWith(Integer.toString(crewNumber))) {
        // Narrowed it down to correct sex and crew number, now "just" college...
        if (collegeMatch(crewNameFromAnu, crew.getName(), crewNumber == 1)) {
          return crew;
        }
      }
    }


    return null;
  }

  private boolean collegeMatch(String crewNameFromAnu, String nameFromOurcs, boolean isFirstBoat) {
    String anuCollegeName;
    if (isFirstBoat) {
      anuCollegeName = crewNameFromAnu;
    } else {
      anuCollegeName = crewNameFromAnu.substring(0, crewNameFromAnu.trim().lastIndexOf(" "));
    }
    String collegeNameFromOurcs = nameFromOurcs.substring(0, nameFromOurcs.trim().lastIndexOf(" "));
    if (anuCollegeName.equals(collegeNameFromOurcs)) {
      return true;
    }
    Map<String, String> anuToOurcsCollegeNames = new HashMap<>();
    anuToOurcsCollegeNames.put("New College", "New");
    anuToOurcsCollegeNames.put("S.E.H.", "St Edmund Hall");
    anuToOurcsCollegeNames.put("L.M.H.", "Lady Margaret Hall");
    anuToOurcsCollegeNames.put("St Benet's Hall", "St Benet's");

    String mappedName = anuToOurcsCollegeNames.get(anuCollegeName);
    if (mappedName != null && mappedName.equals(collegeNameFromOurcs)) {
      return true;
    }
    return false;

  }

  private int parseNumeral(String divNumber) {
    int divNum = 0;
    switch (divNumber) {
      case "I":
        divNum = 1;
        break;
      case "II":
        divNum = 2;
        break;
      case "III":
        divNum = 3;
        break;
      case "IV":
        divNum = 4;
        break;
      case "V":
        divNum = 5;
        break;
      case "VI":
        divNum = 6;
        break;
      case "VII":
        divNum = 7;
        break;
    }
    return divNum;
  }
}
