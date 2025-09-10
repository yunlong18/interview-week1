package com.example.interview.week1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.interview.week1.entity.Foo;
import com.example.interview.week1.service.FooService;

@Service
public class FooServiceImpl implements FooService {

    private List<Foo> foos = new ArrayList<>(List.of(
            new Foo(1L, "foo1", "description1"),
            new Foo(2L, "foo2", "description2")));

    private AtomicLong idGenerator = new AtomicLong(foos.size());

    @Override
    public List<Foo> findAll() {
        return foos;
    }

    @Override
    public Foo findById(Long id) {
        return foos.stream()
                .filter(foo -> foo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Long create(Foo foo) {
        foo.setId(idGenerator.incrementAndGet());
        foos.add(foo);
        return foo.getId();
    }

    @Override
    public Foo update(Long id, Foo foo) {
        Foo existingFoo = findById(id);
        if (existingFoo != null) {
            existingFoo.setName(foo.getName());
            existingFoo.setDescription(foo.getDescription());
        }
        return existingFoo;
    }

    @Override
    public void delete(Long id) {
        foos.removeIf(foo -> foo.getId().equals(id));
    }
}
