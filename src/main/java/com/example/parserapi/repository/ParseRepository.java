package com.example.parserapi.repository;


import com.example.parserapi.entity.ParserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParseRepository extends JpaRepository<ParserEntity,Long> {
}
