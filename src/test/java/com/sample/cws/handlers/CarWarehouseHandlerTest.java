package com.sample.cws.handlers;

import com.sample.cws.domains.Car;
import com.sample.cws.domains.Vehicle;
import com.sample.cws.domains.Warehouse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CarWarehouseHandlerTest {

    CarWarehouseHandler carWarehouseHandler;

    @Before
    public void setUp(){
        carWarehouseHandler = new CarWarehouseHandler();
    }

    @Test
    public void shouldReturnCorrectExtractedList(){

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(1, "make1", "model1", 2019, 12.122, true, "2020-02-17", LocalDate.now().plusDays(1)));
        vehicleList.add(new Vehicle(2, "make2", "model2", 2019, 12.55, true, "2020-02-16", LocalDate.now()));
        vehicleList.add(new Vehicle(3, "make3", "model3", 2019, 12.55, true, "2020-02-19", LocalDate.now().plusDays(2)));
        List<Warehouse> warehouseList = new ArrayList<>();

        warehouseList.add(new Warehouse(1, "warehouse1", new Car("Glasgow", vehicleList), null));
        warehouseList.add(new Warehouse(2, "warehouse2", new Car("Glasgow", vehicleList), null));

        List<Vehicle> resultVehicleList = carWarehouseHandler.extractVehicleList(warehouseList);

        Assert.assertNotNull(resultVehicleList);
        Assert.assertEquals(resultVehicleList.size(), 6);
        Assert.assertEquals(resultVehicleList.get(0).getId(), 2);
    }
}