package br.com.developer.dscatalog.services;

import br.com.developer.dscatalog.dto.CategoryDTO;
import br.com.developer.dscatalog.entities.Category;
import br.com.developer.dscatalog.repositories.CategoryRepository;
import br.com.developer.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    //Todo Buscar todas as categorias
    @Transactional
    public List<CategoryDTO> buscarTodos() {
        List<Category> list = repository.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
    }

    //Todo Buscar a categoria por id
    @Transactional
    public CategoryDTO buscarPorId(Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado!"));
        return new CategoryDTO(entity);
    }

    //Todo Cadastrar categoria
    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }

    //Todo Atualizar categoria
    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {
            Category entity = repository.getOne(id); // Não precisar ir ao BD duas vezes
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado: " + id);
        }
    }
}
