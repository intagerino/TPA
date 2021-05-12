package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Disease.findAll", query = "select d from Disease as d")
})
@Table(name = "DISEASE")
@Getter @Setter
public class Disease implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "disease")
    private List<YearlyCase> yearlyCases = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "MEDICINE_FOR_DISEASES")
    private List<Medicine> medicines = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Disease() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disease disease = (Disease) o;
        return Objects.equals(id, disease.id) &&
                Objects.equals(name, disease.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
