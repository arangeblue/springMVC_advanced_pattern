package com.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @title : OrderControllerV1
 * @author : wikyubok
 * @date : "2021-11-02 16:37:03"
 * @description : 프록시 패턴 v1 orderController 인터페이스
 */


@RequestMapping // 스프링은 @Controller 또는 @ReqeustMapping이 있어야 스프링 컨트롤러로 인식
@ResponseBody
public interface OrderControllerV1 {
    
    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId);

    @GetMapping("/v1/no-log")
    String noLog();

}
