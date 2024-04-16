package com.example.demo.rest;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Item;
import com.example.demo.entity.Status;
import com.example.demo.service.ItemService;

public abstract class ItemController<E extends Item, D> implements GenericController<E, D, Integer> {
    
    @Override
    public abstract ItemService<E, D> getService();

    public ResponseEntity<D> updateStatus(Integer id, String status) {
        return ResponseEntity.ok(getService().updateStatus(id, Status.valueOf(status)));
    }
}
