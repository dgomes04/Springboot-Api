package com.example.springboot.controllers;

import com.example.springboot.dtos.*;
import com.example.springboot.entities.Product;
import com.example.springboot.exceptions.*;
import com.example.springboot.models.base.BaseResponse;
import com.example.springboot.services.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IProductService productService;

    @PostMapping("/")
    public ResponseEntity<BaseResponse<Product>> saveProduct(@RequestBody @Valid AddProductDTO productRecordDto){
        try{
            var response = new BaseResponse<Product>();
            response.setResult(productService.add(productRecordDto));

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch(Exception e){
            var response = new BaseResponse<Product>();
            response.setError(true);
            response.setErrorMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @GetMapping("/")
    public ResponseEntity<BaseResponse<List<Product>>> findAll(){
        try {
            var response = new BaseResponse<List<Product>>();
            response.setResult(productService.getAll());

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception e){
            var response = new BaseResponse<List<Product>>();
            response.setError(true);
            response.setErrorMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Product>> getOne(@PathVariable(value = "id") UUID id){
        try {
            var response = new BaseResponse<Product>();
            var result = productService.getOne(id);
            response.setResult(result);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(ModelNotFoundException e){
            var response = new BaseResponse<Product>();
            response.setError(true);
            response.setErrorMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e){
            var response = new BaseResponse<Product>();
            response.setError(true);
            response.setErrorMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse<Product>> partialUpdateProduct (@PathVariable(value="id")   UUID id,
                                                                @RequestBody @Valid UpdateProductDTO model){
        try {
            var response = new BaseResponse<Product>();
            var result = productService.partialUpdate(id, model);
            response.setResult(result);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(ModelNotFoundException e){
            var response = new BaseResponse<Product>();
            response.setError(true);
            response.setErrorMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e){
            var response = new BaseResponse<Product>();
            response.setError(true);
            response.setErrorMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Product>> completeUpdateProduct (@PathVariable(value="id")   UUID id,
                                                                @RequestBody @Valid AddProductDTO model){
        try {
            var response = new BaseResponse<Product>();
            var result = productService.completeUpdate(id, model);
            response.setResult(result);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(ModelNotFoundException e){
            var response = new BaseResponse<Product>();
            response.setError(true);
            response.setErrorMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e){
            var response = new BaseResponse<Product>();
            response.setError(true);
            response.setErrorMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<String>> delete(@PathVariable(value = "id")UUID id){
        try {
            var response = new BaseResponse<String>();
            var result = productService.delete(id);
            response.setResult(result);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch(ModelNotFoundException e){
            var response = new BaseResponse<String>();
            response.setError(true);
            response.setErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e){
            var response = new BaseResponse<String>();
            response.setError(true);
            response.setErrorMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
