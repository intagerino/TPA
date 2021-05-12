package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Disease;
import lt.vu.entities.YearlyCase;
import lt.vu.persistence.DiseasesDAO;
import lt.vu.persistence.YearlyCasesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
@RequestScoped
public class Diseases {

    @Inject
    private DiseasesDAO diseasesDAO;

    @Getter @Setter
    private Disease diseaseToCreate = new Disease();

    @Getter
    private List<Disease> allDiseases;

    @PostConstruct
    public void init(){ loadAllDiseases(); }

    private void loadAllDiseases() { allDiseases = diseasesDAO.loadAll(); }


    @Transactional
    public String createDisease(){
        diseasesDAO.persist(diseaseToCreate);
        return "index?faces-redirect=true";
    }
}