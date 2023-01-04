package com.connectionservice.repository;

import com.connectionservice.model.UserConnection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends Neo4jRepository<UserConnection, String> {

	UserConnection findByUsername(String username);

	@Query("MATCH(n:UserConnection {username: $senderUsername })-[r:FollowRequest]-(p:UserConnection{username: $receiverUsername }) DELETE r")
	void removeFollowRequest(@Param("senderUsername") String senderUsername, @Param("receiverUsername") String receiverUsername);
}
