package com.telconet.practica.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telconet.practica.entity.Category;
import com.telconet.practica.service.categories.CategorySvc;
import com.telconet.practica.util.JSONResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    private CategorySvc categorySvc;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Listar categorias", response = Category.class, responseContainer = "List")
    public ResponseEntity<?> getAll () {
        return ResponseEntity.ok(this.categorySvc.findAll());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Crear nueva categoria", response = Category.class)
    public ResponseEntity<?> save (@Valid @RequestBody Category category) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(this.categorySvc.save(category));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Listar categoria por ID", response = Category.class, responseContainer = "List")
    public ResponseEntity<?> getById (@PathVariable Long id) { 
        return ResponseEntity.ok(this.categorySvc.findById(id));
    }
   
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Actualizar una categoria por ID", response = Category.class)
    public ResponseEntity<?> updateById (@RequestBody Category category, @PathVariable Long id) { 
        return ResponseEntity.ok(this.categorySvc.update(category, id));
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value="Borrar una categoria por ID", response = Category.class)
    public ResponseEntity<?> destroy (@PathVariable Long id) { 
        this.categorySvc.deleteById(id);
        return ResponseEntity.ok(
            JSONResponse.fromGeneralTemplate("/api/categories/" + id, "Categoria eliminada!", HttpStatus.OK.value()).getBody());
    }
}
