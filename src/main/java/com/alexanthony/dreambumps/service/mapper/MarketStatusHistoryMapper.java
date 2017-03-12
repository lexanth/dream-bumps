package com.alexanthony.dreambumps.service.mapper;

import com.alexanthony.dreambumps.domain.*;
import com.alexanthony.dreambumps.service.dto.MarketStatusHistoryDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity MarketStatusHistory and its DTO MarketStatusHistoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MarketStatusHistoryMapper {

    MarketStatusHistoryDTO marketStatusHistoryToMarketStatusHistoryDTO(MarketStatusHistory marketStatusHistory);

    List<MarketStatusHistoryDTO> marketStatusHistoriesToMarketStatusHistoryDTOs(List<MarketStatusHistory> marketStatusHistories);

    MarketStatusHistory marketStatusHistoryDTOToMarketStatusHistory(MarketStatusHistoryDTO marketStatusHistoryDTO);

    List<MarketStatusHistory> marketStatusHistoryDTOsToMarketStatusHistories(List<MarketStatusHistoryDTO> marketStatusHistoryDTOs);
}
