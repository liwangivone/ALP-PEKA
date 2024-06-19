package com.group1.peka.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.peka.models.entities.Destination;
import com.group1.peka.models.repositories.DestinationRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DestinationService {

    @Autowired
    private DestinationRepo destinationRepo;

    public Destination createDestination(String destinationName) {
        Optional<Destination> nameCheck = destinationRepo.findByDestinationName(destinationName);

        if (nameCheck.isPresent()) {
            return null;
        }

        Destination destination = new Destination('0', destinationName);

        return destinationRepo.save(destination);
    }

    public Optional<Destination> getDestinationByID(int destinationID) {
        return destinationRepo.findByDestinationID(destinationID);
    }

    public Optional<Destination> getDestinationByDestinationName(String destinationName) {
        return destinationRepo.findByDestinationName(destinationName);
    }

    public List<Destination> getAllDestinations() {
        return (List<Destination>) destinationRepo.findAll();
    }

    public Destination updateDestination(Destination destination) {
        return destinationRepo.save(destination);
    }

    public boolean deleteDestinationByIdAndName(int destinationID, String destinationName) {
        Optional<Destination> destination = destinationRepo.findById(destinationID);
        if (destination.isPresent() && destination.get().getDestinationName().equals(destinationName)) {
            destinationRepo.delete(destination.get());
            return true;
        }
        return false;
    }
}



    

