package com.nss.kofekaknado.repository;

import com.nss.kofekaknado.domain.CoffeePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeePointRepository extends JpaRepository<CoffeePoint, Integer> {
}
