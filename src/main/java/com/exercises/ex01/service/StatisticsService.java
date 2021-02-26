package com.exercises.ex01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exercises.ex01.dao.StatisticsDao;
import com.exercises.ex01.dto.DenormalizeRecordsResponseDTO;
import com.exercises.ex01.dto.EAggregationType;
import com.exercises.ex01.dto.StatisticsResponseDTO;

@Service
public class StatisticsService {
	@Autowired
	private StatisticsDao statisticsDao;
	
	public List<DenormalizeRecordsResponseDTO> denormalize(Optional<String> limit, Optional<String> offset) throws Exception {
		if(limit.isPresent() && offset.isPresent() && (Integer.parseInt(limit.get()) <= 0 || Integer.parseInt(offset.get()) < 0)) {
			throw new Exception("BAD_PARAMETERS");
		}
		return statisticsDao.denormalize(limit, offset);
	}

	public StatisticsResponseDTO getStatistics(EAggregationType aggregationType, int aggregationValue) {
		
		StatisticsResponseDTO statisticsResponse = statisticsDao.getStatistics(aggregationType, aggregationValue);
		
		statisticsResponse.setAggregationFilter(aggregationValue);
		statisticsResponse.setAggregationType(aggregationType.toString().toLowerCase());
		
		return statisticsResponse;
	}
	
}
