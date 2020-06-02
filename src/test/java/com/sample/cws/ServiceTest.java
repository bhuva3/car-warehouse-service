package com.sample.cws;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldReturnSuccessResponseOnVehicleDetailsListAPICall() throws Exception {
        mockMvc.perform(get("/getVehicleDetailList")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnSuccessResponseOnVehicleDetailsAPICall() throws Exception {
        mockMvc.perform(get("/getVehicleDetails/1")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBadRequestErrorOnVehicleDetailsAPICallWithInvalidVehicleId() throws Exception {
        mockMvc.perform(get("/getVehicleDetails/110")).andExpect(status().is4xxClientError());
    }

}
