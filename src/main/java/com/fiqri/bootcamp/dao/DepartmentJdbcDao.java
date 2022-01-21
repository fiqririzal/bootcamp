package com.fiqri.bootcamp.dao;

import com.fiqri.bootcamp.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DepartmentJdbcDao {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public List<Department> getListDepartmenJdbcTemplate() {
        String sql = "SELECT department_id, name, description FROM department order by department_id ";
        return jdbcTemplate.query(sql, (result, rowNum) -> {
            Department department = new Department();
            department.setDekripsi(result.getString("description"));
            department.setName(result.getString("name"));
            department.setId(result.getInt("department_id"));
            return department;
        });
    }

    public Department findById(Integer id) throws EmptyResultDataAccessException {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);

        return this.namedJdbcTemplate.queryForObject(
                "Select * From department Where department_id",
                map,
                new RowMapper<Department>() {
                    @Override
                    public Department mapRow(ResultSet result, int rowNum) throws SQLException {
                        return new Department(
                                result.getInt(1),
                                result.getString(2),
                                result.getString(3)
                        );
                    }
                });
    }

    @Transactional
    public Department insert(Department value) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String query = "insert into department(department_id, name, description)\n" +
                "values (nextval('department_department_id_seq'), :nama, :desc)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", value.getNama());
        map.addValue("desc", value.getDekripsi());
        this.namedJdbcTemplate.update(query, map, keyHolder, new String[]{"department_id"});

        Number key = keyHolder.getKey();
        value.setId(key.intValue());
        return value;
    }
    @Transactional
    public void updateById(Department value) {
        String query = "update department set name = :name where department_id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name", value.getNama());
        map.addValue("id", value.getId());
        this.namedJdbcTemplate.update(query, map);
    }

    public List<Department> getListDepartmentPS() {
        String sql = "SELECT department_id, name, description FROM department order by department_id";
        List<Department> departmentList = null;

        try (PreparedStatement data = dataSource.getConnection().prepareStatement(sql)) {
            departmentList = new ArrayList<>();
            ResultSet result = data.executeQuery();
            while (result.next()) {
                Department department = new Department();
                department.setDekripsi(result.getString("description"));
                department.setName(result.getString("name"));
                department.setId(result.getInt("department_id"));
                departmentList.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departmentList;

    }

    public List<Department> list() {
        MapSqlParameterSource map = new MapSqlParameterSource();
        return this.namedJdbcTemplate.query(
                "select * from department",
                new RowMapper<Department>() {
                    @Override
                    public Department mapRow(ResultSet result, int rowNum) throws SQLException {
                        return new Department(
                                result.getInt(1),
                                result.getString(2),
                                result.getString(3));
                    }
                });
    }
}
