package com.codigoquatro.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codigoquatro.vendas.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    
}
