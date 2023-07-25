package com.example.block16SpringCloudBackend.client.feign;

import com.example.block16SpringCloudBackend.trip.infrastructure.dto.output.TripOutputDTO;

import com.example.block16SpringCloudBackend.client.infrastructure.dto.output.ClientOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="block16-Spring-Cloud-Backend", url = "http://localhost:8080/")
public interface FeignBack {
    @GetMapping(value = "client/{id}")
    public ClientOutputDTO showById(@PathVariable("id") Integer idClient);

    @GetMapping("client/getall")
    public List<ClientOutputDTO> findall();

    @GetMapping("trip/{id}")
    public TripOutputDTO getTripById(@PathVariable Integer id);



}