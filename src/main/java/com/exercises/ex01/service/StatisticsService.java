package com.exercises.ex01.service;

import java.util.List;
import java.util.Optional;

import com.exercises.ex01.dto.DenormalizeRecordsResponseDTO;
import com.exercises.ex01.dto.EAggregationType;
import com.exercises.ex01.dto.StatisticsResponseDTO;

public interface StatisticsService {
	
	public List<DenormalizeRecordsResponseDTO> denormalize(Optional<String> limit, Optional<String> offset) throws Exception;

	public StatisticsResponseDTO getStatistics(EAggregationType aggregationType, int aggregationValue) throws Exception;
	
	public List<DenormalizeRecordsResponseDTO> denormalize() throws Exception;
	
}
