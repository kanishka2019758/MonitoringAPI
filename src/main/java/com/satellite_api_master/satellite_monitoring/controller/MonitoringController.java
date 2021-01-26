package com.satellite_api_master.satellite_monitoring.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController

@CrossOrigin("*")
public class MonitoringController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "accessSatellite/{id}/{url}/{url2}")
    public String accessSatellite(@PathVariable ("id") Long id, @PathVariable ("url") String url, @PathVariable ("url2") String url2) {
        String satellite_data ="";
        String launch_data ="";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        String satellite_url = String.format("http://%s:8088/getSatelliteData/%x",url,id);
        String launch_url = String.format("http://%s:8089/getLaunchDataById/%x",url2,id);
        satellite_data = restTemplate.exchange(satellite_url, HttpMethod.GET, entity, String.class).getBody();
        launch_data = restTemplate.exchange(launch_url, HttpMethod.GET, entity, String.class).getBody();
        return(satellite_data + " " + launch_data);
    }

}

