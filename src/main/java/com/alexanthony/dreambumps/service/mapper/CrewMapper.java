package com.alexanthony.dreambumps.service.mapper;

import com.alexanthony.dreambumps.domain.*;
import com.alexanthony.dreambumps.service.dto.CrewDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Crew and its DTO CrewDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CrewMapper {

    CrewDTO crewToCrewDTO(Crew crew);

    List<CrewDTO> crewsToCrewDTOs(List<Crew> crews);

    @Mapping(target = "crewMembers", ignore = true)
    @Mapping(target = "crewPriceHistories", ignore = true)
    @Mapping(target = "crewPositionHistories", ignore = true)
    Crew crewDTOToCrew(CrewDTO crewDTO);

    List<Crew> crewDTOsToCrews(List<CrewDTO> crewDTOs);
}
