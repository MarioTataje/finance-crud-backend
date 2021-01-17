package com.financecrudbackend.controllers;

import com.financecrudbackend.models.FiscalPosition;
import com.financecrudbackend.resources.FiscalPositionResource;
import com.financecrudbackend.resources.MessageResource;
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

    //Method that allows to retrieve all fiscal positions instances
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

    //Method that allows to retrieve a fiscal position by the id
    @GetMapping("/fiscal-positions/{id}")
    public ResponseEntity<FiscalPositionResource> getFiscalPositionById(@PathVariable Long id) {
        FiscalPosition fiscalPosition = fiscalPositionService.getById(id);
        return new ResponseEntity<>(convertToResource(fiscalPosition), HttpStatus.OK);
    }

    //Method that allows to save a fiscal position instance
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

    //Method that allows to update a fiscal position instance
    @PutMapping("/fiscal-positions/{id}")
    public ResponseEntity<FiscalPositionResource> updateFiscalPosition(
            @PathVariable Long id,
            @RequestBody SaveFiscalPositionResource fiscalPosition){
        FiscalPosition updated = fiscalPositionService.update(id, convertToEntity(fiscalPosition));
        return new ResponseEntity<>(convertToResource(updated), HttpStatus.OK);
    }

    //Method that allows to delete a fiscal position instance
    @DeleteMapping("/fiscal-positions/{id}")
    public ResponseEntity<MessageResource> deleteFiscalPosition(@PathVariable Long id){
        fiscalPositionService.delete(id);
        return new ResponseEntity<>(new MessageResource("Deleted Successfully"), HttpStatus.OK);
    }

    //Convert from entity to resource
    public FiscalPositionResource convertToResource(FiscalPosition entity) {
        return mapper.map(entity, FiscalPositionResource.class);
    }

    /*
    In case that the yearOfBalance of the SaveFiscalPositionResource class is a Number use this to convert to Year
    @PostConstruct
    public void init(){
        Converter<Short, Year> toYear = source -> source.getSource() == null ? null : Year.of(source.getSource());
        mapper.typeMap(SaveFiscalPositionResource.class, FiscalPosition.class)
                .addMappings(mapper -> mapper.using(toYear)
                       .map(SaveFiscalPositionResource::getYearOfBalance, FiscalPosition::setYearOfBalance));
    }
    */

    //Convert from resource to entity
    public FiscalPosition convertToEntity(SaveFiscalPositionResource resource) {
        return mapper.map(resource, FiscalPosition.class);
    }
}


