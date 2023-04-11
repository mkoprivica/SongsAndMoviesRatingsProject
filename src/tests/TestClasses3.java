package tests;

import org.junit.Assert;
import org.junit.Test;
import ratings.*;
import ratings.datastructures.LinkedListNode;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

public class TestClasses3 {

    public void compareRatableLists(ArrayList<Ratable> expected,ArrayList<Ratable> actual){
        assertTrue("The ArrayLists are not the same size", expected.size() == actual.size());
        for(int i = 0 ; i < expected.size();i++) {
            Ratable expected_movie = expected.get(i);
            Ratable actual_movie = actual.get(i);
            assertEquals(expected_movie.getTitle(), actual_movie.getTitle());
        }
    }

    public void compareMoviesLists(ArrayList<Movie> expected,ArrayList<Movie> actual){
        assertTrue("The ArrayLists are not the same size", expected.size() == actual.size());
        for(int i = 0 ; i < expected.size();i++) {
            Movie expected_movie = expected.get(i);
            Movie actual_movie = actual.get(i);
            assertEquals(expected_movie.getTitle(), actual_movie.getTitle());
            assertEquals(expected_movie.getCast(), actual_movie.getCast());
        }
    }
    public void compareMoviesRatings(ArrayList<Movie> expected, ArrayList<Movie> actual){
        assertTrue("The ArrayLists are not the same size", expected.size() == actual.size());
        LinkedListNode<Rating> expectedRatings;
        LinkedListNode<Rating> actualRatings;
        for (int i = 0 ; i < expected.size();i++){
            expectedRatings = expected.get(i).getRatings();
            actualRatings = actual.get(i).getRatings();
            checkRatingsList(expectedRatings,actualRatings);
        }

    }

    public void compareSongsLists(ArrayList<Song> expected, ArrayList<Song> actual){
        assertTrue("The ArrayLists are not the same size", expected.size() == actual.size());
        for(int i = 0 ; i < expected.size();i++){
            Song expected_song = expected.get(i);
            Song actual_song = actual.get(i);
            assertEquals(expected_song.getTitle(),actual_song.getTitle());
            assertEquals(expected_song.getArtist(),actual_song.getArtist());
            assertEquals(expected_song.getSongID(),actual_song.getSongID());
        }
    }
    public void compareSongsRatings(ArrayList<Song> expected, ArrayList<Song> actual){
        assertTrue("The ArrayLists are not the same size", expected.size() == actual.size());
        LinkedListNode<Rating> expectedRatings;
        LinkedListNode<Rating> actualRatings;
        for (int i = 0 ; i < expected.size();i++){
            expectedRatings = expected.get(i).getRatings();
            actualRatings = actual.get(i).getRatings();
            checkRatingsList(expectedRatings,actualRatings);
        }

    }


    public void compareRatings(Rating expected, Rating actual) {
        assertTrue(actual.getRating() == expected.getRating());
        assertTrue(actual.getReviewerID().equals(expected.getReviewerID()));
    }

    public void checkRatingsList(LinkedListNode<Rating> expected, LinkedListNode<Rating> actual) {
        if (expected == null) {
            assertTrue(actual == null);
        } else {
            assertTrue(actual != null);
            compareRatings(actual.getValue(), expected.getValue());
            checkRatingsList(actual.getNext(), expected.getNext());
        }
    }
    @Test
    public void testNonexistentSongsFile(){
        String filename = "data/sikeee.csv";
        ArrayList<Song> actual = FileReader.readSongs(filename);
        ArrayList<Song> expected = new ArrayList<>();
        compareSongsLists(expected,actual);
        compareSongsRatings(expected,actual);

    }

