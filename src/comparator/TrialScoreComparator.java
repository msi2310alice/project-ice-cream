package comparator;

import java.util.Comparator;

import model.trial.Trial;

public class TrialScoreComparator implements Comparator<Trial>{
    @Override 
    public int compare(Trial trial1, Trial trial2) {
        return Double.compare(
            trial1.getAverageScoreTrial(),
            trial2.getAverageScoreTrial()
        );
    }
}
