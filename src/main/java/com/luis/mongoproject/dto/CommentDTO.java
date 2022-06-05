package com.luis.mongoproject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class CommentDTO {
	private String text;
	private Date date;
	private AuthorDTO author;
}
