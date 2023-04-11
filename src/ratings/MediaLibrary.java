package ratings;

import ratings.datastructures.RatablesBayesianRatingComparator;

import java.util.ArrayList;
import java.util.Collections;


public class MediaLibrary {
    private ArrayList<Ratable> AllMedia = new ArrayList<>();

    public MediaLibrary(){}

    public void populateLibrary(String songs_ratings,String movies,String movie_ratings){
        ArrayList<Song> SongsAndRatings = FileReader.readSongs(songs_ratings);
        ArrayList<Movie> MoviesAndActors = FileReader.readMovies(movies);
        ArrayList<Movie> MoviesAndRatings = FileReader.readMovieRatings(MoviesAndActors,movie_ratings);
        for (Song song : SongsAndRatings){
            AllMedia.add(song);
        }for (Movie movie : MoviesAndRatings){
            AllMedia.add(movie);
        }
    }
    public ArrayList<Ratable> topKRatables (int k){
        ArrayList<Ratable> TopList = new ArrayList<>();
        AllMedia.sort(new RatablesBayesianRatingComparator());
        for (int i = 0; i < k ; i++ ){
            TopList.add(AllMedia.get(i));
        }

        return TopList;

    }

}
