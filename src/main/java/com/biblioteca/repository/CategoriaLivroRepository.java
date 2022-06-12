package com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.model.CategoriaLivro;

public interface CategoriaLivroRepository extends JpaRepository<CategoriaLivro, Long> {

}
