package com.luis.mongoproject.service;

import com.luis.mongoproject.exception.ResourceNotFoundException;
import com.luis.mongoproject.model.Store;
import com.luis.mongoproject.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public List<Store> addStores(List<Store> storeList){
        return storeRepository.saveAll(storeList);
    }

    public Store getStoreById(String id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store", "id", id));
    }

    public Store getStoreByEmail(String email) {
        return storeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Store", "email", email));
    }


    public boolean existsById(String id) {
        return storeRepository.existsById(id);
    }

    public void deleteById(String id) {
        storeRepository.deleteById(id);
    }

    public Page<Store> getAllStores(Pageable pageable) {
        return storeRepository.findAll(pageable);
    }

    public Page<Store> getAllStores(TextCriteria criteria, Pageable pageable) {
        return storeRepository.findAllBy(criteria,pageable);
    }

    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }
}
