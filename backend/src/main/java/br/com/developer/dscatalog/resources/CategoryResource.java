package br.com.developer.dscatalog.resources;


import br.com.developer.dscatalog.entities.Category;
import br.com.developer.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    //Todo Buscar todas as categorias
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.buscarTodos();
        return ResponseEntity.ok().body(list);
    }


}
