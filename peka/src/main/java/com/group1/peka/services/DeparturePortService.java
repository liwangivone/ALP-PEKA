package com.group1.peka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group1.peka.models.repositories.DeparturePortRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DeparturePortService {
    
    @Autowired
    private DeparturePortRepo departurePortRepo;
}
