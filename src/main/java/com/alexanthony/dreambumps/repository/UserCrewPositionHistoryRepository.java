package com.alexanthony.dreambumps.repository;

import com.alexanthony.dreambumps.domain.UserCrewPositionHistory;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the UserCrewPositionHistory entity.
 */
@SuppressWarnings("unused")
public interface UserCrewPositionHistoryRepository extends JpaRepository<UserCrewPositionHistory,Long> {

    @Query("select userCrewPositionHistory from UserCrewPositionHistory userCrewPositionHistory where userCrewPositionHistory.user.login = ?#{principal.username}")
    List<UserCrewPositionHistory> findByUserIsCurrentUser();

}
