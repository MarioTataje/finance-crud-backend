package com.financecrudbackend.services.impl;

import com.financecrudbackend.exception.NotFoundException;
import com.financecrudbackend.models.FiscalPosition;
import com.financecrudbackend.repositories.FiscalPositionRepository;
import com.financecrudbackend.services.FiscalPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FiscalPositionServiceImpl implements FiscalPositionService {

    private final FiscalPositionRepository fiscalPositionRepository;

    @Autowired
    public FiscalPositionServiceImpl(FiscalPositionRepository fiscalPositionRepository) {
        this.fiscalPositionRepository = fiscalPositionRepository;
    }

    @Override
    public Page<FiscalPosition> getAll(Pageable pageable) {
        return fiscalPositionRepository.findAll(pageable);
    }

    @Override
    public FiscalPosition getById(Long id) {
        return fiscalPositionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("FiscalPosition", "id", id));
    }

    @Override
    public FiscalPosition save(FiscalPosition fiscalPosition) {
        return fiscalPositionRepository.save(fiscalPosition);
    }

    @Override
    public FiscalPosition update(Long id, FiscalPosition fiscalPosition) {
        FiscalPosition founded = fiscalPositionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("FiscalPosition", "id", id));
        founded.setYearOfBalance(fiscalPosition.getYearOfBalance());
        founded.setState(fiscalPosition.getState());
        founded.setCategory(fiscalPosition.getCategory());
        founded.setItem(fiscalPosition.getItem());
        founded.setAmount(fiscalPosition.getAmount());
        founded.setPercentOfGdp(fiscalPosition.getPercentOfGdp());
        return fiscalPositionRepository.save(founded);
    }

    @Override
    public void delete(Long id) {
        FiscalPosition founded = fiscalPositionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("FiscalPosition", "id", id));
        fiscalPositionRepository.delete(founded);
    }
}
