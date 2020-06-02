package com.sample.cws.controllers;


import com.sample.cws.domains.Car;
import com.sample.cws.domains.Vehicle;
import com.sample.cws.domains.Warehouse;
import com.sample.cws.handlers.CarWarehouseHandler;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarWarehouseControllerTest {

    @Mock
    CarWarehouseHandler carWarehouseHandler;

    @InjectMocks
    CarWarehouseController carWarehouseController;



    @Test
    public void shouldReturnValidResponseWithVehicleDetailList(){

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(1, "make1", "model1", 2019, 12.122, true, "2020-02-17", LocalDate.now().plusDays(1)));
        vehicleList.add(new Vehicle(2, "make2", "model2", 2019, 12.55, true, "2020-02-16", LocalDate.now()));
        vehicleList.add(new Vehicle(3, "make3", "model3", 2019, 12.55, true, "2020-02-19", LocalDate.now().plusDays(2)));
        List<Warehouse> warehouseList = new ArrayList<>();

        warehouseList.add(new Warehouse(1, "warehouse1", new Car("Glasgow", vehicleList), null));
        warehouseList.add(new Warehouse(2, "warehouse2", new Car("Glasgow", vehicleList), null));


        when(carWarehouseHandler.getVehicleList()).thenReturn(vehicleList);
        ResponseEntity responseEntity = carWarehouseController.getVehicleDetailList();
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }


    @Test
    public void shouldReturnValidResponseWithVehicleDetail(){

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(1, "make1", "model1", 2019, 12.122, true, "2020-02-17", LocalDate.now().plusDays(1)));
        vehicleList.add(new Vehicle(2, "make2", "model2", 2019, 12.55, true, "2020-02-16", LocalDate.now()));
        vehicleList.add(new Vehicle(3, "make3", "model3", 2019, 12.55, true, "2020-02-19", LocalDate.now().plusDays(2)));

        Warehouse warehouse = new Warehouse(1, "warehouse1", new Car("Glasgow", vehicleList), null);

        when(carWarehouseHandler.getVehicleDetails(eq(1L))).thenReturn(warehouse);
        ResponseEntity responseEntity = carWarehouseController.getVehicleDetails(1L);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void shouldReturnBadRequestErrorOnGetVehicleDetailsWithInvalidVehicleId(){

        when(carWarehouseHandler.getVehicleDetails(eq(111L))).thenReturn(null);
        ResponseEntity responseEntity = carWarehouseController.getVehicleDetails(111L);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Ignore
    public void shouldReturn500ResponseOnAPICall() throws Exception {
        // Need to use PowerMock to mock utility class, At this point not seeing much value in doing it.
    }

}
