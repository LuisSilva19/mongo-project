package com.luis.mongoproject.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="user")
@Data
@Builder
public class User {

	@Id
	private String id;
	private String name;
	private String email;
	
	@DBRef(lazy = true)
	private List<Post> posts;
}
