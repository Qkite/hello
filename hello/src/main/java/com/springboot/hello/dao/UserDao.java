package com.springboot.hello.dao;

import com.springboot.hello.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserDao {

    /*
    POST /user - user 등록하는 기능 -> add
    DELETE /user/{id} - 1명 유저 지우는 기능 -> delete from user where id = ?
    DELETE /user/all - 전체 유저 지우는 기능 -> delete from user

     */

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;


    public UserDao(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(User user){
        this.jdbcTemplate.update("insert into users(id, name, password) values(?, ?, ?)", user.getId(), user.getName(), user.getPassword());

    }

    public void deleteAll(){
        this.jdbcTemplate.update("delete from users");
    }

    public void deleteById(String id){
        this.jdbcTemplate.update("delete from users where id = ?", id);
    }
}
