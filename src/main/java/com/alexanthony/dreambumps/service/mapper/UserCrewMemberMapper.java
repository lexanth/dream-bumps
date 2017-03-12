package com.alexanthony.dreambumps.service.mapper;

import com.alexanthony.dreambumps.domain.*;
import com.alexanthony.dreambumps.service.dto.UserCrewMemberDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity UserCrewMember and its DTO UserCrewMemberDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface UserCrewMemberMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "crew.id", target = "crewId")
    UserCrewMemberDTO userCrewMemberToUserCrewMemberDTO(UserCrewMember userCrewMember);

    List<UserCrewMemberDTO> userCrewMembersToUserCrewMemberDTOs(List<UserCrewMember> userCrewMembers);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "crewId", target = "crew")
    UserCrewMember userCrewMemberDTOToUserCrewMember(UserCrewMemberDTO userCrewMemberDTO);

    List<UserCrewMember> userCrewMemberDTOsToUserCrewMembers(List<UserCrewMemberDTO> userCrewMemberDTOs);

    default Crew crewFromId(Long id) {
        if (id == null) {
            return null;
        }
        Crew crew = new Crew();
        crew.setId(id);
        return crew;
    }
}
