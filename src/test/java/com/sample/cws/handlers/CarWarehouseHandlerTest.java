package com.sample.cws.handlers;

import com.sample.cws.Repositories.CarWarehouseRepository;
import com.sample.cws.domains.Car;
import com.sample.cws.domains.Vehicle;
import com.sample.cws.domains.Warehouse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarWarehouseHandlerTest {


    @Mock
    CarWarehouseRepository carWarehouseRepository;


    @InjectMocks
    CarWarehouseHandler carWarehouseHandler;


    @Test
    public void shouldReturnCorrectExtractedVehicleList(){
        when(carWarehouseRepository.getAllVehicleDetails()).thenReturn(getMockedWarehouseList());
        List<Vehicle> resultVehicleList = carWarehouseHandler.getVehicleList();

        Assert.assertNotNull(resultVehicleList);
        Assert.assertEquals(resultVehicleList.size(), 6);
        Assert.assertEquals(resultVehicleList.get(0).getId(), 2);
    }

    @Test
    public void shouldReturnCorrectExtractedWarehouseDetails(){

        List<Warehouse> mockedWarehouseList = getMockedWarehouseList();
        when(carWarehouseRepository.findByVehicleId(1L)).thenReturn(mockedWarehouseList.get(0));
        Warehouse warehouse = carWarehouseHandler.getVehicleDetails(1L);

        Assert.assertNotNull(warehouse);
        Assert.assertEquals(warehouse.getId(), 1);
    }

    @Test
    public void shouldReturnNullWhenVehicleIdNotExist(){

        when(carWarehouseRepository.findByVehicleId(110L)).thenReturn(null);
        Warehouse warehouse = carWarehouseHandler.getVehicleDetails(110L);
        Assert.assertNull(warehouse);

    }

    @Test
    public void shouldReturnEmptyListWhenNoVehicleExist(){

        when(carWarehouseRepository.getAllVehicleDetails()).thenReturn(new ArrayList<>());
        List<Vehicle> vehicleList= carWarehouseHandler.getVehicleList();
        Assert.assertEquals(vehicleList.size(), 0);

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
