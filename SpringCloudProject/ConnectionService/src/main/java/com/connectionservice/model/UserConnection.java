package com.connectionservice.model;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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

	@Relationship(type = "Blocked")
	private List<UserConnection> blocked;

	@Relationship(type = "BlockedBy")
	private List<UserConnection> blockedBy;

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

	public void blockUser(UserConnection userConnection) {
		if (blocked == null) {
			blocked = new ArrayList<>();
		}
		blocked.add(userConnection);
	}

	public void blockedByUser(UserConnection userConnection) {
		if (blockedBy == null) {
			blockedBy = new ArrayList<>();
		}
		blockedBy.add(userConnection);
	}

}
