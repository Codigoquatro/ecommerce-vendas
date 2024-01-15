package com.codigoquatro.vendas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codigoquatro.vendas.model.Categoria;
import com.codigoquatro.vendas.service.CategoriService;

import jakarta.validation.Valid;

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
    public ResponseEntity<Optional<Categoria> >listaPorId(@PathVariable @NonNull Long codigo){        
        Optional<Categoria> categoria = categoriService.buscarPorCodigo(codigo);

        return categoria.isPresent()?ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody @NonNull Categoria categoria){
        Categoria categoriaSalva = categoriService.salvarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Categoria> atualizar(@Valid @PathVariable @NonNull Long codigo,@RequestBody @NonNull Categoria categoria){
        return ResponseEntity.ok(categoriService.atualizaCategoria(codigo, categoria));  
    }

}
