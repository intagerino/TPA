package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "YearlyCase.findAll", query = "select c from YearlyCase as c where c.disease.id =: diseaseId")
})
@Table(name = "YEARLYCASE")
@Getter @Setter
public class YearlyCase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "CASES")
    private Integer cases;

    @ManyToOne
    @JoinColumn(name="DISEASE_ID")
    private Disease disease;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public YearlyCase() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YearlyCase cases = (YearlyCase) o;
        return Objects.equals(id, cases.id) &&
                Objects.equals(year, cases.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year);
    }
}
