package com.example.interview.week1.controller.converter;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.interview.week1.dto.FooDTO;
import com.example.interview.week1.entity.Foo;

@Mapper
public interface FooMapper {

    FooDTO toDto(Foo foo);

    Foo toEntity(FooDTO fooDTO);

    List<FooDTO> toDtos(List<Foo> foos);
}
