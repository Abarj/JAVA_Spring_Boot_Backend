package com.example.block16SpringCloudBackend.trip.infrastructure.controller;

import com.example.block16SpringCloudBackend.trip.application.TripService;
import com.example.block16SpringCloudBackend.trip.infrastructure.dto.input.TripInputDTO;
import com.example.block16SpringCloudBackend.trip.infrastructure.dto.output.TripOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    TripService tripService;

    @PostMapping
    public TripOutputDTO addTrip(@RequestBody TripInputDTO tripInputDTO) {
        return tripService.addTrip(tripInputDTO);
    }

    @GetMapping
    public List<TripOutputDTO> findAll() {
        return tripService.getAllTrip();
    }

    @GetMapping("/{id}")
    public TripOutputDTO getTripById(@PathVariable Integer id) {
        return tripService.getTripById(id);
    }

    @GetMapping("/passenger/count/{id}")
    public ResponseEntity<Integer> countPassengers(@PathVariable Integer id){
        return new ResponseEntity<>(tripService.countPassengers(id), HttpStatus.OK);
    }

    @GetMapping("/status/{trip_id}/{status}")
    public TripOutputDTO changeStatus(@PathVariable Integer trip_id, @PathVariable Boolean status){
        return tripService.changeStatus(trip_id, status);
    }

    @GetMapping("/verify/{idTrip}")
    public ResponseEntity<?> checkStatus(@PathVariable("idTrip") Integer idTrip){
        return ResponseEntity.ok().body(tripService.checkStatus(idTrip));
    }

    @PutMapping("/{id}")
    public TripOutputDTO updateTrip(@PathVariable Integer id, @RequestBody TripInputDTO tripInputDTO) {
        return tripService.updateTrip(id, tripInputDTO);
    }

    @PutMapping("/addPassenger/{idTrip}/{idClient}")
    public TripOutputDTO addPassenger(@PathVariable Integer idTrip,@PathVariable Integer idClient){
        return tripService.addPassenger(idTrip, idClient);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Integer id) {
        tripService.deleteTrip(id);
    }
}
