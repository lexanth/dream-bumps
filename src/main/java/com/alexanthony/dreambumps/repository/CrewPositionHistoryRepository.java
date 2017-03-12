package com.alexanthony.dreambumps.repository;

import com.alexanthony.dreambumps.domain.CrewPositionHistory;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CrewPositionHistory entity.
 */
@SuppressWarnings("unused")
public interface CrewPositionHistoryRepository extends JpaRepository<CrewPositionHistory,Long> {

  List<CrewPositionHistory> findByCrewId(Long crewId);

}
