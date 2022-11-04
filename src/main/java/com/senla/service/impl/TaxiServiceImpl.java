package com.senla.service.impl;

import com.senla.client.TaxiApiClient;
import com.senla.model.Coordinate;
import com.senla.model.Price;
import com.senla.properties.YandexProperties;
import com.senla.service.TaxiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaxiServiceImpl implements TaxiService {

    private final YandexProperties yandexProperties;
    private final TaxiApiClient taxiApiClient;

    @Override
    public void getPrice(Coordinate startPoint, Coordinate endPoint) {
        String rll = startPoint.toString() + "~" + endPoint.toString();
        String clid = yandexProperties.getClid();
        String apiKey = yandexProperties.getApiKey();

        Price currentPrice = taxiApiClient.getPrice(clid, apiKey, rll);
    }
}
