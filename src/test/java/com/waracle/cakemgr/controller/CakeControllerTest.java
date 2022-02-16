package com.waracle.cakemgr.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.CakeApplication;
import com.waracle.cakemgr.model.Cake;
import com.waracle.cakemgr.repository.CakeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = CakeApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CakeControllerTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CakeRepository repository;

    @Test
    public void givenCakeWhenGetCakesThenStatus200()
            throws Exception {

        createTestCake("Fairy Cake", "A cake for fairies", "/cake.jpg");

        mockMvc.perform(get("/cakes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").value("Fairy Cake"))
                .andExpect(jsonPath("$[0].description").value("A cake for fairies"))
                .andExpect(jsonPath("$[0].image").value("/cake.jpg"));
    }

    @Test
    public void givenCakeWhenPostCakeThenStatus200() throws Exception {
        final Cake cake = new Cake(null,"cake title","cake description","cake image");
        mockMvc.perform(post("/cakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cake)))
                .andExpect(jsonPath("$.title").value("cake title"));
    }

    private void createTestCake( String title, String description, String image ) {
        final Cake cake = new Cake(null,title,description,image);

        repository.saveAndFlush( cake );
    }
}
