package com.example.interview.week1.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.interview.week1.controller.converter.FooMapper;
import com.example.interview.week1.dto.FooDTO;
import com.example.interview.week1.service.FooService;
import com.example.interview.week1.utils.Preconditions;
import com.example.interview.week1.utils.RestPreconditions;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/foos")
class FooController {

    private final FooService fooService;
    private final FooMapper fooMapper;

    public FooController(FooService fooService, FooMapper fooMapper) {
        this.fooService = fooService;
        this.fooMapper = fooMapper;
    }

    @GetMapping
    public List<FooDTO> findAll() {
        return fooMapper.toDtos(fooService.findAll());
    }

    @GetMapping("/{id}")
    public FooDTO findById(@PathVariable("id") Long id) {
        return fooMapper.toDto(RestPreconditions.checkNotFound(fooService.findById(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody @Valid FooDTO resource) {
        Preconditions.checkNotNull(resource);
        return fooService.create(fooMapper.toEntity(resource));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody FooDTO resource) {
        Preconditions.checkNotNull(resource);
        RestPreconditions.checkNotFound(fooService.findById(id));
        fooService.update(id, fooMapper.toEntity(resource));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        fooService.delete(id);
    }
}