    @Test
    public void testSingleSong(){
        String filename = "data/SingleSong.csv";
        ArrayList<Song> actual = FileReader.readSongs(filename);
        ArrayList<Song> expected = new ArrayList<>();

        Song JungleByDrake = new Song("Jungle","Drake","0hO6VSfTfH4iC0XolpXa0B");
        JungleByDrake.addRating(new Rating("373",5));
        JungleByDrake.addRating(new Rating("509",3));
        JungleByDrake.addRating(new Rating("120",5));
        JungleByDrake.addRating(new Rating("216",4));
        JungleByDrake.addRating(new Rating("132",1));
        JungleByDrake.addRating(new Rating("131",5));
        JungleByDrake.addRating(new Rating("53",5));
        JungleByDrake.addRating(new Rating("331",5));
        JungleByDrake.addRating(new Rating("503",4));
        JungleByDrake.addRating(new Rating("162",5));

        expected.add(JungleByDrake);
        compareSongsLists(expected,actual);
        compareSongsRatings(expected,actual);


    }
    @Test
    public void testNonexistentMovieRatingsFile(){
        ArrayList<Movie> empty = new ArrayList<>();
        String filename = "data/sikeee.csv";
        ArrayList<Movie> actual = FileReader.readMovieRatings(empty,filename);
        ArrayList<Movie> expected = new ArrayList<>();
        compareMoviesLists(expected,actual);

    }

    @Test
    public void testOneMovieRatings(){
        ArrayList<Movie> input = new ArrayList<>();

        ArrayList<String> LauraActors = new ArrayList<>(Arrays.asList("Vincent Price", "Judith Anderson" ," Bess Flowers" ,"James Conaty",
                "Sam Harris","Harold Miller","Harry Strang","Ralph Dunn"));
        Movie Laura = new Movie("Laura",LauraActors);
        input.add(Laura);
        assertTrue(input.contains(Laura));

        ArrayList<String> ImpostorActors = new ArrayList<>(Arrays.asList("Gary Sinise","Madeleine Stowe","Mekhi Phifer","Vincent D'Onofrio",
                "Tony Shalhoub","Tim Guinee","Gary Dourdan","Ivana Milicevic","Una Damon"));
        Movie Impostor = new Movie("Impostor",ImpostorActors);
        input.add(Impostor);
        assertTrue(input.contains(Impostor));


        ArrayList<String> TheGuardianActors = new ArrayList<>(Arrays.asList("Kevin Costner","Ashton Kutcher","Sela Ward","Omari Hardwick",
                "Clancy Brown","Neal McDonough","Damon Lipari","Travis Willingham"));
        Movie TheGuardian = new Movie("The Guardian",TheGuardianActors);
        input.add(TheGuardian);
        assertTrue(input.contains(TheGuardian));


        String filename = "data/OneMovieRatings.csv";
        ArrayList<Movie> actual = FileReader.readMovieRatings(input,filename);
        ArrayList<Movie> expected = new ArrayList<>();

        Laura.addRating(new Rating("102",5));
        Laura.addRating(new Rating("222",5));
        Laura.addRating(new Rating("232",5));
        Laura.addRating(new Rating("242",5));
        Laura.addRating(new Rating("336",5));
        Laura.addRating(new Rating("358",5));
        Laura.addRating(new Rating("387",5));
        Laura.addRating(new Rating("436",5));
        Laura.addRating(new Rating("468",3));
        Laura.addRating(new Rating("505",3));
        Laura.addRating(new Rating("547",5));
        expected.add(Laura);

        Impostor.addRating(new Rating("388",4));
        expected.add(Impostor);

        compareMoviesLists(expected,actual);
        compareMoviesRatings(expected,actual);
    }

