package com.sample.cws.Repositories;

import com.sample.cws.connections.MongoDBConnectionManager;
import com.sample.cws.domains.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarWarehouseRepositoryImp implements CarWarehouseRepository{

    private static final Logger LOGGER = LoggerFactory.getLogger(CarWarehouseRepositoryImp.class);

    @Autowired
    MongoDBConnectionManager mongoDBConnectionManager;


    /**
     * This method returns Warehouse list on basis of vehicleId
     * @param vehicleId
     * @return List<Warehouse>
     */
    @Override
    public Warehouse findByVehicleId(Long vehicleId) {
        LOGGER.info("Invoking findByVehicleId() for vehicleId [{}]", vehicleId);

        MongoTemplate mongoTemplate = mongoDBConnectionManager.getMongoTemplate();

        Query query = new Query();
        query.addCriteria(Criteria.where("cars.vehicles").elemMatch(Criteria.where("_id").is(vehicleId)));

        List<Warehouse> warehouseList = mongoTemplate.find(query, Warehouse.class);
        return warehouseList.size() > 0 ? warehouseList.get(0) : null;
    }

    /**
     * This method retrieving all records in one call, it can be improved by using pagination as mentioned in getVehicleDetailsWithPagination()
     * @return List<Warehouse>
     */
    @Override
    public List<Warehouse> getAllVehicleDetails() {
        LOGGER.info("Invoking getAllVehicleDetails()");
        List<Warehouse> warehouseList;
        MongoTemplate mongoTemplate = mongoDBConnectionManager.getMongoTemplate();

        warehouseList = mongoTemplate.findAll(Warehouse.class);
        return warehouseList;
    }

    /**
     *
     * @param index index number of page/record
     * @param offset number of record to be fetched
     * @return List<Warehouse>
     */
    @Override
    public List<Warehouse> getVehicleDetailsWithPagination(int index, int offset) {
        LOGGER.info("Invoking getVehicleDetailsWithPagination() with index [{}] and offset [{}]", index, offset);
        List<Warehouse> warehouseList;
        MongoTemplate mongoTemplate = mongoDBConnectionManager.getMongoTemplate();

        final Pageable pageableRequest = PageRequest.of(index, offset);
        Query query = new Query();
        query.with(pageableRequest);

        warehouseList = mongoTemplate.find(query, Warehouse.class);

        return warehouseList;
    }

}
