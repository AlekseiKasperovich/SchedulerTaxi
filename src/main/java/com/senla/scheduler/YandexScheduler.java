package com.senla.scheduler;

import com.senla.model.Coordinate;
import com.senla.properties.CoordinateProperties;
import com.senla.service.TaxiService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YandexScheduler {

    private final CoordinateProperties coordinateProperties;
    private final TaxiService taxiService;

    @Timed("gettingPriceScheduler")
    @Scheduled(fixedDelay = 30_000)
    public void updatePrice() {
        Coordinate startPoint =
                new Coordinate(
                        coordinateProperties.getStartLongitude(),
                        coordinateProperties.getStartLatitude());
        Coordinate endPoint =
                new Coordinate(
                        coordinateProperties.getFinishLongitude(),
                        coordinateProperties.getFinishLatitude());
        taxiService.getPrice(startPoint, endPoint);
    }
}
