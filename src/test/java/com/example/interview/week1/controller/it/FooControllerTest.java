package com.example.interview.week1.controller.it;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.interview.week1.repository.FooRepository;

@SpringBootTest
@AutoConfigureMockMvc
class FooControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private FooRepository fooRepository;

    @BeforeEach
    void setUp() throws Exception {
        fooRepository.deleteAll();
    }

    @Test
    void testFindAll_whenNone_thenEmpty() throws Exception {
        mockMvc.perform(get("/foos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void testCreate_thenSuccess() throws Exception {
        mockMvc.perform(
                post("/foos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Sample Foo\",\"description\":\"Sample Description\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void testCreateAndFindAll_thenSuccess() throws Exception {
        mockMvc.perform(
                post("/foos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Sample Foo\",\"description\":\"Sample Description\"}"))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/foos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Sample Foo"))
                .andExpect(jsonPath("$[0].description").value("Sample Description"));
    }

    @Test
    void testFindById_thenSuccess() throws Exception {
        mockMvc.perform(
                post("/foos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Sample Foo\",\"description\":\"Sample Description\"}"))
                .andExpect(status().isCreated())
                .andDo(result -> {
                    String response = result.getResponse().getContentAsString();
                    Long id = Long.valueOf(response);
                    mockMvc.perform(get("/foos/" + id))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.name").value("Sample Foo"))
                            .andExpect(jsonPath("$.description").value("Sample Description"));
                });
    }

    @Test
    void testUpdate_thenSuccess() throws Exception {
        mockMvc.perform(
                post("/foos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Sample Foo\",\"description\":\"Sample Description\"}"))
                .andExpect(status().isCreated())
                .andDo(result -> {
                    String response = result.getResponse().getContentAsString();
                    Long id = Long.valueOf(response);
                    mockMvc.perform(
                            put("/foos/" + id)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("{\"name\":\"Updated Foo\",\"description\":\"Updated Description\"}"))
                            .andExpect(status().isOk());
                    mockMvc.perform(get("/foos/" + id))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.name").value("Updated Foo"))
                            .andExpect(jsonPath("$.description").value("Updated Description"));
                });
    }

    @Test
    void testDelete_thenSuccess() throws Exception {
        mockMvc.perform(
                post("/foos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Sample Foo\",\"description\":\"Sample Description\"}"))
                .andExpect(status().isCreated())
                .andDo(result -> {
                    String response = result.getResponse().getContentAsString();
                    Long id = Long.valueOf(response);
                    mockMvc.perform(
                            get("/foos/" + id))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.name").value("Sample Foo"))
                            .andExpect(jsonPath("$.description").value("Sample Description"));
                    mockMvc.perform(
                            delete("/foos/" + id))
                            .andExpect(status().isOk());
                    mockMvc.perform(get("/foos/" + id))
                            .andExpect(status().isNotFound());
                });
    }
}
