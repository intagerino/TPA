package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.YearlyCase;
import lt.vu.entities.Disease;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.YearlyCasesDAO;
import lt.vu.persistence.DiseasesDAO;
import lt.vu.services.IncrementNumber;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class CasesForDisease implements Serializable {

    @Inject
    IncrementNumber incrementer;

    @Inject
    private DiseasesDAO diseasesDAO;

    @Inject
    private YearlyCasesDAO yearlyCasesDAO;

    @Getter @Setter
    private Disease disease;

    @Getter @Setter
    private YearlyCase yearlyCaseToCreate = new YearlyCase();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if(requestParameters.get("diseaseId") != null){
            Integer diseaseId = Integer.parseInt(requestParameters.get("diseaseId"));
            this.disease = diseasesDAO.findOne(diseaseId);
        }
    }

    @Transactional
    @LoggedInvocation
    public String createYearlyCase() {
        yearlyCaseToCreate.setDisease(this.disease);
        yearlyCaseToCreate.setCases(incrementer.IncrementNumberBy(this.yearlyCaseToCreate.getCases()));
        yearlyCasesDAO.persist(yearlyCaseToCreate);
        return "disease?faces-redirect=true&diseaseId=" + this.disease.getId();
    }
}