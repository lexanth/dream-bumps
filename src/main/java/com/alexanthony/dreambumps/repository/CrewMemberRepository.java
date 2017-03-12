package com.alexanthony.dreambumps.repository;

import com.alexanthony.dreambumps.domain.CrewMember;
import com.alexanthony.dreambumps.service.dto.CrewMemberDTO;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CrewMember entity.
 */
@SuppressWarnings("unused")
public interface CrewMemberRepository extends JpaRepository<CrewMember,Long> {

  List<CrewMember> findByCrewId(Long crewId);

}
