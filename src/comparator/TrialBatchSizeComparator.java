package comparator;

import java.util.Comparator;

import model.trial.Trial;

public class TrialBatchSizeComparator implements Comparator<Trial>{
    @Override 
    public int compare(Trial trial1, Trial trial2) {
        return Double.compare(
            trial1.getBatchSizeTrial(),
            trial2.getBatchSizeTrial()
        );
    }
}
