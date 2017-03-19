package com.alexanthony.dreambumps.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.domain.CrewPositionHistory;
import com.alexanthony.dreambumps.service.dto.CrewPositionHistoryDTO;

@Mapper(componentModel = "spring", uses = {})
public interface CrewPositionHistoryMapper {

  @Mapping(source = "crew.id", target = "crewId")
  CrewPositionHistoryDTO crewPositionHistoryToCrewPositionHistoryDTO(CrewPositionHistory crewPositionHistory);
  
  List<CrewPositionHistoryDTO> crewPositionHistorysToCrewPositionHistoryDTOs(List<CrewPositionHistory> crewPositionHistories);
  
  @Mapping(source = "crewId", target = "crew")
  CrewPositionHistory crewPositionHistoryDTOToCrewPositionHistory(CrewPositionHistoryDTO crewPositionHistoryDTO);
  
  List<CrewPositionHistory> crewPositionHistoryDTOsToCrewPositionHistorys(List<CrewPositionHistoryDTO> crewPositionHistoryDTOs);
  
  default Crew crewFromId(Long id) {
    if (id == null) {
        return null;
    }
    Crew crew = new Crew();
    crew.setId(id);
    return crew;
}
}
