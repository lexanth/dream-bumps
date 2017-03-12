package com.alexanthony.dreambumps.service.mapper;

import com.alexanthony.dreambumps.domain.*;
import com.alexanthony.dreambumps.service.dto.CrewMemberDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity CrewMember and its DTO CrewMemberDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CrewMemberMapper {

    @Mapping(source = "crew.id", target = "crewId")
    CrewMemberDTO crewMemberToCrewMemberDTO(CrewMember crewMember);

    List<CrewMemberDTO> crewMembersToCrewMemberDTOs(List<CrewMember> crewMembers);

    @Mapping(source = "crewId", target = "crew")
    CrewMember crewMemberDTOToCrewMember(CrewMemberDTO crewMemberDTO);

    List<CrewMember> crewMemberDTOsToCrewMembers(List<CrewMemberDTO> crewMemberDTOs);

    default Crew crewFromId(Long id) {
        if (id == null) {
            return null;
        }
        Crew crew = new Crew();
        crew.setId(id);
        return crew;
    }
}
