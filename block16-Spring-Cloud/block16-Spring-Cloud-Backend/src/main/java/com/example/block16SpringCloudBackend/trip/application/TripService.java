package com.example.block16SpringCloudBackend.trip.application;

import com.example.block16SpringCloudBackend.trip.infrastructure.dto.input.TripInputDTO;
import com.example.block16SpringCloudBackend.trip.infrastructure.dto.output.TripOutputDTO;

import java.util.List;


public interface TripService {

    TripOutputDTO addTrip(TripInputDTO tripInputDTO);
    List<TripOutputDTO> getAllTrip();
    TripOutputDTO getTripById(Integer id);
    TripOutputDTO updateTrip(Integer id, TripInputDTO tripInputDTO);
    void deleteTrip(Integer id);
    public Integer countPassengers(Integer trip_id);
    public TripOutputDTO addPassenger(Integer idTrip, Integer idClient);
    TripOutputDTO changeStatus(Integer idTrip, Boolean status);
    public boolean checkStatus(Integer idTrip);
}
