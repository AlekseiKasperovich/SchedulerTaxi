package com.senla.controller;

import com.senla.model.MomentPrice;
import com.senla.service.TaxiService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController {

    private final TaxiService taxiService;

    @GetMapping("/prices")
    public List<MomentPrice> getAllPrice() {
        return taxiService.getAllPrice();
    }
}
