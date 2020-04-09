package com.example.category;

import com.example.category.model.Category;
import com.example.category.model.CategoryDTO;
import com.example.category.repository.CategoryRepository;
import com.example.category.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController()
@RequestMapping(path = "/api/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(path = "/{id}")
    public Mono<CategoryDTO> getCategoryById(@PathVariable(name = "id") UUID id) {
        return categoryRepository.findById(id)
                .map(category -> ObjectMapperUtils.map(category, CategoryDTO.class))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(String.format("No category with id %s found.", id))));
    }

    @GetMapping(path = "/")
    public Flux<Category> getAllCategories() {
        return categoryRepository.findAll()
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("No category found")));
    }

    @PostMapping(path = "/")
    public Mono<ResponseEntity<CategoryDTO>> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setId(UUID.randomUUID());
        return categoryRepository.save(ObjectMapperUtils.map(categoryDTO, Category.class))
                .map(resp -> new ResponseEntity<>(ObjectMapperUtils.map(resp, CategoryDTO.class), HttpStatus.CREATED));
    }

    @PutMapping(path = "/")
    public Mono<ResponseEntity<CategoryDTO>> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryRepository.findById(categoryDTO.getId())
                .flatMap(resp -> composeUpdateResponse(ObjectMapperUtils.map(categoryDTO, Category.class)))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(String.format("No category with id %s found.", categoryDTO.getId()))));
    }


    private Mono<ResponseEntity<CategoryDTO>> composeUpdateResponse(Category category) {
        return categoryRepository.save(category)
                .map(resp -> new ResponseEntity<>(ObjectMapperUtils.map(resp, CategoryDTO.class), HttpStatus.CREATED));

    }

    @DeleteMapping(path = "/{id}")
    public Mono<Void> deleteCategoryById(@PathVariable(name = "id") UUID id) {
       return categoryRepository.findById(id)
               .flatMap(category -> categoryRepository.deleteById(id));
    }
}
