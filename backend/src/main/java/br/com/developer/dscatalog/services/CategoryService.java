package br.com.developer.dscatalog.services;

import br.com.developer.dscatalog.dto.CategoryDTO;
import br.com.developer.dscatalog.entities.Category;
import br.com.developer.dscatalog.repositories.CategoryRepository;
import br.com.developer.dscatalog.services.exceptions.DataBaseException;
import br.com.developer.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    //Todo Buscar todas as categorias
    @Transactional
    public Page<CategoryDTO> buscarTodosPaginados(PageRequest pageRequest) {
        Page<Category> list = repository.findAll(pageRequest);
        return list.map(x -> new CategoryDTO(x));
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

    //Todo Excluir categoria
    public void delete(Long id) {

        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id não encontrado: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Violação de integridade");
        }

    }
}
