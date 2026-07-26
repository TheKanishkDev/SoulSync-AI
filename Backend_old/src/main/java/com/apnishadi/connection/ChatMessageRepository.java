package com.apnishadi.connection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
	List<ChatMessage> findByConversationIdOrderByCreatedAtAsc(Long conversationId);
	@Modifying
	@Transactional
	@Query("""
    DELETE FROM ChatMessage m
    WHERE m.conversation.interestRequest.senderProfile.id = :profileId
       OR m.conversation.interestRequest.receiverProfile.id = :profileId
    """)
	void deleteAllByProfileId(@Param("profileId") Long profileId);
}
