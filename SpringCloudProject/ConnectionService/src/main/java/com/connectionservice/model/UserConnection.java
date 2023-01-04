package com.connectionservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node
@Getter
@Setter
@NoArgsConstructor
public class UserConnection {

	@Id
    private String username;

    private boolean isPublic;
    
    public UserConnection(String username, boolean isPublic) {
    	this.username = username;
    	this.isPublic = isPublic;
	}

	@Relationship(type = "FollowRequest", direction = Relationship.Direction.INCOMING)
	private List<UserConnection> followRequests;

	@Relationship(type = "Follower", direction = Relationship.Direction.INCOMING)
	private List<UserConnection> followers;

	public void createFollowRequests(UserConnection userConnection) {
		if (followRequests == null) {
			followRequests = new ArrayList<>();
		}
		followRequests.add(userConnection);
	}

	public void createFollower(UserConnection userConnection) {
		if (followers == null) {
			followers = new ArrayList<>();
		}
		followers.add(userConnection);
	}
}
