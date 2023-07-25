package com.example.block16SpringCloudBackend.trip.application;

import com.example.block16SpringCloudBackend.client.domain.Client;
import com.example.block16SpringCloudBackend.client.infrastructure.repository.ClientRepository;
import com.example.block16SpringCloudBackend.trip.domain.Trip;
import com.example.block16SpringCloudBackend.trip.infrastructure.dto.input.TripInputDTO;
import com.example.block16SpringCloudBackend.trip.infrastructure.dto.output.TripOutputDTO;
import com.example.block16SpringCloudBackend.trip.infrastructure.repository.TripRepository;
import com.example.block16SpringCloudBackend.utils.exceptions.CustomUnprocessableException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class TripServiceImpl implements TripService {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public TripOutputDTO addTrip(TripInputDTO tripInputDTO) {
        Trip trip = new Trip(tripInputDTO);
        tripRepository.save(trip);

        return new TripOutputDTO(trip);
    }

    @Override
    public List<TripOutputDTO> getAllTrip() {
        List<Trip> trips = tripRepository.findAll();
        List<TripOutputDTO> tripOutputDTOS = new ArrayList<>();

        for (Trip trip : trips) {
            tripOutputDTOS.add(new TripOutputDTO(trip));
        }

        return tripOutputDTOS;
    }

    @Override
    public TripOutputDTO getTripById(Integer id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new com.example.block16SpringCloudBackend.utils.exceptions.CustomUnprocessableException("No se ha encontrado ningún cliente con el id: " + id));

        return new TripOutputDTO(trip);
    }

    @Override
    public TripOutputDTO updateTrip(Integer id, TripInputDTO tripInputDTO) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new com.example.block16SpringCloudBackend.utils.exceptions.CustomUnprocessableException("No se ha encontrado ningún cliente con el id: " + id));
        trip.update(tripInputDTO);
        tripRepository.save(trip);

        return new TripOutputDTO(trip);
    }

    @Override
    public void deleteTrip(Integer id) {
        tripRepository.deleteById(id);
    }

    @Override
    public Integer countPassengers(Integer trip_id) {
        Trip trip = tripRepository.findById(trip_id)
                .orElseThrow(()->new EntityNotFoundException("No trips found with id: " + trip_id));
        return trip.getPassengers().size();
    }

    @Override
    public TripOutputDTO addPassenger(Integer idTrip, Integer idClient) {
        Trip trip = tripRepository.findById(idTrip)
                .orElseThrow(()->new EntityNotFoundException("Trip does not exist"));
        if(trip.getPassengers().size() == 40) {
            throw new CustomUnprocessableException("No passenger seats available");
        }
        if(trip.getPassengers().stream().filter(client-> Objects.equals(client.getIdClient(), idClient)).toList().size() == 1){
            throw new CustomUnprocessableException("Passenger already exists within the trip");
        }

        Client client = clientRepository.findById(idClient)
                .orElseThrow(()->new CustomUnprocessableException("Customer does not exist"));

        trip.getPassengers().add(client);
        tripRepository.save(trip);

        return new TripOutputDTO(trip);
    }

    @Override
    public TripOutputDTO changeStatus(Integer idTrip, Boolean status) {
        Trip trip = tripRepository.findById(idTrip).orElseThrow(()->new EntityNotFoundException("Trip does not exist"));
        trip.setStatus(status);
        tripRepository.save(trip);
        return new TripOutputDTO(trip);
    }

    @Override
    public boolean checkStatus(Integer idTrip) {
        return tripRepository.findById(idTrip).orElseThrow(()->new EntityNotFoundException("Trip does not exist"))
                .isStatus();
    }
}
