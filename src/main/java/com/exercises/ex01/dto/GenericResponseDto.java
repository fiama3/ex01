package com.exercises.ex01.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class GenericResponseDto {
	private Object data;
	private String error;
	private String message;
	private LocalDateTime timestamp = LocalDateTime.now();
	
	public GenericResponseDto(Object data) {
		super();
		this.data = data;
	}	
}

