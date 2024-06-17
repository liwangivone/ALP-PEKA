package com.group1.peka.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.peka.models.entities.Origin;
import com.group1.peka.models.repositories.OriginRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OriginService {

    @Autowired
    private OriginRepo originRepo;

    public Origin createOrigin(String originName) {
        Origin origin = new Origin(originName);
        return originRepo.save(origin);
    }

    public Optional<Origin> getOriginByID(int originID) {
        return originRepo.findById(originID);
    }
    
    public Iterable<Origin> getAllOrigins() {
        return originRepo.findAll();
    }
}
