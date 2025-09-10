package com.example.interview.week1.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.interview.week1.service.FooService;

@WebMvcTest(FooController.class)
public class FooControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FooService fooService;

    @Test
    void testFindAll_thenSuccess() throws Exception {
        when(fooService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/foos"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void testFindById_whenNotFound_then404() throws Exception {
        when(fooService.findById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/foos/1"))
            .andExpect(status().isNotFound());
    }
}