    @Test
    public void testMediaLibrary1(){
        String songs_ratings = "data/TwoSong.csv";
        String movies ="data/SampleMediaMovies.csv";
        String movie_ratings = "data/OneMovieRatings.csv";


        MediaLibrary Top3 = new MediaLibrary();
        Top3.populateLibrary(songs_ratings, movies, movie_ratings);
        ArrayList<Ratable> actual = Top3.topKRatables(3);
        ArrayList<Ratable> expected = new ArrayList<>();

        ArrayList<String> LauraActors = new ArrayList<>(Arrays.asList("Vincent Price", "Judith Anderson" ," Bess Flowers" ,"James Conaty",
                "Sam Harris","Harold Miller","Harry Strang","Ralph Dunn"));
        Movie Laura = new Movie("Laura",LauraActors);
        Laura.addRating(new Rating("102",5));
        Laura.addRating(new Rating("222",5));
        Laura.addRating(new Rating("232",5));
        Laura.addRating(new Rating("242",5));
        Laura.addRating(new Rating("336",5));
        Laura.addRating(new Rating("358",5));
        Laura.addRating(new Rating("387",5));
        Laura.addRating(new Rating("436",5));
        Laura.addRating(new Rating("468",3));
        Laura.addRating(new Rating("505",3));
        Laura.addRating(new Rating("547",5));
        expected.add(0,Laura);
        assertTrue(expected.contains(Laura));



        Song JungleByDrake = new Song("Jungle","Drake","0hO6VSfTfH4iC0XolpXa0B");
        JungleByDrake.addRating(new Rating("373",5));
        JungleByDrake.addRating(new Rating("509",3));
        JungleByDrake.addRating(new Rating("120",5));
        JungleByDrake.addRating(new Rating("216",4));
        JungleByDrake.addRating(new Rating("132",1));
        JungleByDrake.addRating(new Rating("131",5));
        JungleByDrake.addRating(new Rating("53",5));
        JungleByDrake.addRating(new Rating("331",5));
        JungleByDrake.addRating(new Rating("503",4));
        JungleByDrake.addRating(new Rating("162",5));
        expected.add(1,JungleByDrake);
        assertTrue(expected.contains(JungleByDrake));


        ArrayList<String> ImpostorActors = new ArrayList<>(Arrays.asList("Gary Sinise","Madeleine Stowe","Mekhi Phifer","Vincent D'Onofrio",
                "Tony Shalhoub","Tim Guinee","Gary Dourdan","Ivana Milicevic","Una Damon"));
        Movie Impostor = new Movie("Impostor",ImpostorActors);
        Impostor.addRating(new Rating("388",4));
        expected.add(2,Impostor);
        assertTrue(expected.contains(Impostor));

        compareRatableLists(expected,actual);
    }
    @Test
    public void testMediaLibrary2(){
        String songs_ratings = "data/MediaSong.csv";
        String movies ="data/SampleMediaMovies.csv";
        String movie_ratings = "data/OneMovieRatings.csv";


        MediaLibrary Top3 = new MediaLibrary();
        Top3.populateLibrary(songs_ratings, movies, movie_ratings);
        ArrayList<Ratable> actual = Top3.topKRatables(3);
        ArrayList<Ratable> expected = new ArrayList<>();


        Song JungleByDrake = new Song("Jungle","Drake","0hO6VSfTfH4iC0XolpXa0B");
        JungleByDrake.addRating(new Rating("373",5));
        JungleByDrake.addRating(new Rating("509",5));
        JungleByDrake.addRating(new Rating("120",5));
        JungleByDrake.addRating(new Rating("216",5));
        JungleByDrake.addRating(new Rating("132",5));
        JungleByDrake.addRating(new Rating("131",5));
        JungleByDrake.addRating(new Rating("53",5));
        JungleByDrake.addRating(new Rating("331",5));
        JungleByDrake.addRating(new Rating("503",5));
        JungleByDrake.addRating(new Rating("162",5));
        expected.add(0,JungleByDrake);
        assertTrue(expected.contains(JungleByDrake));

        ArrayList<String> LauraActors = new ArrayList<>(Arrays.asList("Vincent Price", "Judith Anderson" ," Bess Flowers" ,"James Conaty",
                "Sam Harris","Harold Miller","Harry Strang","Ralph Dunn"));
        Movie Laura = new Movie("Laura",LauraActors);
        Laura.addRating(new Rating("102",5));
        Laura.addRating(new Rating("222",5));
        Laura.addRating(new Rating("232",5));
        Laura.addRating(new Rating("242",5));
        Laura.addRating(new Rating("336",5));
        Laura.addRating(new Rating("358",5));
        Laura.addRating(new Rating("387",5));
        Laura.addRating(new Rating("436",5));
        Laura.addRating(new Rating("468",3));
        Laura.addRating(new Rating("505",3));
        Laura.addRating(new Rating("547",5));
        expected.add(1,Laura);
        assertTrue(expected.contains(Laura));


        ArrayList<String> ImpostorActors = new ArrayList<>(Arrays.asList("Gary Sinise","Madeleine Stowe","Mekhi Phifer","Vincent D'Onofrio",
                "Tony Shalhoub","Tim Guinee","Gary Dourdan","Ivana Milicevic","Una Damon"));
        Movie Impostor = new Movie("Impostor",ImpostorActors);
        Impostor.addRating(new Rating("388",4));
        expected.add(2,Impostor);
        assertTrue(expected.contains(Impostor));

        compareRatableLists(expected,actual);


    }
}
