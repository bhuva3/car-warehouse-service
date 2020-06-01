package com.sample.cws.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.cws.domains.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class JsonParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonParser.class);

    public JsonParser() {
    }

    /**
     *  This method to deserialise json data to object
     * @return list of warehouse
     * @throws IOException in event of invalid jsonString
     */
    public static List<Warehouse> getWarehouseData() throws IOException {
        return new ObjectMapper().readValue(getJsonDataAsString(), new TypeReference<List<Warehouse>>() {});
    }

    /**
     *  This method to read json data from data.json file.
     *  Anyway its not good idea to read whole file in one go in a string.
     * @return JsonString from data.json file
     * @throws IOException
     */
    private static String getJsonDataAsString() throws IOException {

        String jsonData = null;
        try {
            File file = ResourceUtils.getFile("classpath:data.json");
            jsonData = new String(Files.readAllBytes(file.toPath()));
        } catch (FileNotFoundException fe){
            LOGGER.error("Exception occurred while retrieving file [{}]", fe);
            throw new FileNotFoundException();
        } catch (IOException ioe){
            LOGGER.error("Exception occurred while reading file [{}]", ioe);
            throw new IOException();
        }
        return jsonData;
    }

}
