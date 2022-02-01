package br.com.developer.dscatalog.services;

import br.com.developer.dscatalog.dto.CategoryDTO;
import br.com.developer.dscatalog.entities.Category;
import br.com.developer.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    //Todo Buscar todas as categorias
    public List<CategoryDTO> buscarTodos() {
        List<Category> list =  repository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }


}
