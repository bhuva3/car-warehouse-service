package com.sample.cws.Repositories;

import com.sample.cws.connections.MongoDBConnectionManager;
import com.sample.cws.domains.Car;
import com.sample.cws.domains.Vehicle;
import com.sample.cws.domains.Warehouse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarWarehouseRepositoryTest {

    @Mock
    MongoTemplate mongoTemplate;

    @Mock
    MongoDBConnectionManager mongoDBConnectionManager;

    @InjectMocks
    CarWarehouseRepository carWarehouseRepository = new CarWarehouseRepositoryImp();

    @Test
    public void shouldReturnValidWarehouseList(){
        when(mongoDBConnectionManager.getMongoTemplate()).thenReturn(mongoTemplate);
        when(mongoTemplate.findAll(Warehouse.class)).thenReturn(getMockedWarehouseList());
        List<Warehouse> warehouseList = carWarehouseRepository.getAllVehicleDetails();
        Assert.assertNotNull(warehouseList);
        Assert.assertEquals(warehouseList.size(), 2);

    }

    @Test
    public void shouldReturnValidWarehouseDetailsWhenVehicleIdExist(){
        when(mongoDBConnectionManager.getMongoTemplate()).thenReturn(mongoTemplate);
        when(mongoTemplate.find(any(Query.class), eq(Warehouse.class))).thenReturn(getMockedWarehouseList());
        Warehouse warehouse = carWarehouseRepository.findByVehicleId(1L);
        Assert.assertNotNull(warehouse);
        Assert.assertEquals(warehouse.getId(), 1);

    }

    @Test
    public void shouldReturnEmptyWarehouseListWhenNoWarehouseDetailsFound(){
        when(mongoDBConnectionManager.getMongoTemplate()).thenReturn(mongoTemplate);
        when(mongoTemplate.findAll(Warehouse.class)).thenReturn(new ArrayList<>());
        List<Warehouse> warehouseList = carWarehouseRepository.getAllVehicleDetails();
        Assert.assertNotNull(warehouseList);
        Assert.assertEquals(warehouseList.size(), 0);

    }

    @Test
    public void shouldReturnNullWarehouseDetailsWhenVehicleIdNotExist(){
        when(mongoDBConnectionManager.getMongoTemplate()).thenReturn(mongoTemplate);
        when(mongoTemplate.find(any(Query.class), eq(Warehouse.class))).thenReturn(new ArrayList<>());
        Warehouse warehouse = carWarehouseRepository.findByVehicleId(1L);
        Assert.assertNull(warehouse);

    }

    private List<Warehouse> getMockedWarehouseList() {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle(1, "make1", "model1", 2019, 12.122, true, "2020-02-17", LocalDate.now().plusDays(1)));
        vehicleList.add(new Vehicle(2, "make2", "model2", 2019, 12.55, true, "2020-02-16", LocalDate.now()));
        vehicleList.add(new Vehicle(3, "make3", "model3", 2019, 12.55, true, "2020-02-19", LocalDate.now().plusDays(2)));
        List<Warehouse> warehouseList = new ArrayList<>();

        warehouseList.add(new Warehouse(1, "warehouse1", new Car("Glasgow", vehicleList), null));
        warehouseList.add(new Warehouse(2, "warehouse2", new Car("Glasgow", vehicleList), null));
        return warehouseList;
    }
}