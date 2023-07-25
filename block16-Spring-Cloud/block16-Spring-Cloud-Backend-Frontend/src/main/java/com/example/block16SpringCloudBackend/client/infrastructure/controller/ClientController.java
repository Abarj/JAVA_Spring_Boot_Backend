package com.example.block16SpringCloudBackend.client.infrastructure.controller;

import com.example.block16SpringCloudBackend.client.feign.FeignBack;
import com.example.block16SpringCloudBackend.client.infrastructure.dto.output.ClientOutputDTO;
import com.example.block16SpringCloudBackend.trip.infrastructure.dto.output.TripOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    FeignBack feignBack;

    @GetMapping("/client/{id}")
    public ClientOutputDTO getClientById(@PathVariable("id") Integer id){
        return feignBack.showById(id);
    }
    @GetMapping("/getall")
    public List<ClientOutputDTO> findall(){
        return feignBack.findall();
    }

    @GetMapping("/trip/{id}")
    public TripOutputDTO getTripById(@PathVariable Integer id){
        return feignBack.getTripById(id);
    }
}
