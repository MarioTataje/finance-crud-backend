package com.financecrudbackend.services;

import com.financecrudbackend.models.FiscalPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FiscalPositionService {
    Page<FiscalPosition> getAll(Pageable pageable);
    FiscalPosition getById(Long id);
    FiscalPosition save(FiscalPosition fiscalPosition);
    FiscalPosition update(Long id, FiscalPosition fiscalPosition);
    void delete(Long id);
}