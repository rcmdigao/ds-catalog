package br.com.developer.dscatalog.config;

import br.com.developer.dscatalog.entities.Category;
import br.com.developer.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {



        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        Category cat3 = new Category(null, "Computadores");
        Category cat4 = new Category(null, "Cama, Mesa e Banho");
        Category cat5 = new Category(null, "Higiene");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
        
        
        
        
        
    }
}
