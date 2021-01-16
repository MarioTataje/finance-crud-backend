package com.financecrudbackend.repositories;

import com.financecrudbackend.models.FiscalPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiscalPositionRepository extends JpaRepository<FiscalPosition, Long> {

}
