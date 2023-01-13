package com.telconet.practica.service.inscriptions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telconet.practica.entity.Category;
import com.telconet.practica.entity.Inscription;
import com.telconet.practica.entity.User;
import com.telconet.practica.exception.ConflictException;
import com.telconet.practica.exception.NotFoundException;
import com.telconet.practica.repository.InscriptionRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InscriptionSvcManager implements InscriptionSvc {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Transactional
    @Override
    public List<Inscription> findAll() {
        return this.inscriptionRepository.findAll();
    }

    @Transactional
    @Override
    public Inscription save(Inscription inscription) {
        return this.inscriptionRepository.save(inscription);
    }

    @Transactional
    @Override
    public Inscription findById(Long id) {
        return this.inscriptionRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("No se encontro una inscripcion disponible con el ID: " + id));
    }

    @Transactional
    @Override
    public Inscription update(Inscription inscription, Long id) {
        Inscription _inscription = this.inscriptionRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("No se encontro una inscripcion disponible con el ID: " + id));
        
        Integer maxHour = inscription.getMaxHour();
        User professional = inscription.getProfessional();
        Category category = inscription.getCategory();

        if (maxHour != null)
            _inscription.setMaxHour(maxHour);
        
        if (professional != null)
            _inscription.setProfessional(professional);

        if (category != null)
            _inscription.setCategory(category);
        
        Inscription inscriptionUpdated = this.inscriptionRepository.save(_inscription);
        
        if (inscriptionUpdated == null)
            throw new ConflictException("No ha sido posible actualizar la inscripcion");
        
        return inscriptionUpdated;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        this.inscriptionRepository.findById(id)
			.map(inscription -> { inscriptionRepository.deleteById(inscription.getId()); return inscription; })
			.orElseThrow(() -> new NotFoundException("No se encontro una inscripcion con el Id " + id));
    }
}
