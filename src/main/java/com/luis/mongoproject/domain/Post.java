package com.luis.mongoproject.domain;

import com.luis.mongoproject.dto.AuthorDTO;
import com.luis.mongoproject.dto.CommentDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@Builder
public class Post {

	@Id
	private String id;
	private Date date;
	private String title;
	private String body;
	private AuthorDTO author;

	private List<CommentDTO> comments;

}
