package com.alexanthony.dreambumps.repository;

import com.alexanthony.dreambumps.domain.UserCrewMember;
import com.alexanthony.dreambumps.domain.enumeration.Sex;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the UserCrewMember entity.
 */
@SuppressWarnings("unused")
public interface UserCrewMemberRepository extends JpaRepository<UserCrewMember,Long> {

    @Query("select userCrewMember from UserCrewMember userCrewMember where userCrewMember.user.login = ?#{principal.username}")
    List<UserCrewMember> findByUserIsCurrentUser();

    List<UserCrewMember> findByUserIdAndSex(Long userId, Sex sex);

}
