package com.sample.cws.handlers;

import com.sample.cws.Repositories.CarWarehouseRepository;
import com.sample.cws.domains.Car;
import com.sample.cws.domains.Vehicle;
import com.sample.cws.domains.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarWarehouseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarWarehouseHandler.class);

    @Autowired
    CarWarehouseRepository carWarehouseRepository;

    public CarWarehouseHandler() {
    }

    /**
     * This method extract vehicleList from datastore and sort asc order on dateAdded
     * @param
     * @return vehicleList
     */
    public List<Vehicle> getVehicleList(){
        LOGGER.info("Invoking getVehicleList");

        List<Warehouse> warehouseList = carWarehouseRepository.getAllVehicleDetails();

        List<Vehicle> vehicleList = warehouseList.stream().map(Warehouse:: getCars).map(Car:: getVehicles).flatMap(List:: stream).collect(Collectors.toList());

        vehicleList.sort(Comparator.comparing(Vehicle :: getDateAdded));
        LOGGER.info("Returning extracted carList");
        return vehicleList;
    }

    /**
     * This method extract vehicleDetails using vehicleId
     *
     * @param vehicleId
     * @return Warehouse
     */
    public Warehouse getVehicleDetails(Long vehicleId){
        LOGGER.info("Invoking getVehicleDetails for vehicleId: [{}]", vehicleId);

        Warehouse warehouse = carWarehouseRepository.findByVehicleId(vehicleId);
        if(warehouse !=null) {

            // removing unwanted vehicle details from warehouse object
            List<Vehicle> vehicles = new ArrayList<>();
            vehicles.add(warehouse.getCars().getVehicleById(vehicleId));
            warehouse.getCars().setVehicles(vehicles);

            LOGGER.info("Returning extracted vehicle details");

            return warehouse;
        }
        LOGGER.info("No vehicle details found for vehicleId [{}] returning null", vehicleId);
        return null ;
    }

}
