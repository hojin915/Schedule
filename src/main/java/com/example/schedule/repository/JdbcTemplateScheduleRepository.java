package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.ScheduleUpdateRequestDto;
import com.example.schedule.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ScheduleResponseDto createSchedule(Todo todo) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("todoId");

        LocalDateTime requestTime = LocalDateTime.now();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", todo.getTodo());
        parameters.put("writerName", todo.getWriterName());
        parameters.put("userId", todo.getWriterId());
        parameters.put("password", todo.getPassword());
        parameters.put("createdAt", requestTime);
        parameters.put("updatedAt", requestTime);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), todo.getTodo(), todo.getWriterName(), todo.getWriterId(), requestTime, requestTime);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from schedule", scheduleRowMapper());
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long todoId) {
        String sql = "select * from schedule where todoId = ?";
        List<ScheduleResponseDto> result = jdbcTemplate.query(sql, scheduleRowMapper(), todoId);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public ScheduleResponseDto updateSchedule(ScheduleUpdateRequestDto dto, Long todoId) {
        String sql = "update schedule set todo = ?, writerName = ?, updatedAt = ? where todoId = ?";
        jdbcTemplate.update(sql, dto.getTodo(), dto.getWriterName(), LocalDateTime.now(), todoId);
        return findScheduleById(todoId);
    }

    @Override
    public ScheduleResponseDto updateScheduleTodo(ScheduleUpdateRequestDto dto, Long todoId) {
        String sql = "update schedule set todo = ?, updatedAt = ? where todoId = ?";
        jdbcTemplate.update(sql, dto.getTodo(), LocalDateTime.now(), todoId);
        return findScheduleById(todoId);
    }

    @Override
    public ScheduleResponseDto updateScheduleWriterName(ScheduleUpdateRequestDto dto, Long todoId) {
        String sql = "update schedule set writerName = ?, updatedAt = ? where todoId = ?";
        jdbcTemplate.update(sql, dto.getWriterName(), LocalDateTime.now(), todoId);
        return findScheduleById(todoId);
    }

    @Override
    public String findPasswordById(Long todoId){
        String sql = "select password from schedule where todoId = ?";
        List<String> result = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("password"), todoId);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public int deleteSchedule(Long todoId) {
        return jdbcTemplate.update("delete from schedule where todoId = ?", todoId);
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper(){
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs,int rowNum) throws SQLException {
                Timestamp createdAtTS = rs.getTimestamp("createdAt");
                Timestamp updatedAtTS = rs.getTimestamp("updatedAt");
                LocalDateTime createdAt = createdAtTS.toLocalDateTime();
                LocalDateTime updatedAt = updatedAtTS.toLocalDateTime();

                return new ScheduleResponseDto(
                        rs.getLong("todoId"),
                        rs.getString("todo"),
                        rs.getString("writerName"),
                        rs.getLong("userId"),
                        createdAt,
                        updatedAt
                );
            }
        };
    }
}
