package com.example.springboot.services;


import com.example.springboot.dtos.AddProductDTO;
import com.example.springboot.dtos.UpdateProductDTO;
import com.example.springboot.entities.Product;
import com.example.springboot.exceptions.ModelNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IProductService {
    Product add(AddProductDTO model);
    List<Product> getAll();
    Product getOne(UUID id) throws ModelNotFoundException;
    Product partialUpdate(UUID id, UpdateProductDTO model) throws ModelNotFoundException;
    Product completeUpdate(UUID id, AddProductDTO model) throws ModelNotFoundException;
    String delete(UUID id) throws ModelNotFoundException;
}
