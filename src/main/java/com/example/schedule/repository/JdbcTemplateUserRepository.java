package com.example.schedule.repository;

import com.example.schedule.dto.UserResponseDto;
import com.example.schedule.entity.User;
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
public class JdbcTemplateUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserResponseDto registerUser(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("user").usingGeneratedKeyColumns("userId");

        LocalDateTime requestTime = LocalDateTime.now();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userName", user.getName());
        parameters.put("email", user.getEmail());
        parameters.put("createdAt", requestTime);
        parameters.put("updatedAt", requestTime);

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new UserResponseDto(key.longValue(), user.getName(), user.getEmail(), requestTime, requestTime);
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        return jdbcTemplate.query("select * from user", userRowMapper());
    }

    private RowMapper<UserResponseDto> userRowMapper() {
        return new RowMapper<UserResponseDto>(){
            @Override
            public UserResponseDto mapRow(ResultSet rs,int rowNum) throws SQLException{
                Timestamp createdAtTS = rs.getTimestamp("createdAt");
                Timestamp updatedAtTS = rs.getTimestamp("updatedAt");
                LocalDateTime createdAt = createdAtTS.toLocalDateTime();
                LocalDateTime updatedAt = updatedAtTS.toLocalDateTime();

                return new UserResponseDto(
                        rs.getLong("userId"),
                        rs.getString("userName"),
                        rs.getString("email"),
                        createdAt,
                        updatedAt
                );
            }
        };
    }
}
