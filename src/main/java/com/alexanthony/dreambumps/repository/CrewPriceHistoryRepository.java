package com.alexanthony.dreambumps.repository;

import com.alexanthony.dreambumps.domain.CrewPriceHistory;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CrewPriceHistory entity.
 */
@SuppressWarnings("unused")
public interface CrewPriceHistoryRepository extends JpaRepository<CrewPriceHistory,Long> {

  List<CrewPriceHistory> findByCrewId(Long crewId);

  CrewPriceHistory findTop1ByCrewIdOrderByDateTimeAsc(Long crewId);

}
