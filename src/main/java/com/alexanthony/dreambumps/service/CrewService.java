package com.alexanthony.dreambumps.service;

import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.domain.CrewMember;
import com.alexanthony.dreambumps.domain.CrewPositionHistory;
import com.alexanthony.dreambumps.domain.CrewPriceHistory;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import com.alexanthony.dreambumps.repository.CrewRepository;
import com.alexanthony.dreambumps.service.dto.CrewDTO;
import com.alexanthony.dreambumps.service.dto.CrewPositionHistoryDTO;
import com.alexanthony.dreambumps.service.mapper.CrewMapper;
import org.apache.commons.lang3.text.WordUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Crew.
 */
@Service
@Transactional
public class CrewService {

  private final Logger log = LoggerFactory.getLogger(CrewService.class);

  private final CrewRepository crewRepository;

  private final CrewMapper crewMapper;

  private final CrewMemberService crewMemberService;

  private final CrewPositionHistoryService crewPositionHistoryService;

  private final CrewPriceHistoryService crewPriceHistoryService;

  public CrewService(CrewRepository crewRepository, CrewMapper crewMapper, CrewMemberService crewMemberService, CrewPositionHistoryService crewPositionHistoryService, CrewPriceHistoryService crewPriceHistoryService) {
    this.crewRepository = crewRepository;
    this.crewMapper = crewMapper;
    this.crewMemberService = crewMemberService;
    this.crewPositionHistoryService = crewPositionHistoryService;
    this.crewPriceHistoryService = crewPriceHistoryService;
  }

  /**
   * Save a crew.
   *
   * @param crewDTO
   *          the entity to save
   * @return the persisted entity
   */
  public CrewDTO saveWithStartPosition(CrewDTO crewDTO) {
    Integer startPosition = crewDTO.getStartPosition();
    if (startPosition == null) {
      throw new RuntimeException("No start position");
    }
    CrewDTO result = save(crewDTO);
    CrewPositionHistory crewPositionHistory = new CrewPositionHistory().day(0).crew(crewMapper.crewDTOToCrew(crewDTO)).position(startPosition);
    crewPositionHistoryService.save(crewPositionHistory);
    return result;
  }

  public CrewDTO save(CrewDTO crewDTO) {
    log.debug("Request to save Crew : {}", crewDTO);
    Crew crew = crewMapper.crewDTOToCrew(crewDTO);
    crew = save(crew);
    CrewDTO result = crewMapper.crewToCrewDTO(crew);
    return result;
  }

  public Crew save(Crew crew) {
    boolean isNew = crew.getId() == null;
    crew = crewRepository.save(crew);
    crewPriceHistoryService.createForCrew(crew);
    if (isNew) {
      crewMemberService.createMembersForCrew(crew);
    }
    return crew;
  }

  /**
   * Get all the crews.
   *
   * @return the list of entities
   */
  @Transactional(readOnly = true)
  public List<CrewDTO> findAll() {
    log.debug("Request to get all Crews");
    List<CrewDTO> result = crewRepository.findAll().stream().map(crewMapper::crewToCrewDTO).map(this::populatePositionsAndPrices)
        .collect(Collectors.toCollection(LinkedList::new));

    return result;
  }

  /**
   * Get one crew by id.
   *
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Transactional(readOnly = true)
  public CrewDTO findOneDTO(Long id) {
    log.debug("Request to get CrewDTO : {}", id);
    Crew crew = crewRepository.findOne(id);
    CrewDTO crewDTO = crewMapper.crewToCrewDTO(crew);

    return populatePositionsAndPrices(crewDTO);
  }

  @Transactional(readOnly = true)
  public Crew findOne(Long id) {
    log.debug("Request to get Crew : {}", id);
    return crewRepository.findOne(id);

  }

  private CrewDTO populatePositionsAndPrices(CrewDTO crewDTO) {
    List<CrewPositionHistoryDTO> positionHistories = crewPositionHistoryService.findAllByCrew(crewDTO.getId());
    Optional<CrewPositionHistoryDTO> zeroDay = positionHistories.stream().filter(a -> a.getDay().equals(0)).findFirst();
    if (zeroDay.isPresent()) {
      crewDTO.setStartPosition(zeroDay.get().getPosition());
    }
    // Might want to reverse the sorting here
    Optional<CrewPositionHistoryDTO> mostRecentDay = positionHistories.stream().sorted((d1,d2) -> Integer.compare(d2.getDay(), d1.getDay())).findFirst();
    if (mostRecentDay.isPresent()) {
      crewDTO.setPosition(mostRecentDay.get().getPosition());
    }

    CrewPriceHistory oldestPriceHistory = crewPriceHistoryService.findOldestForCrew(crewDTO.getId());
    if (oldestPriceHistory != null) {
      crewDTO.setStartPrice(oldestPriceHistory.getPrice());
    }

    return crewDTO;
  }

  /**
   * Delete the crew by id.
   *
   * @param id
   *          the id of the entity
   */
  public void delete(Long id) {
    log.debug("Request to delete Crew : {}", id);
    crewRepository.delete(id);
  }

