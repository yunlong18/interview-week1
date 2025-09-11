package com.example.interview.week1.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.interview.week1.controller.converter.FooMapper;
import com.example.interview.week1.dto.FooDTO;
import com.example.interview.week1.entity.Foo;
import com.example.interview.week1.service.FooService;

@WebMvcTest(FooController.class)
public class FooControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FooService fooService;

    @MockitoBean
    private FooMapper fooMapper;

    @Test
    void testFindAll_thenSuccess() throws Exception {
        when(fooService.findAll())
                .thenReturn(List.of())
                .thenReturn(List.of(new Foo(1L, "Sample Foo", "Sample Description")));
        when(fooMapper.toDtos(List.of())).thenReturn(List.of());
        when(fooMapper.toDtos(List.of(new Foo(1L, "Sample Foo", "Sample Description"))))
                .thenReturn(
                        List.of(new FooDTO(1L, "Sample Foo", "Sample Description")));

        mockMvc.perform(get("/foos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
        mockMvc.perform(get("/foos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Sample Foo"))
                .andExpect(jsonPath("$[0].description").value("Sample Description"));
    }

    @Test
    void testFindById_whenFound_thenSuccess() throws Exception {
        when(fooService.findById(1L)).thenReturn(new Foo(1L, "Sample Foo", "Sample Description"));
        when(fooMapper.toDto(new Foo(1L, "Sample Foo", "Sample Description")))
                .thenReturn(new FooDTO(1L, "Sample Foo", "Sample Description"));

        mockMvc.perform(get("/foos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Sample Foo"))
                .andExpect(jsonPath("$.description").value("Sample Description"));
    }

    @Test
    void testFindById_whenNotFound_then404() throws Exception {
        when(fooService.findById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/foos/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreate_whenValidInput_thenSuccess() throws Exception {
        when(fooService.create(new Foo(null, "New Foo", "New Description"))).thenReturn(1L);
        when(fooMapper.toEntity(new FooDTO(null, "New Foo", "New Description")))
                .thenReturn(new Foo(null, "New Foo", "New Description"));

        mockMvc.perform(
                post("/foos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Foo\",\"description\":\"New Description\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("1"));
    }

    @Test
    void testCreate_whenInvalidInput_then400() throws Exception {
        // Missing name
        mockMvc.perform(
                post("/foos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"New Description\"}"))
                .andExpect(status().isBadRequest());

        // Name is null
        mockMvc.perform(
                post("/foos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":null,\"description\":\"New Description\"}"))
                .andExpect(status().isBadRequest());

        // Name is empty
        mockMvc.perform(
                post("/foos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"  \",\"description\":\"New Description\"}"))
                .andExpect(status().isBadRequest());

        // Name is too long
        String longName = "a".repeat(101);
        mockMvc.perform(
                post("/foos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"" + longName + "\",\"description\":\"New Description\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdate_whenValidInput_thenSuccess() throws Exception {
        when(fooService.findById(1L)).thenReturn(new Foo(1L, "Old Foo", "Old Description"));
        when(fooMapper.toEntity(new FooDTO(null, "Updated Foo", "Updated Description")))
                .thenReturn(new Foo(null, "Updated Foo", "Updated Description"));

        mockMvc.perform(
                put("/foos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Foo\",\"description\":\"Updated Description\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate_whenInvalidInput_then400() throws Exception {
        // Missing name
        mockMvc.perform(
                put("/foos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"description\":\"Updated Description\"}"))
                .andExpect(status().isBadRequest());

        // Name is null
        mockMvc.perform(
                put("/foos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":null,\"description\":\"Updated Description\"}"))
                .andExpect(status().isBadRequest());

        // Name is empty
        mockMvc.perform(
                put("/foos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"description\":\"Updated Description\"}"))
                .andExpect(status().isBadRequest());

        // Name is too long
        String longName = "a".repeat(101);
        mockMvc.perform(
                put("/foos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"" + longName + "\",\"description\":\"Updated Description\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDelete_thenSuccess() throws Exception {
        mockMvc.perform(
                delete("/foos/1"))
                .andExpect(status().isOk());
    }
}