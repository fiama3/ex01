package com.exercises.ex01.dto;

import lombok.Data;

@Data
public class DenormalizeRecordsResponseDTO {
	private int id;
	private int age;
	private int workclassId;
	private String workclassName;
	private int educationLevelId;
	private String educationLevelName;
	private int marital_statusId;
	private String maritalStatusName;
	private int occupationId;
	private String occupationName;
	private int raceId;
	private String raceName;
	private int sexId;
	private String sexName;
	private int countryId;
	private String countryName;
}

