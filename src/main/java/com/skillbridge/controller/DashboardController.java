package com.skillbridge.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {


    private final DashboardService dashboardService;


    public DashboardController(DashboardService dashboardService){

        this.dashboardService = dashboardService;

    }


    @GetMapping("/stats")
    public Map<String, Long> getStats(){

        return dashboardService.getDashboardStats();

    }

}