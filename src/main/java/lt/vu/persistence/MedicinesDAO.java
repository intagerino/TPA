package lt.vu.persistence;

import lt.vu.entities.Medicine;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MedicinesDAO {
    @Inject
    private EntityManager em;

    public List<Medicine> loadAll() {
        return em.createNamedQuery("Medicine.findAll", Medicine.class).getResultList();
    }

    public void persist(Medicine medicine){
        this.em.persist(medicine);
    }

    public Medicine findOne(Integer id){ return em.find(Medicine.class, id); }

    public Medicine update(Medicine medicine){
        return em.merge(medicine);
    }
}