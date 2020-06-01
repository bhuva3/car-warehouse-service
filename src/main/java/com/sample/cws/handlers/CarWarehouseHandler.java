package com.sample.cws.handlers;

import com.sample.cws.domains.Car;
import com.sample.cws.domains.Vehicle;
import com.sample.cws.domains.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarWarehouseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarWarehouseHandler.class);

    public CarWarehouseHandler() {
    }

    /**
     * This method extract vehicleList from warehouseList and sort asc order on dateAdded
     * @param warehouseList
     * @return vehicleList
     */
    public List<Vehicle> extractVehicleList(List<Warehouse> warehouseList){
        LOGGER.info("Invoking extractVehicleList");
        List<Vehicle> vehicleList = warehouseList.stream().map(Warehouse:: getCars).map(Car:: getVehicles).flatMap(List:: stream).collect(Collectors.toList());

        vehicleList.sort(Comparator.comparing(Vehicle :: getDateAdded));
        LOGGER.info("Returning extracted carList");
        return vehicleList;
    }
}
