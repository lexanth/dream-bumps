package com.alexanthony.dreambumps.repository;

import com.alexanthony.dreambumps.domain.Crew;
import com.alexanthony.dreambumps.domain.UserCrewPositionHistory;

import com.alexanthony.dreambumps.domain.enumeration.Sex;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the UserCrewPositionHistory entity.
 */
@SuppressWarnings("unused")
public interface UserCrewPositionHistoryRepository extends JpaRepository<UserCrewPositionHistory,Long> {

    @Query("select userCrewPositionHistory from UserCrewPositionHistory userCrewPositionHistory where userCrewPositionHistory.user.login = ?#{principal.username}")
    List<UserCrewPositionHistory> findByUserIsCurrentUser();

  List<UserCrewPositionHistory> findByDay(Integer day);

  List<UserCrewPositionHistory> findByCrewAndDay(Crew crew, Integer day);

  @Query("select userCrewPositionHistory from UserCrewPositionHistory userCrewPositionHistory where userCrewPositionHistory.user.id = :userId and userCrewPositionHistory.sex = :sex")
  List<UserCrewPositionHistory> findByUserIdAndSex(@Param("userId") Long userId, @Param("sex") Sex sex);
}
