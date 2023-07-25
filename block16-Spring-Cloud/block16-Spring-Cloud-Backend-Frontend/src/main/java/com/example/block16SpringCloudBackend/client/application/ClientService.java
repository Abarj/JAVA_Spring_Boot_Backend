package com.example.block16SpringCloudBackend.client.application;

import com.example.block16SpringCloudBackend.client.feign.FeignBack;
import com.example.block16SpringCloudBackend.client.infrastructure.dto.output.ClientOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService  {
    @Autowired
    FeignBack feignBack;

    public ClientOutputDTO getClientById(Integer id) {
        return feignBack.showById(id);
    }

    public List<ClientOutputDTO> getPassenger(){
        return feignBack.findall();
    }
}
