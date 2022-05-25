package com.luis.mongoproject.controller;
import com.luis.mongoproject.model.Store;
import com.luis.mongoproject.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public Store getStoreById(@PathVariable String id) {
        return storeService.getStoreById(id);
    }

    @GetMapping("/email/{email}")
    public Store getStoreByEmail(@PathVariable String email) {
        return storeService.getStoreByEmail(email);
    }

    @PostMapping
    public List<Store> addStores(@RequestBody List<Store> storeList) {
        return storeService.addStores(storeList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
       if(!storeService.existsById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       storeService.deleteById(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public Page<Store> findStoresBySearchText(
            @RequestParam(required = false, defaultValue = "") String searchText,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "1") Integer pageSize) {

        Page<Store> searchResults;
        if(searchText.isEmpty()) {
            Pageable pageable = PageRequest.of(page, pageSize);
            searchResults = storeService.getAllStores(pageable);
        } else {
            Sort sort = Sort.by("score");
            Pageable pageable = PageRequest.of(page, pageSize, sort);
            TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(searchText);

            searchResults = storeService.getAllStores(criteria, pageable);
        }
        return searchResults;
    }
}
