package com.alexanthony.dreambumps.repository;

import com.alexanthony.dreambumps.domain.MarketStatusHistory;
import com.alexanthony.dreambumps.service.dto.MarketStatusHistoryDTO;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MarketStatusHistory entity.
 */
@SuppressWarnings("unused")
public interface MarketStatusHistoryRepository extends JpaRepository<MarketStatusHistory,Long> {

  MarketStatusHistory findFirstByOrderByDateTimeDesc();

}
