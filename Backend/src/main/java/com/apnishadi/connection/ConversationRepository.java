package com.apnishadi.connection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
	Optional<Conversation> findByInterestRequestId(Long interestRequestId);

	@Query("""
			select c from Conversation c
			where c.profileOne.id = :profileId or c.profileTwo.id = :profileId
			order by c.createdAt desc
			""")
	List<Conversation> findForProfile(@Param("profileId") Long profileId);
	@Modifying
	@Transactional
	@Query("""
    DELETE FROM Conversation c
    WHERE c.interestRequest.senderProfile.id = :profileId
       OR c.interestRequest.receiverProfile.id = :profileId
    """)
	void deleteAllByProfileId(@Param("profileId") Long profileId);
}
