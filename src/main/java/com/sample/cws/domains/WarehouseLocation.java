package com.sample.cws.domains;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WarehouseLocation {

    private String lat;

    @JsonProperty("long")
    private String longitude;

    public WarehouseLocation() {
    }

    public WarehouseLocation(String lat, String longitude) {
        this.lat = lat;
        this.longitude = longitude;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "WarehouseLocation{" +
                "lat='" + lat + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WarehouseLocation that = (WarehouseLocation) o;

        if (lat != null ? !lat.equals(that.lat) : that.lat != null) return false;
        return longitude != null ? longitude.equals(that.longitude) : that.longitude == null;
    }

    @Override
    public int hashCode() {
        int result = lat != null ? lat.hashCode() : 0;
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }
}
