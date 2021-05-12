package lt.vu.usecases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Disease;
import lt.vu.entities.Medicine;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.DiseasesDAO;
import lt.vu.persistence.MedicinesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
@RequestScoped
public class MedicinesForDiseases implements Serializable {

    @Inject
    private DiseasesDAO diseasesDAO;

    @Getter @Setter
    private Disease disease = new Disease();

    @Inject
    private MedicinesDAO medicinesDAO;

    @Getter @Setter
    private Medicine medicine;

    @Getter
    private List<Medicine> allMedicines;

    @Getter
    private List<Medicine> addedMedicines;

    @Getter
    private List<Medicine> unaddedMedicines;

    @PostConstruct
    public void init(){
        loadAllMedicines();
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer diseaseId = Integer.parseInt(requestParameters.get("diseaseId"));
        this.disease = diseasesDAO.findOne(diseaseId);
        loadAddedMedicines();
        loadUnaddedMedicines();
    }

    public void loadAllMedicines() { this.allMedicines = medicinesDAO.loadAll(); }

    public void loadAddedMedicines() { this.addedMedicines = this.disease.getMedicines(); }

    public void loadUnaddedMedicines() { this.unaddedMedicines = difference(this.allMedicines, this.addedMedicines); }

    public <T> List<T> difference(List<T> first, List<T> second) {
        List<T> toReturn = new ArrayList<>(first);
        toReturn.removeAll(second);
        return toReturn;
    }

    @Transactional
    @LoggedInvocation
    public String addMedicine(){
        addedMedicines.add(this.medicine);
        this.disease.setMedicines(this.addedMedicines);
        diseasesDAO.update(this.disease);
        loadAddedMedicines();
        loadUnaddedMedicines();
        return "disease?faces-redirect=true&diseaseId=" + this.disease.getId();
    }
}
