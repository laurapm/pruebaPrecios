package com.klagan.pruebaprecios.service.implementation;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klagan.pruebaprecios.entity.Price;
import com.klagan.pruebaprecios.repository.PricesRepository;
import com.klagan.pruebaprecios.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PricesRepository pricesRepository;

    public Optional<Price> calculateFinalPrice(LocalDateTime applicationDate, Long productID, Long brandID) {
        // Look in the repository a price with given product id and brand id
        List<Price> priceWithBrandIdAndProductId = pricesRepository.findByBrandBrandIdAndProductProductId(brandID,
                productID);

        // If theres no price with those id return null
        if (priceWithBrandIdAndProductId.isEmpty()) {
            return Optional.empty();
        }

        // Otherwise, get prices that belongs to the application date
        List<Price> filteredPrice = priceWithBrandIdAndProductId.stream()
                .filter(price -> (price.getStartDate().isBefore(applicationDate)
                        || price.getStartDate().isEqual(applicationDate))
                        && (price.getEndDate().isAfter(applicationDate)
                                || price.getEndDate().isEqual(applicationDate)))
                .collect(Collectors.toList());

        // If there is no price that belongs to the date return null
        if (filteredPrice == null || filteredPrice.isEmpty()) {
            return Optional.empty();
        }

        // Otherwise, reorder the result with the highest priority
        Optional<Price> priceWithHighestPriority = filteredPrice.stream()
                .max(Comparator.comparingInt(Price::getPriority));

        // Protection code
        if (!priceWithHighestPriority.isPresent()) {
            return Optional.empty();
        }

        return priceWithHighestPriority;
    }

}
