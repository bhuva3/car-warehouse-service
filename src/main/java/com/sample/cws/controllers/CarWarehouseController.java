package com.sample.cws.controllers;

import com.sample.cws.domains.Vehicle;
import com.sample.cws.domains.Warehouse;
import com.sample.cws.handlers.CarWarehouseHandler;
import com.sample.cws.utils.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CarWarehouseController {

    @Autowired
    CarWarehouseHandler carWarehouseHandler;

    private static final Logger LOGGER = LoggerFactory.getLogger(CarWarehouseController.class);

    /**
     * This API to get vehicle list
     * Swagger API documentation can be added if required
     * Can be improved by getting vehicle list on basis of index number and offset (pagination). Instead get whole vehiclelist in one call.
     * @return ResponseEntity with vehicleList
     */
    @ResponseBody
    @GetMapping(value = "/getVehicleDetailList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVehicleDetailList() {
        ResponseEntity response;
        List<Vehicle> vehicleList;

        try {
            List<Warehouse> warehouseList = JsonParser.getWarehouseData();
            vehicleList = carWarehouseHandler.extractVehicleList(warehouseList);
        } catch (IOException e) {
            LOGGER.error("Exception occurred while parsing json file [{}]", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response = new ResponseEntity(vehicleList, HttpStatus.OK);

        return response;
    }

    @ResponseBody
    @GetMapping(value = "/getVehicleDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVehicleDetails(@PathVariable (value = "id") long vehicleId) {
        LOGGER.info("Invoking getVehicleDetails() for vehicleId: [{}]", vehicleId);
        ResponseEntity response;
        Warehouse warehouse;

        try {
            List<Warehouse> warehouseList = JsonParser.getWarehouseData();
            warehouse = carWarehouseHandler.extractVehicleDetails(vehicleId, warehouseList);

        } catch (IOException e) {
            LOGGER.error("Exception occurred while parsing json file [{}]", e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(warehouse !=null){
            response = new ResponseEntity(warehouse, HttpStatus.OK);
        } else {
            response = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}
