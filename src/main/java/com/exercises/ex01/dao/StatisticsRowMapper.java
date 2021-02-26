package com.exercises.ex01.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.exercises.ex01.dto.StatisticsResponseDTO;

public class StatisticsRowMapper implements RowMapper<StatisticsResponseDTO>{
	@Override
	  public StatisticsResponseDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		StatisticsResponseDTO statisticsResponseDTO = new StatisticsResponseDTO();
		
		statisticsResponseDTO.setCapital_gain_avg(rs.getFloat("capital_gain_avg"));
		statisticsResponseDTO.setCapital_gain_sum(rs.getFloat("capital_gain_sum"));
		statisticsResponseDTO.setCapital_loss_avg(rs.getFloat("capital_loss_avg"));
		statisticsResponseDTO.setCapital_loss_sum(rs.getFloat("capital_loss_sum"));
		statisticsResponseDTO.setOver_50k_count(rs.getInt("over_50k_count"));
		statisticsResponseDTO.setUnder_50k_count(rs.getInt("total")-rs.getInt("over_50k_count"));
		
		return statisticsResponseDTO;
	  }
}
