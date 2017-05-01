package com.alexanthony.dreambumps.service;

import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.domain.CrewMember;
import com.alexanthony.dreambumps.repository.CrewMemberRepository;
import com.alexanthony.dreambumps.service.dto.CrewMemberDTO;
import com.alexanthony.dreambumps.service.mapper.CrewMemberMapper;
import com.sun.el.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Service Implementation for managing CrewMember.
 */
@Service
@Transactional
public class CrewMemberService {

  private final Logger log = LoggerFactory.getLogger(CrewMemberService.class);

  private static final Set<Integer> seats = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

  private final CrewMemberRepository crewMemberRepository;

  private final CrewMemberMapper crewMemberMapper;

  public CrewMemberService(CrewMemberRepository crewMemberRepository, CrewMemberMapper crewMemberMapper) {
    this.crewMemberRepository = crewMemberRepository;
    this.crewMemberMapper = crewMemberMapper;
  }

  /**
   * Save a crewMember.
   *
   * @param crewMemberDTO
   *          the entity to save
   * @return the persisted entity
   */
  public CrewMemberDTO save(CrewMemberDTO crewMemberDTO) {
    log.debug("Request to save CrewMember : {}", crewMemberDTO);
    CrewMember crewMember = crewMemberMapper.crewMemberDTOToCrewMember(crewMemberDTO);
    crewMember = crewMemberRepository.save(crewMember);
    CrewMemberDTO result = crewMemberMapper.crewMemberToCrewMemberDTO(crewMember);
    return result;
  }

  /**
   * Get all the crewMembers.
   *
   * @return the list of entities
   */
  @Transactional(readOnly = true)
  public List<CrewMemberDTO> findAll() {
    log.debug("Request to get all CrewMembers");
    List<CrewMemberDTO> result = crewMemberRepository.findAll().stream()
        .map(crewMemberMapper::crewMemberToCrewMemberDTO).collect(Collectors.toCollection(LinkedList::new));

    return result;
  }

  /**
   * Get one crewMember by id.
   *
   * @param id
   *          the id of the entity
   * @return the entity
   */
  @Transactional(readOnly = true)
  public CrewMemberDTO findOne(Long id) {
    log.debug("Request to get CrewMember : {}", id);
    CrewMember crewMember = crewMemberRepository.findOne(id);
    CrewMemberDTO crewMemberDTO = crewMemberMapper.crewMemberToCrewMemberDTO(crewMember);
    return crewMemberDTO;
  }

  /**
   * Delete the crewMember by id.
   *
   * @param id
   *          the id of the entity
   */
  public void delete(Long id) {
    log.debug("Request to delete CrewMember : {}", id);
    crewMemberRepository.delete(id);
  }

  public List<CrewMemberDTO> getCrewMembersForCrew(Long crewId) {
    log.debug("Request to get CrewMembers for crew : {}", crewId);
		return crewMemberRepository.findByCrewId(crewId).stream().map(crewMemberMapper::crewMemberToCrewMemberDTO).collect(Collectors.toCollection(LinkedList::new));
	}

  public void createMembersForCrew(Crew crew) {
    List<CrewMember> members = seats.stream().map((seat) -> {
      return new CrewMember().seat(seat).crew(crew).name(crew.getName() + " "+ Integer.toString(seat));
    }).collect(Collectors.toCollection(LinkedList::new));
    crewMemberRepository.save(members);
  }

  public List<CrewMemberDTO> save(List<CrewMemberDTO> crewMemberDTOs) {
    return crewMemberDTOs.stream().map(this::save).collect(Collectors.toCollection(LinkedList::new));
  }

  public List<CrewMember> saveMembers(List<CrewMember> crewMembers) {
    return crewMemberRepository.save(crewMembers);
  }
}
