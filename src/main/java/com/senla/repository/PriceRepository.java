package com.senla.repository;

import com.senla.model.MomentPrice;
import io.micrometer.core.annotation.Timed;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends CrudRepository<MomentPrice, Long> {

    @Timed("gettingPricesFromDB")
    List<MomentPrice> findAll();
}
