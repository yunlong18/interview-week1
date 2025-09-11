package com.example.interview.week1.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.interview.week1.entity.Foo;
import com.example.interview.week1.repository.FooRepository;
import com.example.interview.week1.service.FooService;

@Service
public class FooServiceImpl implements FooService {

    private final FooRepository fooRepository;

    public FooServiceImpl(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @Override
    public List<Foo> findAll() {
        return fooRepository.findAll();
    }

    @Override
    public Foo findById(Long id) {
        return fooRepository.findById(id).orElse(null);
    }

    @Override
    public Long create(Foo foo) {
        return fooRepository.save(foo).getId();
    }

    @Override
    public Foo update(Long id, Foo foo) {
        Foo existingFoo = findById(id);
        if (existingFoo != null) {
            existingFoo.setName(foo.getName());
            existingFoo.setDescription(foo.getDescription());
            fooRepository.save(existingFoo);
        }
        return existingFoo;
    }

    @Override
    public void delete(Long id) {
        fooRepository.deleteById(id);
    }
}
