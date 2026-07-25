package com.apnishadi.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByEmailIgnoreCase(String email);
	boolean existsByEmailIgnoreCase(String email);
	@Modifying
	@Transactional
	@Query("DELETE FROM Account a WHERE a.profile.id = :profileId")
	void deleteByProfileId(@Param("profileId") Long profileId);
}
