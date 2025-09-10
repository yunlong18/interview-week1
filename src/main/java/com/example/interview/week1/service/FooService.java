package com.example.interview.week1.service;

import java.util.List;

import com.example.interview.week1.entity.Foo;

public interface FooService {
    List<Foo> findAll();

    Foo findById(Long id);

    Long create(Foo foo);

    Foo update(Long id, Foo foo);

    void delete(Long id);
}
