package com.senla.service;

import com.senla.model.Coordinate;

public interface TaxiService {
    void getPrice(Coordinate startPoint, Coordinate endPoint);
}
