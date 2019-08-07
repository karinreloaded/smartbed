package at.fhv.uct.adaptaal.smartbed.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "feature")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column (name = "level")
    private Integer level;

    @Column (name = "upper_level_feature_id")
    private Integer upperLevelFeature;

    @Column (name = "german_name")
    private String german_name;

    @Column (name = "question")
    private String question;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUpperLevelFeature() {
        return upperLevelFeature;
    }

    public void setUpperLevelFeature(Integer upperLevelFeature) {
        this.upperLevelFeature = upperLevelFeature;
    }

    public String getGerman_name() {
        return german_name;
    }

    public void setGerman_name(String german_name) {
        this.german_name = german_name;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return Objects.equals(id, feature.id) &&
                Objects.equals(name, feature.name) &&
                Objects.equals(level, feature.level) &&
                Objects.equals(upperLevelFeature, feature.upperLevelFeature) &&
                Objects.equals(german_name, feature.german_name) &&
                Objects.equals(question, feature.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, level, upperLevelFeature, german_name, question);
    }
}
