package com.waracle.cakemgr.repository;

import com.waracle.cakemgr.model.Cake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CakeRepository extends JpaRepository<Cake, Integer> {
}
