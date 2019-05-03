package at.fhv.uct.adaptaal.smartbed.controllers;

import at.fhv.uct.adaptaal.smartbed.domain.Feature;
import at.fhv.uct.adaptaal.smartbed.domain.MatchedFeature;
import at.fhv.uct.adaptaal.smartbed.persistence.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class MVCController {
    private ArrayList<MatchedFeature> matchedFeatures = new ArrayList<>(); // list of all featurenames
                                                // in combination with boolean matched,
                                                // if matched == false the feature is not checked by user
    private ArrayList<Boolean> resultMatches = new ArrayList<>();
    private Iterable<Feature> features;// all features from database

    private final FeatureRepository featureRepository;
    @Autowired
    public MVCController(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @GetMapping("/sleepconfigstart")
    public String startForm(Model model){

        // get all features from database and initialize matchedFeature
        features = featureRepository.findAll();
        matchedFeatures.clear();
        resultMatches.clear();
        for (Feature f:features) {
            MatchedFeature mf = new MatchedFeature();
            mf.setFeatureName(f.getName());
            mf.setMatched(false);
            matchedFeatures.add(mf);
            resultMatches.add(false);
        }


        model.addAttribute("matchedFeatures", matchedFeatures);
        model.addAttribute("resultMatches", resultMatches.toArray());
        return "sleepconfigstart";
    }

    @PostMapping("/sleepconfigstart")
    public  String submit( Model model){
        model.addAttribute("matchedFeatures", matchedFeatures);
        model.addAttribute("resultMatches", resultMatches.toArray());

        for (MatchedFeature mf: matchedFeatures) {
            System.out.println("############# " + mf.getFeatureName() + " " + mf.getMatched());
        }
        for (Boolean rms: resultMatches) {
            System.out.println("############# result: " + rms);
        }
//        for (Map.Entry<String, Boolean> entry : matchedFeatures.entrySet()) {
//            if (entry.getValue().equals(false)) {
//                System.out.println(entry.getKey());
//            }
//        }
        return "sleepresult";
    }
}
