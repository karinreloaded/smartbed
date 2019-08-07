package at.fhv.uct.adaptaal.smartbed.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "technology")
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name="german_name")
    private String german_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGerman_name() {
        return german_name;
    }

    public void setGerman_name(String german_name) {
        this.german_name = german_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Technology that = (Technology) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(german_name, that.german_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, german_name);
    }
}
