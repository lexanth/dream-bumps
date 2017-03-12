package com.alexanthony.dreambumps.repository;

import com.alexanthony.dreambumps.domain.UserCrewPriceHistory;
import com.alexanthony.dreambumps.domain.enumeration.Sex;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the UserCrewPriceHistory entity.
 */
@SuppressWarnings("unused")
public interface UserCrewPriceHistoryRepository extends JpaRepository<UserCrewPriceHistory, Long> {

  @Query("select userCrewPriceHistory from UserCrewPriceHistory userCrewPriceHistory where userCrewPriceHistory.user.login = ?#{principal.username}")
  List<UserCrewPriceHistory> findByUserIsCurrentUser();

  List<UserCrewPriceHistory> findByUserIdAndSex(Long userId, Sex sex);

}
