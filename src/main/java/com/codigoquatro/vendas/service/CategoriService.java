package com.codigoquatro.vendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codigoquatro.vendas.model.Categoria;

import com.codigoquatro.vendas.repository.CategoriaRepository;

@Service
public class CategoriService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> buscarTodos(){
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long codigo){
        return categoriaRepository.findById(codigo);
    }
}
