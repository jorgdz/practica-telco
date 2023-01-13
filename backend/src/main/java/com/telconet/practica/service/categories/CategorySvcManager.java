package com.telconet.practica.service.categories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telconet.practica.entity.Category;
import com.telconet.practica.exception.ConflictException;
import com.telconet.practica.exception.NotFoundException;
import com.telconet.practica.repository.CategoryRepository;

@Service
public class CategorySvcManager implements CategorySvc {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category findById(Long id) {
        return this.categoryRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("No se encontro una categoria con el ID: " + id));
    }

    @Override
    @Transactional
    public Category update(Category category, Long id) {
        Category _category = this.categoryRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("No se encontro una categoria con el ID: " + id));

        String 
			name = category.getName(),
            minDate = category.getMinDate();
        
        if (name != null)
            _category.setName(name);
        
        if (minDate != null)
            _category.setMinDate(minDate);

        Category categoryUpdated = this.categoryRepository.save(_category);

        if (categoryUpdated == null)
            throw new ConflictException("No hemos podido actualizar la categoria");

        return categoryUpdated;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.categoryRepository.findById(id)
			.map(ctg -> { categoryRepository.deleteById(ctg.getId()); return ctg; })
			.orElseThrow(() -> new NotFoundException("No se encontro una categoria con el Id " + id));
    }
}
