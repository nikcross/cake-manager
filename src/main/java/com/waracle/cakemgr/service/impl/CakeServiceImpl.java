package com.waracle.cakemgr.service.impl;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import com.waracle.cakemgr.service.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing cake
 */
@Service
public class CakeServiceImpl implements CakeService {

    private CakeRepository cakeRepository;

    @Autowired
    public CakeServiceImpl(CakeRepository cakeRepository ) {
        this.cakeRepository = cakeRepository;
    }

    /**
     * Get a list of all cakes
     *
     * @return a list of cakes
     */
    public List<Cake> listCakes() {
        return cakeRepository.findAll();
    }

    /**
     * Add a cake to the list
     *
     * @param cake
     * @return the saved cake
     */
    public Cake addCake( Cake cake ) {
        return cakeRepository.saveAndFlush( cake );
    }
}
