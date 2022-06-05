package com.luis.mongoproject.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthorDTO {
	private String id;
	private String name;
}
