package com.sample.cws.controllers;


import com.sample.cws.domains.Car;
import com.sample.cws.domains.Vehicle;
import com.sample.cws.domains.Warehouse;
import com.sample.cws.handlers.CarWarehouseHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CarWarehouseControllerTest {

    @Mock
    CarWarehouseHandler carWarehouseHandler;

    @InjectMocks
    CarWarehouseController carWarehouseController;

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldReturnValidResponseWithVehicleDetailList(){

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(1, "make1", "model1", 2019, 12.122, true, "2020-02-17", LocalDate.now().plusDays(1)));
        vehicleList.add(new Vehicle(2, "make2", "model2", 2019, 12.55, true, "2020-02-16", LocalDate.now()));
        vehicleList.add(new Vehicle(3, "make3", "model3", 2019, 12.55, true, "2020-02-19", LocalDate.now().plusDays(2)));
        List<Warehouse> warehouseList = new ArrayList<>();

        warehouseList.add(new Warehouse(1, "warehouse1", new Car("Glasgow", vehicleList), null));
        warehouseList.add(new Warehouse(2, "warehouse2", new Car("Glasgow", vehicleList), null));


        when(carWarehouseHandler.extractVehicleList(warehouseList)).thenReturn(vehicleList);
        ResponseEntity responseEntity = carWarehouseController.getVehicleDetailList();
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void shouldReturnSuccessResponseOnVehicleDetailsListAPICall() throws Exception {
        mockMvc.perform(get("/getVehicleDetailList")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnValidResponseWithVehicleDetail(){

        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(1, "make1", "model1", 2019, 12.122, true, "2020-02-17", LocalDate.now().plusDays(1)));
        vehicleList.add(new Vehicle(2, "make2", "model2", 2019, 12.55, true, "2020-02-16", LocalDate.now()));
        vehicleList.add(new Vehicle(3, "make3", "model3", 2019, 12.55, true, "2020-02-19", LocalDate.now().plusDays(2)));

        Warehouse warehouse = new Warehouse(1, "warehouse1", new Car("Glasgow", vehicleList), null);

        when(carWarehouseHandler.extractVehicleDetails(eq(1L), anyList())).thenReturn(warehouse);
        ResponseEntity responseEntity = carWarehouseController.getVehicleDetails(1L);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void shouldReturnBadRequestErrorOnGetVehicleDetailsWithInvalidVehicleId(){

        when(carWarehouseHandler.extractVehicleDetails(eq(111L), anyList())).thenReturn(null);
        ResponseEntity responseEntity = carWarehouseController.getVehicleDetails(111L);
        Assert.assertNotNull(responseEntity);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

    @Test
    public void shouldReturnSuccessResponseOnVehicleDetailsAPICall() throws Exception {
        mockMvc.perform(get("/getVehicleDetails/1")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestErrorOnVehicleDetailsAPICallWithInvalidVehicleId() throws Exception {
        mockMvc.perform(get("/getVehicleDetails/110")).andExpect(status().is4xxClientError());
    }

    @Ignore
    public void shouldReturn500ResponseOnAPICall() throws Exception {
        // Need to use PowerMock to mock utility class, At this point not seeing much value in doing it.
    }

}