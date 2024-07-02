//package com.example.meetingMinutesdemo.controllers;
//
//
//import com.example.meetingMinutesdemo.services.HomeServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//public class HomeController {
//
//    @Autowired
//    private HomeServiceImpl homeService;
//    @GetMapping
//    @RequestMapping("/home")
//    public Map getStatus(){
//        return  homeService.getStatus();
//    }
//
////    @GetMapping
////    @RequestMapping("/")
////    public String home(){
////        return  "<h1>Public Page</h1>";
////    }
//
//    @GetMapping
//    @RequestMapping("/user")
//    public String user(){
//        return  "<h1>User Page</h1>";
//    }
//
//    @GetMapping
//    @RequestMapping("/admin")
//    public String admin(){
//        return  "<h1>Admin Page</h1>";
//    }
//}
