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
        Optional<Origin> nameCheck = originRepo.findByOriginName(originName);

        if (nameCheck.isPresent()) {
            return null;
        }

        Origin origin = new Origin('0', originName);

        return originRepo.save(origin);
    }

    public Optional<Origin> getOriginByID(int originID) {
        return originRepo.findByOriginID(originID);
    }

    public Optional<Origin> getOriginByOriginName(String originName) {
        return originRepo.findByOriginName(originName);
    }
    
    public Iterable<Origin> getAllOrigins() {
        return originRepo.findAll();
    }

    public Origin updateOrigin(Origin origin) {
        return originRepo.save(origin);
    }

    public boolean deleteOriginByIdAndName(int originID, String originName) {
        Optional<Origin> origin = originRepo.findById(originID);
        if (origin.isPresent() && origin.get().getOriginName().equals(originName)) {
            originRepo.delete(origin.get());
            return true;
        }
        return false;
    }
}
