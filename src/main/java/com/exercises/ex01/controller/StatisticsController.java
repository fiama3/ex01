package com.exercises.ex01.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exercises.ex01.dto.EAggregationType;
import com.exercises.ex01.dto.GenericResponseDto;
import com.exercises.ex01.service.StatisticsService;


@RestController
@RequestMapping
public class StatisticsController {
	
	@Autowired
	StatisticsService statisticsService;
	
	@GetMapping({"/denormalized"})
	public ResponseEntity<?> getDenormalizedRecords(
			@RequestParam Optional<String> limit,
			@RequestParam Optional<String> offset) {
		GenericResponseDto response = new GenericResponseDto();
		try {
			response.setData(statisticsService.denormalize(limit, offset));
		} catch (Exception se) {
			response.setError(HttpStatus.BAD_REQUEST.toString());
			response.setMessage(se.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping({"/statistics"})
	public ResponseEntity<?> getStatistics(
			@RequestParam(required=true) EAggregationType aggregationType,
			@RequestParam(required=true) int aggregationValue) {
		GenericResponseDto response = new GenericResponseDto();
		try {
			response.setData(statisticsService.getStatistics(aggregationType, aggregationValue));
		} catch (Exception se) {
			response.setError(HttpStatus.BAD_REQUEST.toString());
			response.setMessage(se.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

