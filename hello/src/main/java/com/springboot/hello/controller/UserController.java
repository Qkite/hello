package com.springboot.hello.controller;

import com.springboot.hello.dao.UserDao;
import com.springboot.hello.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

        /*
    POST /user - user 등록하는 기능 -> add
    DELETE /user/{id} - 1명 유저 지우는 기능 -> delete from user where id = ?
    DELETE /user/all - 전체 유저 지우는 기능 -> delete from user
     */

    @PostMapping(value = "/user")
    public String add(){
        User user1 = new User("1028", "Today", "112233");
        userDao.add(user1);
        return "id: " + user1.getId() + ", name: " + user1.getName() + ", password: " + user1.getPassword();
    }


    @DeleteMapping(value = "/user/{id}")
    public String delete(@RequestParam String id){
        userDao.deleteById(id);
        return "id가 " + id + "인 데이터가 삭제되었습니다.";
    }

    @DeleteMapping(value="/user/all")
    public String deleteAll(){
        userDao.deleteAll();
        return "데이터가 전부 삭제되었습니다.";
    }


}