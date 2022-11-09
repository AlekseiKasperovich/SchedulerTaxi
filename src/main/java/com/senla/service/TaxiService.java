package com.senla.service;

import com.senla.model.Coordinate;
import com.senla.model.MomentPrice;
import java.util.List;

public interface TaxiService {

    void getPrice(Coordinate startPoint, Coordinate endPoint);

    List<MomentPrice> getAllPrice();
}
