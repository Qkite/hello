package com.springboot.hello.controller;

import com.springboot.hello.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello World";
    }

    @GetMapping(value = "/name")
    public String getName(){
        return "Kyeongrok";
    }

    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }

    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String str){
        return str;
    }
    // 맞추기 어려울 때 @PathVariable에 파라미터의 이름을 적으면 됩니다



    @GetMapping(value = "/request1")
    public String getRequestParam1(@RequestParam String name, @RequestParam String email, @RequestParam String organization){
        return String.format("%s %s %s", name, email, organization);
    }

    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param){
        param.entrySet().forEach(map -> {
            System.out.printf("key:%s value:%s\n", map.getKey(), map.getValue());
        } );

        return "request2가 호출 완료 되었습니다";
        // Java8의 Stream 형식
        //(mpa -> {}) 이 부분이 메소드의 형식이라고 생각하면 됨
    }

    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDto){
        System.out.println(memberDto);
        // return "request3 호출 완료";
        return memberDto.toString();
    }


}
