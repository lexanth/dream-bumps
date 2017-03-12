package com.alexanthony.dreambumps.repository;

import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.domain.enumeration.Sex;
import com.alexanthony.dreambumps.service.dto.CrewDTO;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Crew entity.
 */
@SuppressWarnings("unused")
public interface CrewRepository extends JpaRepository<Crew,Long> {

  List<Crew> findBySex(Sex sex);

}
