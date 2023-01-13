package com.telconet.practica.service.inscriptions;

import java.util.List;

import com.telconet.practica.entity.Inscription;

public interface InscriptionSvc {
    
    /**
     * 
     * Listar todas las inscripciones
     * @return
     */
    public List<Inscription> findAll ();

    /**
     * 
     * Guardar una inscripcion
     * @param inscription
     * @return
     */
    public Inscription save (Inscription inscription);

    /**
     * 
     * Buscar una Inscription por el ID
     * 
     * @param id
     * @return
     */
    public Inscription findById (Long id);

    /**
     * 
     * Permite actualizar una Inscription existente
     * 
     * @param inscription
     * @param id
     * @return
     */
    public Inscription update (Inscription inscription, Long id);

    /**
     * 
     * Permite eliminar una Inscription existente
     * 
     * @param id
     */
    public void deleteById (Long id);
}
