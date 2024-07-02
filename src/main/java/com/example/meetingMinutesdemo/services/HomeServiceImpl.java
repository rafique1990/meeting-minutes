//package com.example.meetingMinutesdemo.services;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Service
//public class HomeServiceImpl implements  HomeService {
//
//    @Value("${app.version:default_value}")//try to use properties file for this purpose
//    private String appVersion;
//    @Override
//    public Map getStatus() {
//        Map map = new HashMap<String,String>();
//        map.put("app-version",appVersion);
//        return map;
//    }
//}
