package lt.vu.persistence;

import lt.vu.entities.Disease;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class DiseasesDAO {
    @Inject
    private EntityManager em;

    public List<Disease> loadAll(){
        return em.createNamedQuery("Disease.findAll", Disease.class).getResultList();
    }

    public void persist(Disease disease){
        this.em.persist(disease);
    }

    public Disease findOne(Integer id){
        return em.find(Disease.class, id);
    }

    public Disease update(Disease disease){
        return em.merge(disease);
    }

}