package com.exercises.ex01.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.exercises.ex01.dto.DenormalizeRecordsResponseDTO;

public class DenormalizedRecordRowMapper implements RowMapper<DenormalizeRecordsResponseDTO>{
	@Override
	  public DenormalizeRecordsResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		DenormalizeRecordsResponseDTO denormalizeRecords = new DenormalizeRecordsResponseDTO();
		denormalizeRecords.setId(rs.getInt("id"));
		denormalizeRecords.setAge(rs.getInt("age"));
		denormalizeRecords.setWorkclassId(rs.getInt("workclass_id"));
		denormalizeRecords.setWorkclassName(rs.getString("workclass_name"));
		denormalizeRecords.setEducationLevelId(rs.getInt("education_level_id"));
		denormalizeRecords.setEducationLevelName(rs.getString("education_level_name"));
		denormalizeRecords.setMarital_statusId(rs.getInt("marital_status_id"));
		denormalizeRecords.setMaritalStatusName(rs.getString("marital_status_name"));
		denormalizeRecords.setOccupationId(rs.getInt("occupation_id"));
		denormalizeRecords.setOccupationName(rs.getString("occupation_name"));
		denormalizeRecords.setRaceId(rs.getInt("race_id"));
		denormalizeRecords.setRaceName(rs.getString("race_name"));
		denormalizeRecords.setSexId(rs.getInt("sex_id"));
		denormalizeRecords.setSexName(rs.getString("sex_name"));
		denormalizeRecords.setCountryId(rs.getInt("country_id"));
		denormalizeRecords.setCountryName(rs.getString("country_name"));
	    return denormalizeRecords;
	  }
}
