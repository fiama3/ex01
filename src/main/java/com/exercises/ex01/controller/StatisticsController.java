package com.exercises.ex01.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exercises.ex01.dto.DenormalizeRecordsResponseDTO;
import com.exercises.ex01.dto.EAggregationType;
import com.exercises.ex01.dto.GenericResponseDto;
import com.exercises.ex01.service.StatisticsService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;


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
	
	@GetMapping({"/denormalized/csv"})
    public void getDenormalizedRecordsCsv(HttpServletResponse response) throws Exception{

		String filename = "records.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        //create a csv writer
        StatefulBeanToCsv<DenormalizeRecordsResponseDTO> writer = new StatefulBeanToCsvBuilder<DenormalizeRecordsResponseDTO>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        //write all users to csv file
        writer.write(statisticsService.denormalize());
		
    }
}

