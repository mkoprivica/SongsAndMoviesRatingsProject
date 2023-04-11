package ratings.datastructures;
import ratings.Song;


public class SongBayesianRatingComparator extends Comparator<Song> {

    @Override
    public boolean compare(Song a, Song b) {
        double a_bayesian_avg = a.bayesianAverageRating(2,3);
        double b_bayesian_avg = b.bayesianAverageRating(2,3);
        return a_bayesian_avg > b_bayesian_avg;
    }

}