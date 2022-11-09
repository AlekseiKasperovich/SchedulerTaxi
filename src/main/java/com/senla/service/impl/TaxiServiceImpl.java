package com.senla.service.impl;

import com.senla.client.TaxiApiClient;
import com.senla.model.Coordinate;
import com.senla.model.MomentPrice;
import com.senla.model.Price;
import com.senla.properties.YandexProperties;
import com.senla.repository.PriceRepository;
import com.senla.service.TaxiService;
import io.micrometer.core.instrument.MeterRegistry;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class TaxiServiceImpl implements TaxiService {

    private final YandexProperties yandexProperties;
    private final TaxiApiClient taxiApiClient;
    private final PriceRepository priceRepository;
    private AtomicInteger price;

    public TaxiServiceImpl(
            YandexProperties yandexProperties,
            TaxiApiClient taxiApiClient,
            PriceRepository priceRepository,
            MeterRegistry registry) {
        this.yandexProperties = yandexProperties;
        this.taxiApiClient = taxiApiClient;
        this.priceRepository = priceRepository;
        price = new AtomicInteger();
        registry.gauge("priceTaxi", price);
            }

    @Override
    public void getPrice(Coordinate startPoint, Coordinate endPoint) {
        String rll = startPoint.toString() + "~" + endPoint.toString();
        String clid = yandexProperties.getClid();
        String apiKey = yandexProperties.getApiKey();

        Price currentPrice = taxiApiClient.getPrice(clid, apiKey, rll);
        if (currentPrice.getOptions().isEmpty()) {
            throw new RuntimeException("Options are empty");
        }
        double priceDouble = currentPrice.getOptions().get(0).getPrice();
        price.set((int) priceDouble);
        MomentPrice momentPrice = new MomentPrice(LocalDateTime.now(), priceDouble);
        priceRepository.save(momentPrice);
    }

    @Override
    public List<MomentPrice> getAllPrice() {
        return priceRepository.findAll();
    }
}
