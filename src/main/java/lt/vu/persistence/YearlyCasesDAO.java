package lt.vu.persistence;

import lt.vu.entities.YearlyCase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class YearlyCasesDAO {
    @Inject
    private EntityManager em;

    public List<YearlyCase> loadAll(Integer id){
        return em.createNamedQuery("YearlyCase.findAll", YearlyCase.class).setParameter("diseaseId", id).getResultList();
    }

    public void persist(YearlyCase yearlyCase){
        this.em.persist(yearlyCase);
    }

    public YearlyCase findOne(Integer id){
        return em.find(YearlyCase.class, id);
    }

    public YearlyCase update(YearlyCase yearlyCase){
        return em.merge(yearlyCase);
    }
}