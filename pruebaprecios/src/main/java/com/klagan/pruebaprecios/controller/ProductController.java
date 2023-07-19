package com.klagan.pruebaprecios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.klagan.pruebaprecios.entity.Product;
import com.klagan.pruebaprecios.handler.ResponseHandler;
import com.klagan.pruebaprecios.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Object> getAllProducts() {
        List<Product> products = productService.getAllProduct();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "UserController");

        return ResponseHandler.generateResponse("Successfully Retrieved data!", HttpStatus.OK, products);
    }

}
