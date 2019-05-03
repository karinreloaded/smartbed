package at.fhv.uct.adaptaal.smartbed.domain;

public class MatchedFeature {

    private String featureName;
    private Boolean matched;

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public Boolean getMatched() {
        return matched;
    }

    public void setMatched(Boolean matched) {
        this.matched = matched;
    }
}
