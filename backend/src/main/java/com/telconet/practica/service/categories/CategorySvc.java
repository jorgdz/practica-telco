package com.telconet.practica.service.categories;

import java.util.List;

import com.telconet.practica.entity.Category;

public interface CategorySvc {
    
    /**
     * 
     * Listar todas las categorias
     * @return
     */
    public List<Category> findAll ();

    /**
     * 
     * Guardar una categoria
     * @param category
     * @return
     */
    public Category save (Category category);

    /**
     * 
     * Buscar una categoria por el ID
     * 
     * @param id
     * @return
     */
    public Category findById (Long id);

    /**
     * 
     * Permite actualizar una categoria existente
     * 
     * @param category
     * @param id
     * @return
     */
    public Category update (Category category, Long id);

    /**
     * 
     * Permite eliminar una categoria existente
     * 
     * @param id
     */
    public void deleteById (Long id);
}
