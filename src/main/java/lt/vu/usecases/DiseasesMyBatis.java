package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.DiseaseMapper;
import lt.vu.mybatis.model.Disease;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
@RequestScoped
public class DiseasesMyBatis {

    @Inject
    private DiseaseMapper diseaseMapper;

    @Getter @Setter
    private Disease diseaseToCreate = new Disease();

    @Getter
    private List<Disease> allDiseases;

    @PostConstruct
    public void init(){ loadAllDiseases(); }

    private void loadAllDiseases() { this.allDiseases = diseaseMapper.selectAll(); }


    @Transactional
    public String createDisease(){
        diseaseMapper.insert(diseaseToCreate);
        return "diseases?faces-redirect=true";
    }
}