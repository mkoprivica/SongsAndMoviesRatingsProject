package ratings.datastructures;
import ratings.Ratable;
import ratings.Rating;

import java.util.Comparator;


public class RatablesBayesianRatingComparator implements Comparator<Ratable> {

    public int compare(Ratable a, Ratable b) {
        double a_bayesian_avg = a.bayesianAverageRating(2, 3);
        double b_bayesian_avg = b.bayesianAverageRating(2, 3);
        if (a_bayesian_avg == b_bayesian_avg) {
            return 0;
        } else if (a_bayesian_avg > b_bayesian_avg) {
            return -1;
        } else {
            return 1;
        }
    }
}
