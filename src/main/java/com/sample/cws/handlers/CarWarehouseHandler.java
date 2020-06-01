package com.sample.cws.handlers;

import com.sample.cws.domains.Car;
import com.sample.cws.domains.Vehicle;
import com.sample.cws.domains.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
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

    /**
     * This method extract vehicleDetails from warehouseList
     *
     * @param vehicleId
     * @param warehouseList
     * @return Warehouse
     */
    public Warehouse extractVehicleDetails(Long vehicleId, List<Warehouse> warehouseList){
        LOGGER.info("Invoking extractVehicleDetails for vehicleId: [{}]", vehicleId);

        // predicate to filter out warehouse containing vehicleId
        Predicate<Warehouse> warehousePredicate = warehouse -> warehouse.getCars().isVehicleExist(vehicleId) == true;

        // use predicate to filter out warehouse details
        List<Warehouse> warehouses = warehouseList.stream().filter(warehousePredicate).collect(Collectors.toList());

        if(!warehouses.isEmpty()) {

            // removing unwanted vehicle details from warehouse object
            List<Vehicle> vehicles = new ArrayList<>();
            Warehouse warehouse = warehouses.get(0);
            vehicles.add(warehouse.getCars().getVehicleById(vehicleId));

            warehouse.getCars().setVehicles(vehicles);

            LOGGER.info("Returning extracted vehicle details");

            return warehouse;
        }

        LOGGER.info("No vehicle details found for vehicleId [{}] returning null", vehicleId);
        return null ;
    }

}
