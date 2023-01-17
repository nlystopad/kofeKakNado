package com.nss.kofekaknado.repository;

import com.nss.kofekaknado.domain.PlanEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanEventRepository extends JpaRepository<PlanEvent, Integer> {
}
