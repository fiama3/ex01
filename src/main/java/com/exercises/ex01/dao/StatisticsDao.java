package com.exercises.ex01.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.exercises.ex01.dto.DenormalizeRecordsResponseDTO;
import com.exercises.ex01.dto.EAggregationType;
import com.exercises.ex01.dto.StatisticsResponseDTO;

@Repository
public class StatisticsDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("deprecation")
	public List<DenormalizeRecordsResponseDTO> denormalize(Optional<String> limit, Optional<String> offset) throws Exception{
		
		String query = "SELECT 	records.id, age, " + 
				"workclass_id, workclasses.name as workclass_name, " + 
				"education_level_id, education_levels.name as education_level_name, " + 
				"marital_status_id, marital_statuses.name as marital_status_name, " + 
				"occupation_id, occupations.name as occupation_name, " + 
				"race_id, races.name as race_name, " + 
				"sex_id, sexes.name as sex_name, " + 
				"country_id, countries.name as country_name " + 
				"FROM records " + 
				"LEFT JOIN workclasses ON records.workclass_id = workclasses.id " + 
				"LEFT JOIN education_levels ON records.education_level_id = education_levels.id " + 
				"LEFT JOIN marital_statuses ON records.marital_status_id = marital_statuses.id " + 
				"LEFT JOIN occupations ON records.occupation_id = occupations.id " + 
				"LEFT JOIN races ON records.race_id = races.id " + 
				"LEFT JOIN sexes ON records.sex_id = sexes.id " + 
				"LEFT JOIN countries ON records.country_id = countries.id " +
				"ORDER BY records.id";
		
		List<DenormalizeRecordsResponseDTO> queryResponse = new ArrayList<DenormalizeRecordsResponseDTO>();
		
		if(limit.isPresent() && offset.isPresent()) {
			query = query + " LIMIT ? OFFSET ?";
			queryResponse = jdbcTemplate.query(query, new Object[] { Integer.parseInt(limit.get()), Integer.parseInt(offset.get()) }, new DenormalizedRecordRowMapper()); 
		} else {
			queryResponse = jdbcTemplate.query(query, new DenormalizedRecordRowMapper()); 
		}
		
		
		return queryResponse;
	}

	public StatisticsResponseDTO getStatistics(EAggregationType aggregationType, int aggregationValue) {
		
		String query = "SELECT sum(capital_gain) as capital_gain_sum, " + 
				"avg(capital_gain) as capital_gain_avg, " + 
				"sum(capital_loss) as capital_loss_sum, " + 
				"avg(capital_loss) as capital_loss_avg, " + 
				"(SELECT count(*) FROM records WHERE " + aggregationType.toString().toLowerCase()+" = " + aggregationValue + " AND over_50k = TRUE) as over_50k_count, " + 
				"count(*) as total " + 
				"FROM records " + 
				"WHERE "+aggregationType.toString().toLowerCase()+" = "+aggregationValue;
		
		
		
		List<StatisticsResponseDTO> queryResponse = jdbcTemplate.query(query, new StatisticsRowMapper()); 
		
		return queryResponse.get(0);
	}

	public List<DenormalizeRecordsResponseDTO> denormalize() {
		String query = "SELECT 	records.id, age, " + 
				"workclass_id, workclasses.name as workclass_name, " + 
				"education_level_id, education_levels.name as education_level_name, " + 
				"marital_status_id, marital_statuses.name as marital_status_name, " + 
				"occupation_id, occupations.name as occupation_name, " + 
				"race_id, races.name as race_name, " + 
				"sex_id, sexes.name as sex_name, " + 
				"country_id, countries.name as country_name " + 
				"FROM records " + 
				"LEFT JOIN workclasses ON records.workclass_id = workclasses.id " + 
				"LEFT JOIN education_levels ON records.education_level_id = education_levels.id " + 
				"LEFT JOIN marital_statuses ON records.marital_status_id = marital_statuses.id " + 
				"LEFT JOIN occupations ON records.occupation_id = occupations.id " + 
				"LEFT JOIN races ON records.race_id = races.id " + 
				"LEFT JOIN sexes ON records.sex_id = sexes.id " + 
				"LEFT JOIN countries ON records.country_id = countries.id " +
				"ORDER BY records.id";
		
		List<DenormalizeRecordsResponseDTO> queryResponse = jdbcTemplate.query(query, new DenormalizedRecordRowMapper()); 
		
		return queryResponse;
	}
}




