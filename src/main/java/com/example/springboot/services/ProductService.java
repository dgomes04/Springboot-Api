package com.example.springboot.services;

import com.example.springboot.dtos.AddProductDTO;
import com.example.springboot.dtos.UpdateProductDTO;
import com.example.springboot.entities.Product;
import com.example.springboot.exceptions.ModelNotFoundException;
import com.example.springboot.repositories.IProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Service
public class ProductService implements IProductService{
    @Autowired
    IProductRepository productRepository;
    public Product add(AddProductDTO model) {
        var entity = new Product();
        BeanUtils.copyProperties(model, entity);
        entity.setCreatedAt(LocalDateTime.now());
        return productRepository.save(entity);
    }
    public List<Product> getAll(){
        return productRepository.findAll();
    }
    public Product getOne(UUID id) throws ModelNotFoundException{
        var productO = productRepository.findById(id);
        if(productO.isPresent()){
            if(productO.get().getDeletedAt() == null){
                return productO.get();
            }
        }
        throw new ModelNotFoundException(String.format("Produto não encontrado: Id: %s", id));
    }
    @Override
    public Product partialUpdate(UUID id, UpdateProductDTO model) throws ModelNotFoundException {
        var product = this.getOne(id);
        product.setUpdatedAt(LocalDateTime.now());
        if(model.name() != null)
            product.setName(model.name());
        if(model.value() != null)
            product.setValue(model.value());
        return productRepository.save(product);
    }
    @Override
    public Product completeUpdate(UUID id, AddProductDTO model) throws ModelNotFoundException {
        var product = this.getOne(id);
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }


    @Override
    public String delete(UUID id) throws ModelNotFoundException {
        var product = this.getOne(id);
        product.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);
        return String.format("Produto excluído com sucesso! Id: %s", id);
    }

}
