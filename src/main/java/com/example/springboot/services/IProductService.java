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
    public Product add(AddProductDTO model);
    public List<Product> getAll();
    public Product getOne(UUID id) throws ModelNotFoundException;
    public Product update(UUID id, UpdateProductDTO model) throws ModelNotFoundException;
    public String delete(UUID id) throws ModelNotFoundException;
}
