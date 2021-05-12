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
        @NamedQuery(name = "Medicine.findAll", query = "select m from Medicine as m")
})
@Table(name = "MEDICINE")
@Getter @Setter
public class Medicine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(mappedBy = "medicines")
    private List<Disease> diseases = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Medicine() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicine medicine = (Medicine) o;
        return Objects.equals(id, medicine.id) &&
                Objects.equals(name, medicine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
