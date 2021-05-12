package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.MedicineMapper;
import lt.vu.mybatis.model.Medicine;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Medicines {
    @Inject
    private MedicineMapper medicineMapper;

    @Getter
    private List<Medicine> allMedicines;

    @Getter @Setter
    private Medicine medicineToCreate = new Medicine();

    @PostConstruct
    public void init() {
        this.loadAllMedicines();
    }

    private void loadAllMedicines() {
        this.allMedicines = medicineMapper.selectAll();
    }

    @Transactional
    public String createMedicine() {
        medicineMapper.insert(medicineToCreate);
        return "index?faces-redirect=true";
    }
}
