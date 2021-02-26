package com.exercises.ex01.dto;

import lombok.Data;

@Data
public class StatisticsResponseDTO {
	private String aggregationType;
	private int aggregationFilter;
	private float capital_gain_sum;
	private float capital_gain_avg;
	private float capital_loss_sum;
	private float capital_loss_avg;
	private int over_50k_count;
	private int under_50k_count;
}