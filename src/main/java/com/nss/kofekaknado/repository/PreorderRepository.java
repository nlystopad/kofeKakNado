package com.nss.kofekaknado.repository;

import com.nss.kofekaknado.domain.Preorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreorderRepository extends JpaRepository<Preorder, Integer> {
}
