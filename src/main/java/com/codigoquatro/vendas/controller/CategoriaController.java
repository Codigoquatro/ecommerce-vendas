package com.codigoquatro.vendas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codigoquatro.vendas.model.Categoria;
import com.codigoquatro.vendas.service.CategoriService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriService categoriService;

    @GetMapping
    public List<Categoria> listarTodos(){
        return categoriService.buscarTodos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Categoria> >listaPorId(@PathVariable Long codigo){        
        Optional<Categoria> categoria = categoriService.buscarPorId(codigo);

        return categoria.isPresent()?ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

}
