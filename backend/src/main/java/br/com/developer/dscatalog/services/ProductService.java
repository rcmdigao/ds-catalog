package br.com.developer.dscatalog.services;

import br.com.developer.dscatalog.dto.CategoryDTO;
import br.com.developer.dscatalog.dto.ProductDTO;
import br.com.developer.dscatalog.entities.Category;
import br.com.developer.dscatalog.entities.Product;
import br.com.developer.dscatalog.repositories.CategoryRepository;
import br.com.developer.dscatalog.repositories.ProductRepository;
import br.com.developer.dscatalog.repositories.ProductRepository;
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
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    //Todo Buscar todas os produtos
    @Transactional
    public Page<ProductDTO> buscarTodosPaginados(PageRequest pageRequest) {
        Page<Product> list = repository.findAll(pageRequest);
        return list.map(x -> new ProductDTO(x));
    }

    //Todo Buscar produto por ID
    @Transactional
    public ProductDTO buscarPorId(Long id) {
        Optional<Product> obj = repository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado!"));
        return new ProductDTO(entity, entity.getCategories());
    }

    //Todo Cadastrar produtos
    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }


    //Todo Atualizar produtos
    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getOne(id); // Não precisar ir ao BD duas vezes
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id não encontrado: " + id);
        }
    }

    //Todo Excluir produtos
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id não encontrado: " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Violação de integridade");
        }

    }

    //Todo DTO > Entity
    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());

        // lImpando a lista
        entity.getCategories().clear();
        // copiando as categorias para a entidade
        for (CategoryDTO catDto : dto.getCategories()) {
            Category category = categoryRepository.getOne(catDto.getId());
            entity.getCategories().add(category);
        }
    }

}
