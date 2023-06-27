package com.example.block6personcontrollers1.application;

import com.example.block6personcontrollers1.domains.Ciudad;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CiudadService {

    @PostConstruct
    public void init() {
        ciudades = new ArrayList<>();
        ciudades.add(new Ciudad("Madrid", 1000000));
        ciudades.add(new Ciudad("Barcelona", 500000));
        ciudades.add(new Ciudad("Logroño", 200000));
    }

    private List<Ciudad> ciudades;

    public CiudadService() {
        ciudades = new ArrayList<>();
    }

    public void agregarCiudad(Ciudad ciudad) {
        ciudades.add(ciudad);
    }

    public List<Ciudad> obtenerCiudades() {
        return ciudades;
    }
}
