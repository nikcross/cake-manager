package com.waracle.cakemgr.service;

import com.waracle.cakemgr.model.Cake;

import java.util.List;

public interface CakeService {
    Cake addCake(Cake cake);
    List<Cake> listCakes();
}
