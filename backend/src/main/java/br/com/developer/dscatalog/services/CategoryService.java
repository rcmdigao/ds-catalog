package br.com.developer.dscatalog.services;

import br.com.developer.dscatalog.entities.Category;
import br.com.developer.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    //Todo Buscar todas as categorias
    public List<Category> buscarTodos() {
        return repository.findAll();
    }



}
