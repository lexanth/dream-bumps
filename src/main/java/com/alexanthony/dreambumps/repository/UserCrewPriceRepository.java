package com.alexanthony.dreambumps.repository;

import com.alexanthony.dreambumps.domain.UserCrewPrice;
import com.alexanthony.dreambumps.domain.enumeration.Sex;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the UserCrewPrice entity.
 */
@SuppressWarnings("unused")
public interface UserCrewPriceRepository extends JpaRepository<UserCrewPrice,Long> {

    @Query("select userCrewPrice from UserCrewPrice userCrewPrice where userCrewPrice.user.login = ?#{principal.username}")
    List<UserCrewPrice> findByUserIsCurrentUser();

    List<UserCrewPrice> findBySex(Sex sex);

    UserCrewPrice findFirstByUserIdAndSex(Long userId, Sex sex);

}
