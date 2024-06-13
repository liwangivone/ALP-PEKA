package com.group1.peka.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.peka.models.entities.ShipOperation;
import com.group1.peka.models.repositories.ShipOperationRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShipOperationService {

    @Autowired
    private ShipOperationRepo shipOperationRepo;

    public Optional<ShipOperation> getShipOperationbyID(int id) {
        return shipOperationRepo.findById(id);
    }
}