  public List<CrewDTO> findAllForSex(Sex sex) {
    log.debug("Request to find all Crews for : {}", sex);
    return crewRepository.findBySex(sex).stream().map(crewMapper::crewToCrewDTO).map(this::populatePositionsAndPrices)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  public List<CrewDTO> populateCrewsFromOurcs(String url) throws IOException {
    log.debug("Populating all crews from OURCs");
    log.debug(url);
    if (!crewRepository.findAll().isEmpty()) {
      // throw an error
    }
    Document entriesPage = Jsoup.connect(url).get();

    Elements tables = entriesPage.getElementsByTag("div");
    Elements collegePanels = entriesPage.getElementsByAttributeValueStarting("id", "club-");

    List<Crew> crews = crewRepository.findAll();

    List<Crew> crewList = new ArrayList<>();

    for (Element collegePanel : collegePanels) {
      String college = null;
      Elements links = collegePanel.getElementsByTag("a");
      for (Element link : links) {
        for (Attribute attr : link.attributes()) {
          if (attr.getKey().equals("href") && attr.getValue().startsWith("/clubs/")) {
            college = link.text();
            System.out.println(college);
          }
        }
      }
      Elements crewHeaders = collegePanel.getElementsByTag("h4");
      for (Element crewHeader : crewHeaders) {
        if (hasExistingCrewForName(crewHeader.text(), crews)) {
          continue;
        }
        Crew crew = new Crew();
        crewList.add(crew);
        String crewName = crewHeader.text();
        crew.setName(crewName);
        if (crewName.contains("W1") || crewName.contains("W2") || crewName.contains("W3") || crewName.contains("W4")) {
          crew.setSex(Sex.female);
        } else {
          crew.setSex(Sex.male);
        }
        crew.setCrewMembers(new HashSet<>());
        crew.setPrice(BigDecimal.ZERO);
        System.out.println(crewHeader.text());
        Element crewPanel = crewHeader.parent().parent();
        Elements crewMemberRows = crewPanel.getElementsByTag("tr");
        for (Element crewMemberRow : crewMemberRows) {
//          System.out.println(crewMemberRow.text());
          String seat = crewMemberRow.child(0).text();
          String name = crewMemberRow.child(1).text();
          if (!"Coach".equals(seat)) {
            CrewMember member = new CrewMember();
            member.setCrew(crew);
            member.setName(WordUtils.capitalizeFully(name, ' ', '-', '\''));
            if (name.trim().isEmpty()) {
              member.setName("Unnamed "+seat);
            }
            if (seat.equals("Bow")) {
              member.setSeat(1);
            } else if (seat.equals("Str")) {
              member.setSeat(8);
            } else if (seat.equals("Cox")) {
              member.setSeat(9);
            } else {
              member.setSeat(Integer.parseInt(seat));
            }
            crew.getCrewMembers().add(member);
          }
        }
        if (crew.getCrewMembers().size() < 9) {
          fillCrew(crew);
        }
      }
    }

//    Elements tables = entriesPage.getElementsByTag("table");
////    for (Element table: tables) {
////      System.out.println(table.text());
////    }
//    Element crewListTable = tables.get(1);
//    Elements alternatingHeadersAndCrewListsAndReturnToTop = crewListTable.getElementsByTag("tr");
//    String college = null;
//
//    List<Crew> crewList = new ArrayList<>();
//    for (Element row: alternatingHeadersAndCrewListsAndReturnToTop) {
//      if (row.children().size() > 0) {
//        Elements collegeHeaders = row.getElementsByTag("h2");
//        if (collegeHeaders.size() > 0) {
//          college = collegeHeaders.get(0).text();
////          System.out.println("College: "+college);
//        } else {
//          Elements crewTables = row.getElementsByClass("entry_list");
//          if (crewTables.size() > 0) {
//            Elements mensCrews = row.children().get(0).children();
//            String crewName = null;
//            for (Element item: mensCrews) {
//              if (item.tagName().equals("h3")) {
//                crewName = "M"+item.text().split("n's ")[1].substring(0,1);
////                System.out.println("Crew: "+crewName);
//              } else {
//                Crew crew = new Crew();
//                crewList.add(crew);
//                crew.setName(college+" "+crewName);
//                crew.setSex(Sex.male);
//                crew.setCrewMembers(new HashSet<>());
//                crew.setPrice(BigDecimal.ZERO);
//
//                for (Element memberRow : item.getElementsByTag("tr")) {
//                  String seat = memberRow.child(0).text();
//                  String name = memberRow.child(1).text();
//                  name = name.split("\\(")[0];
//                  if (!seat.equals("Coach")) {
//                    CrewMember member = new CrewMember();
//                    member.setName(WordUtils.capitalizeFully(name, ' ', '-'));
//                    member.setCrew(crew);
//                    if (seat.equals("Bow")) {
//                      member.setSeat(1);
//                    } else if (seat.equals("Stroke")) {
//                      member.setSeat(8);
//                    } else if (seat.equals("Cox")) {
//                      member.setSeat(9);
//                    } else {
//                      member.setSeat(Integer.parseInt(seat));
//                    }
//                    if (name.trim().isEmpty()) {
//                      member.setName("Unnamed "+seat);
//                    }
//                    crew.getCrewMembers().add(member);
//                  }
//                }
//                if (crew.getCrewMembers().size() < 9) {
//                  fillCrew(crew);
//                }
//              }
//            }
//
//
//            Elements womensCrews = row.children().get(1).children();
//            crewName = null;
//            for (Element item: womensCrews) {
//              if (item.tagName().equals("h3")) {
//                crewName = "W"+item.text().split("n's ")[1].substring(0,1);
//                System.out.println("Crew: "+crewName);
//              } else {
//                Crew crew = new Crew();
//                crewList.add(crew);
//                crew.setName(college+" "+crewName);
//                crew.setSex(Sex.female);
//                crew.setCrewMembers(new HashSet<>());
//                crew.setPrice(BigDecimal.ZERO);
//
//                for (Element memberRow : item.getElementsByTag("tr")) {
//                  String seat = memberRow.child(0).text();
//                  String name = memberRow.child(1).text();
//                  name = name.split("\\(")[0];
//                  if (!seat.equals("Coach")) {
//                    CrewMember member = new CrewMember();
//                    member.setName(WordUtils.capitalizeFully(name, ' ', '-'));
//                    member.setCrew(crew);
//                    if (seat.equals("Bow")) {
//                      member.setSeat(1);
//                    } else if (seat.equals("Stroke")) {
//                      member.setSeat(8);
//                    } else if (seat.equals("Cox")) {
//                      member.setSeat(9);
//                    } else {
//                      member.setSeat(Integer.parseInt(seat));
//                    }
//                    if (name.trim().isEmpty()) {
//                      member.setName("Unnamed "+seat);
//                    }
//                    crew.getCrewMembers().add(member);
//                  }
//                }
//                if (crew.getCrewMembers().size() < 9) {
//                  fillCrew(crew);
//                }
//              }
//            }
//          }
//        }
//      }
//    }
    crewList = crewRepository.save(crewList);
    List<CrewMember> membersToSave = new ArrayList<>();
    for (Crew crew: crewList) {
      for (CrewMember member: crew.getCrewMembers()) {
        member.setCrew(crew);
        membersToSave.add(member);
      }
    }
    crewMemberService.saveMembers(membersToSave);
    return crewMapper.crewsToCrewDTOs(crewList);
  }

  private boolean hasExistingCrewForName(String text, List<Crew> crews) {
    for (Crew crew: crews) {
      if (crew.getName().equals(text)) {
        return true;
      }
    }
    // HAX - crews that got entered but aren't racing
    if (text.equals("Worcester M3")
      || text.equals("St Benet's M2")
      || text.equals("Keble M5")
      || text.equals("St Edmund Hall M3")
      || text.equals("St Peter's M3")
      || text.equals("Magdalen M4")
      || text.equals("New College W3")) {
      return true;
    }
    return false;
  }

  private void fillCrew(Crew crew) {
    for (int seat = 1; seat <= 9; seat++) {
      Optional<CrewMember> member = findMemberForSeat(crew.getCrewMembers(), seat);
      if (!member.isPresent()) {
        CrewMember newMember = new CrewMember()
          .name("Unnamed "+Integer.toString(seat))
          .crew(crew)
          .seat(seat);
        crew.getCrewMembers().add(newMember);
      }
    }
  }

  private Optional<CrewMember> findMemberForSeat(Set<CrewMember> crewMembers, int seat) {
    return crewMembers.stream().filter(member -> member.getSeat() == seat).findFirst();
  }
}
