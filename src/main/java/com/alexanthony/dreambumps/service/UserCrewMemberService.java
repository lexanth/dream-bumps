package com.alexanthony.dreambumps.service;

import com.alexanthony.dreambumps.domain.User;
import com.alexanthony.dreambumps.domain.UserCrewMember;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import com.alexanthony.dreambumps.repository.UserCrewMemberRepository;
import com.alexanthony.dreambumps.service.dto.UserCrewMemberDTO;
import com.alexanthony.dreambumps.service.mapper.UserCrewMemberMapper;
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

/**
 * Service Implementation for managing UserCrewMember.
 */
@Service
@Transactional
public class UserCrewMemberService {

    private final Logger log = LoggerFactory.getLogger(UserCrewMemberService.class);
    private static final Set<Integer> seats = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    
    private final UserCrewMemberRepository userCrewMemberRepository;

    private final UserCrewMemberMapper userCrewMemberMapper;

    public UserCrewMemberService(UserCrewMemberRepository userCrewMemberRepository, UserCrewMemberMapper userCrewMemberMapper) {
        this.userCrewMemberRepository = userCrewMemberRepository;
        this.userCrewMemberMapper = userCrewMemberMapper;
    }
    


    /**
     * Save a userCrewMember.
     *
     * @param userCrewMemberDTO the entity to save
     * @return the persisted entity
     */
    public UserCrewMemberDTO save(UserCrewMemberDTO userCrewMemberDTO) {
        log.debug("Request to save UserCrewMemberDTO : {}", userCrewMemberDTO);
        UserCrewMember userCrewMember = userCrewMemberMapper.userCrewMemberDTOToUserCrewMember(userCrewMemberDTO);
        userCrewMember = save(userCrewMember);
        UserCrewMemberDTO result = userCrewMemberMapper.userCrewMemberToUserCrewMemberDTO(userCrewMember);
        return result;
    }
    
    public UserCrewMember save(UserCrewMember userCrewMember) {
      log.debug("Request to save UserCrewMember : {}", userCrewMember);
      userCrewMember = userCrewMemberRepository.save(userCrewMember);
      return userCrewMember;
  }

    /**
     *  Get all the userCrewMembers.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<UserCrewMemberDTO> findAll() {
        log.debug("Request to get all UserCrewMembers");
        List<UserCrewMemberDTO> result = userCrewMemberRepository.findAll().stream()
            .map(userCrewMemberMapper::userCrewMemberToUserCrewMemberDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one userCrewMember by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public UserCrewMemberDTO findOneDTO(Long id) {
        log.debug("Request to get UserCrewMemberDTO : {}", id);
        UserCrewMember userCrewMember = findOne(id);
        UserCrewMemberDTO userCrewMemberDTO = userCrewMemberMapper.userCrewMemberToUserCrewMemberDTO(userCrewMember);
        return userCrewMemberDTO;
    }
    
    @Transactional(readOnly = true)
    public UserCrewMember findOne(Long id) {
        log.debug("Request to get UserCrewMember : {}", id);
        UserCrewMember userCrewMember = userCrewMemberRepository.findOne(id);
        return userCrewMember;
    }

    /**
     *  Delete the  userCrewMember by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete UserCrewMember : {}", id);
        userCrewMemberRepository.delete(id);
    }

	public List<UserCrewMemberDTO> getMembersForUserAndSex(Long userId, Sex sex) {
    log.debug("Request to get UserCrewMembers for user and sex : {}", userId);
		List<UserCrewMember> members = userCrewMemberRepository.findByUserIdAndSex(userId, sex);
		return members.stream().map(userCrewMemberMapper::userCrewMemberToUserCrewMemberDTO)
            .collect(Collectors.toCollection(LinkedList::new));
	}
	
	
	
	private List<UserCrewMember> createEmptyCrewMemberListForSex(User user, Sex sex) {
	  return seats.stream().map((seat) -> {
	    return new UserCrewMember().seat(seat).user(user).sex(sex);
	  }).collect(Collectors.toCollection(LinkedList::new));
	}
	
	public void createMembersForUser(User user) {
	  List<UserCrewMember> members = createEmptyCrewMemberListForSex(user, Sex.male);
	  members.addAll(createEmptyCrewMemberListForSex(user, Sex.female));
	  userCrewMemberRepository.save(members);
	}
	
	
}
