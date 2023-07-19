package com.klagan.pruebaprecios.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klagan.pruebaprecios.DTO.PricesModel;
import com.klagan.pruebaprecios.DTO.RequestPriceProduct;
import com.klagan.pruebaprecios.entity.Price;
import com.klagan.pruebaprecios.handler.ResponseHandler;
import com.klagan.pruebaprecios.service.implementation.PriceServiceImpl;

@RestController
@RequestMapping("e-commerce/prices")
public class PricesController {

    @Autowired
    private PriceServiceImpl priceService;

    @PostMapping("")
    public ResponseEntity<Object> getPriceForProductAndDay(@RequestBody RequestPriceProduct requestForm) {
        if (requestForm == null || requestForm == new RequestPriceProduct()) {
            return ResponseHandler.generateResponse("Cannot calculate price without data", HttpStatus.BAD_REQUEST,
                    null);
        } else if (requestForm.getApplicationDate() == null) {
            return ResponseHandler.generateResponse("Apllication date cannot be null", HttpStatus.BAD_REQUEST, null);
        } else if (requestForm.getProductId() == null) {
            return ResponseHandler.generateResponse("Product id cannot be null", HttpStatus.BAD_REQUEST, null);
        } else if (requestForm.getBrandId() == null) {
            return ResponseHandler.generateResponse("Brand id date cannot be null", HttpStatus.BAD_REQUEST, null);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(requestForm.getApplicationDate(), formatter);
            Optional<Price> prices = priceService.calculateFinalPrice(dateTime,
                    requestForm.getProductId(), requestForm.getBrandId());
            if (!prices.isPresent()) {
                return ResponseHandler.generateResponse("Cannot calculate price with the given data!",
                        HttpStatus.NOT_FOUND, null);
            } else {
                // Create object to response
                PricesModel result = new PricesModel();
                result.setProductId(prices.get().getProduct().getProductId());
                result.setChainId(prices.get().getBrand().getBrandId());
                result.setRateToApply(prices.get().getPriority());
                result.setStartDate(prices.get().getStartDate());
                result.setEndDate(prices.get().getEndDate());
                String priceValue = String.valueOf(prices.get().getPrice()) + ""
                        + prices.get().getCurrency();
                result.setPrice(priceValue);

                return ResponseHandler.generateResponse("Price succesfully calculated!", HttpStatus.OK, result);
            }
        }
    }
}
