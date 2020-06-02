package com.sample.cws.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


public class Warehouse {

    @JsonProperty("_id")
    @Field("_id")
    private long id;

    private String name;

    private Car cars;

    @JsonProperty("location")
    @Field("location")
    private WarehouseLocation warehouseLocation;

    public Warehouse() {
    }

    public Warehouse(long id, String name, Car cars, WarehouseLocation warehouseLocation) {
        this.id = id;
        this.name = name;
        this.cars = cars;
        this.warehouseLocation = warehouseLocation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCars() {
        return cars;
    }

    public void setCars(Car cars) {
        this.cars = cars;
    }

    public WarehouseLocation getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(WarehouseLocation warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cars=" + cars +
                ", warehouseLocation=" + warehouseLocation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Warehouse warehouse = (Warehouse) o;

        if (id != warehouse.id) return false;
        if (!name.equals(warehouse.name)) return false;
        if (cars != null ? !cars.equals(warehouse.cars) : warehouse.cars != null) return false;
        return warehouseLocation != null ? warehouseLocation.equals(warehouse.warehouseLocation) : warehouse.warehouseLocation == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (cars != null ? cars.hashCode() : 0);
        result = 31 * result + (warehouseLocation != null ? warehouseLocation.hashCode() : 0);
        return result;
    }
}
