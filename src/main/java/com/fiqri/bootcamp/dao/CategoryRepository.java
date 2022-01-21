package com.fiqri.bootcamp.dao;

import com.fiqri.bootcamp.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class CategoryRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public List<Category> list() {
        return this.namedJdbcTemplate.query(
                "Select * From category", new RowMapper<Category>() {
                    @Override
                    public Category mapRow(ResultSet result, int rowNum) throws SQLException {
                        return new Category(
                                result.getInt(1),
                                result.getInt(2),
                                result.getString(3),
                                result.getString(4));
                    }
                });
    }

    public Category findById(Integer category_id) throws EmptyResultDataAccessException {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("Id", category_id);
        return this.namedJdbcTemplate.queryForObject("select * from Category where category_id=:Id",
                map, new RowMapper<Category>() {
                    @Override
                    public Category mapRow(ResultSet result, int rowNum) throws SQLException {
                        return new Category(
                                result.getInt(1),
                                result.getInt(2),
                                result.getString(3),
                                result.getString(4));

                    }
                });
    }

    public Category findbyIdDuplicate(Integer category_id) throws IncorrectResultSizeDataAccessException,
            EmptyResultDataAccessException {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("category_id", category_id);
        return this.namedJdbcTemplate.queryForObject("select * from Category", map, new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet result, int rowNum) throws SQLException {
                return new Category(
                        result.getInt(1),
                        result.getInt(2),
                        result.getString(3),
                        result.getString(4));

            }
        });

    }

    public Category findByIdNothrowing(Integer category_id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("category_id", category_id);
        return this.namedJdbcTemplate.queryForObject("select * from category where category_id =:category_id", map, new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet result, int rowNum) throws SQLException {
                return new Category(
                        result.getInt(1),
                        result.getInt(2),
                        result.getString(3),
                        result.getString(4));
            }
        });
    }

    @Transactional
    public Category insert(Category value) {

        String query = "insert into category(department_id,name, description) values (:department_id, :name, :desc)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name", value.getname());
        map.addValue("desc", value.getdescription());
        map.addValue("department_id", value.getdepartment_id());

        this.namedJdbcTemplate.update(query, map);

        return value;
    }

    @Transactional
    public Category update(Category value) {

        String query = "update category set department_id=:department_id,name=:name, description=:description Where category_id=:category_id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name", value.getname());
        map.addValue("description", value.getdescription());
        map.addValue("department_id", value.getdepartment_id());
        map.addValue("category_id", value.getcategory_id());

        this.namedJdbcTemplate.update(query , map);

        return value;
    }
    @Transactional
    public void delete(int id) throws SQLException{
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("category_id",id);

        String sql ="DELETE FROM category WHERE category_id=:category_id";
        this.namedJdbcTemplate.update(sql,map);

    }

}
