package com.alexanthony.dreambumps.repository;

import com.alexanthony.dreambumps.domain.UserCrewPrice;
import com.alexanthony.dreambumps.domain.enumeration.Sex;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

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

//SELECT * FROM USER_CREW_PRICE where sex = 'male'
  // SELECT * FROM USER_CREW_PRICE where sex = 'male' and user_id in (select id from USER_CREW_MEMBER  where crew_id = 994)
  @Query("select userCrewPrice from UserCrewPrice userCrewPrice where userCrewPrice.sex = :sex and userCrewPrice.user.id in (select userCrewMember.user.id from UserCrewMember userCrewMember where userCrewMember.crew.id = :crewId)")
  List<UserCrewPrice> findByOwnsCrewWithIdAndSex(@Param("crewId") Long id, @Param("sex") Sex sex);
}
