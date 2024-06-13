package com.group1.peka.services;

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

    public Destination createDestination(Destination destination) {
        return destinationRepo.save(destination);
    }

    public Destination updateDestination(int id, Destination destination) {
        destination.setId(id);
        return destinationRepo.save(destination);
    }

    public void deleteDestination(int id) {
        destinationRepo.deleteById(id);
    }

    public Optional<Destination> getDestinationById(int id) {
        return destinationRepo.findById(id);
    }

    public Iterable<Destination> getAllDestinations() {
        return destinationRepo.findAll();
    }
}

