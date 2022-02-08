package br.com.developer.dscatalog.resources;


import br.com.developer.dscatalog.dto.CategoryDTO;
import br.com.developer.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> list = service.buscarTodos();
        return ResponseEntity.ok().body(list);
    }

    //Todo Buscar a categoria por id
    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        CategoryDTO dto = service.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }

}
