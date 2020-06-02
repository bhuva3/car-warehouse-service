package com.sample.cws.Repositories;

import com.sample.cws.domains.Warehouse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan
public interface CarWarehouseRepository {

        Warehouse findByVehicleId(Long vehicleId);

        List<Warehouse> getAllVehicleDetails();

        List<Warehouse> getVehicleDetailsWithPagination(int index, int offset);

}
