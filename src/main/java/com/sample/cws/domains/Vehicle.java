package com.sample.cws.domains;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

public class Vehicle{

    @JsonProperty("_id")
    @Field("_id")
    private long id;

    private String make;

    private String model;

    @JsonProperty("year_model")
    @Field("year_model")
    private int yearModel;

    private double price;

    private boolean licensed;

    @JsonProperty("date_added")
    @Field("date_added")
    private String strDateAdded;

    private LocalDate dateAdded;


    public Vehicle() {
    }

    public Vehicle(long id, String make, String model, int yearModel, double price, boolean licensed, String strDateAdded, LocalDate dateAdded) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.yearModel = yearModel;
        this.price = price;
        this.licensed = licensed;
        this.strDateAdded = strDateAdded;
        this.dateAdded = dateAdded;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearModel() {
        return yearModel;
    }

    public void setYearModel(int yearModel) {
        this.yearModel = yearModel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isLicensed() {
        return licensed;
    }

    public void setLicensed(boolean licensed) {
        this.licensed = licensed;
    }

    public String getStrDateAdded() {
        return strDateAdded;
    }

    public void setStrDateAdded(String strDateAdded) {
        this.strDateAdded = strDateAdded;
    }

    public LocalDate getDateAdded() {
        return LocalDate.parse(getStrDateAdded());
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", yearModel=" + yearModel +
                ", price=" + price +
                ", licensed=" + licensed +
                ", strDateAdded='" + strDateAdded + '\'' +
                ", dateAdded=" + dateAdded +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (id != vehicle.id) return false;
        if (yearModel != vehicle.yearModel) return false;
        if (Double.compare(vehicle.price, price) != 0) return false;
        if (licensed != vehicle.licensed) return false;
        if (make != null ? !make.equals(vehicle.make) : vehicle.make != null) return false;
        if (model != null ? !model.equals(vehicle.model) : vehicle.model != null) return false;
        if (strDateAdded != null ? !strDateAdded.equals(vehicle.strDateAdded) : vehicle.strDateAdded != null)
            return false;
        return dateAdded.equals(vehicle.dateAdded);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + yearModel;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (licensed ? 1 : 0);
        result = 31 * result + (strDateAdded != null ? strDateAdded.hashCode() : 0);
        result = 31 * result + dateAdded.hashCode();
        return result;
    }
}
