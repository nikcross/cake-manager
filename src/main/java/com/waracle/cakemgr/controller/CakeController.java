package com.waracle.cakemgr.controller;

import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.service.CakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( value="/cakes" )
public class CakeController {

    private CakeService cakeService;

    @Autowired
    public CakeController(CakeService cakeService) {
        this.cakeService = cakeService;
    }

    /**
     * List all cakes.
     *
     * @return list of cakes
     */
    @GetMapping
    @CrossOrigin( value = "*" )
    public List<Cake> listCakes() {
        return cakeService.listCakes();
    }

    /**
     * Add a cake to the list of cakes
     *
     * @param cake
     * @return the new list of cakes
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    @CrossOrigin( value = "*" )
    public Cake addCake(@RequestBody Cake cake ) {
        return cakeService.addCake( cake );
    }
}
