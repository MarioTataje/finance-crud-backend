package com.financecrudbackend.controllers;

import com.financecrudbackend.models.FiscalPosition;
import com.financecrudbackend.resources.FiscalPositionResource;
import com.financecrudbackend.resources.SaveFiscalPositionResource;
import com.financecrudbackend.services.FiscalPositionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class FiscalPositionController {
    private final FiscalPositionService fiscalPositionService;
    private final ModelMapper mapper;

    @Autowired
    public FiscalPositionController(FiscalPositionService fiscalPositionService, ModelMapper mapper){
        this.fiscalPositionService = fiscalPositionService;
        this.mapper = mapper;
    }

    @GetMapping("/fiscal-positions")
    public ResponseEntity<Page<FiscalPositionResource>> getAllFiscalPosition(Pageable pageable) {
        try {
            Page<FiscalPosition> fiscalPositionPage = fiscalPositionService.getAll(pageable);
            if (fiscalPositionPage.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<FiscalPositionResource> fiscalPositionsResources = fiscalPositionPage.getContent()
                    .stream()
                    .map(this::convertToResource)
                    .collect(Collectors.toList());
            Page<FiscalPositionResource> resources = new PageImpl<>(fiscalPositionsResources, pageable, fiscalPositionsResources.size());
            return new ResponseEntity<>(resources, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fiscal-positions/{id}")
    public ResponseEntity<FiscalPositionResource> getFiscalPositionById(@PathVariable Long id) {
        FiscalPosition fiscalPosition = fiscalPositionService.getById(id);
        return new ResponseEntity<>(convertToResource(fiscalPosition), HttpStatus.OK);
    }

    @PostMapping("/fiscal-positions")
    public ResponseEntity<FiscalPositionResource> saveFiscalPosition
            (@RequestBody SaveFiscalPositionResource fiscalPosition){
        try {
            FiscalPosition created = fiscalPositionService.save(convertToEntity(fiscalPosition));
            return new ResponseEntity<>(convertToResource(created), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public FiscalPositionResource convertToResource(FiscalPosition entity) {
        return mapper.map(entity, FiscalPositionResource.class);
    }

    public FiscalPosition convertToEntity(SaveFiscalPositionResource resource) {
        return mapper.map(resource, FiscalPosition.class);
    }
}


