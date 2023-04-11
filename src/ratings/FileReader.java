package ratings;

import ratings.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FileReader {

    public static ArrayList<String> readFile(String filename) {
        try {
            return new ArrayList<>(Files.readAllLines(Paths.get(filename)));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }


    //"songID,artist,title,reviewerID,rating"


    public static ArrayList<Song> readSongs(String filename) {
        ArrayList<Song> Songs = new ArrayList<>();
        int count = 0;
        HashMap<String , Integer> UniqueSong = new HashMap<>();
        ArrayList<String> lines = readFile(filename);
        for (String line : lines){
            ArrayList<String> splits = new ArrayList<>(Arrays.asList(line.split(",")));

            String songID = splits.get(0);
            String artist = splits.get(1);
            String title = splits.get(2);
            String reviewerID = (splits.get(3));
            int rating = Integer.parseInt(splits.get(4));

            if (!UniqueSong.containsKey(songID)){
                UniqueSong.put(songID,count++);
                Songs.add(new Song(title,artist,songID));
            }


            for (Song song : Songs){

                if (song.getSongID().equals(songID) &&
                        song.getArtist().equals(artist) &&
                        song.getTitle().equals(title) &&
                        song.didReviewerRateSong(reviewerID) == false){
                    song.addRating(new Rating(reviewerID,rating));
                }
            }

        }

        return Songs;

    }


    //"movieTitle,castMember0,castMember1,castMember2,etc"

    public static ArrayList<Movie> readMovies(String filename) {
        ArrayList<Movie> Movies = new ArrayList<>();
        ArrayList<String> lines = readFile(filename);
        for (String line : lines){
            ArrayList<String> splits = new ArrayList<>(Arrays.asList(line.split(",")));
            ArrayList<String> actors = new ArrayList<>();

            String movieTitle = splits.get(0);

            for (int i=1 ; i < splits.size() ; i++ ){
                actors.add(splits.get(i));
            }

            Movies.add(new Movie(movieTitle,actors));

        }

        return Movies;

    }


    //"title,reviewerId,rating"


    public static ArrayList<Movie> readMovieRatings (ArrayList<Movie> readMoviesList, String filename) {
        ArrayList<Movie> Movies = new ArrayList<>();
        ArrayList<String> lines = readFile(filename);
        for (String line : lines) {
            ArrayList<String> splits = new ArrayList<>(Arrays.asList(line.split(",")));

            String title = splits.get(0);
            String reviewerId = splits.get(1);
            int rating = Integer.parseInt(splits.get(2));

            for (Movie movie : readMoviesList) {
                if (movie.getTitle().equals(title) && movie.didReviewerRateSong(reviewerId) == false) {
                    if (Movies.contains(movie) == false) {
                        movie.addRating(new Rating(reviewerId, rating));
                        Movies.add(movie);
                    } else if (Movies.contains(movie) == true) {
                        movie.addRating(new Rating(reviewerId, rating));
                    }
                }
            }
        }
        return Movies;
    }


}
