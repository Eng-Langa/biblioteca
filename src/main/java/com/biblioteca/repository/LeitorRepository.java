package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.*;
public interface LeitorRepository extends JpaRepository<Leitor,Long> {

}
