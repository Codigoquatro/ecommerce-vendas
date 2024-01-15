package com.codigoquatro.vendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.lang.NonNull;
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

    public Optional<Categoria> buscarPorCodigo(@NonNull Long codigo){
        return categoriaRepository.findById(codigo);
    }

    public Categoria salvarCategoria(@NonNull Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Categoria atualizaCategoria(@NonNull Long codigo,@NonNull Categoria categoria){
        Categoria  categoriaSalvar = validaCategoriaExiste(codigo);

        BeanUtils.copyProperties(categoria, categoriaSalvar,"codigo");
        return categoriaRepository.save(categoriaSalvar);
    }

    private Categoria validaCategoriaExiste(@NonNull Long codigo) {
        Optional<Categoria> categoria = buscarPorCodigo(codigo);

        if(categoria.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        } 
        
        return categoria.get();
    }

}
