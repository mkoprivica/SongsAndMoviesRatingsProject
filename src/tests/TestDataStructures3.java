package tests;

import org.junit.Test;
import ratings.*;
import ratings.datastructures.LinkedListNode;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.Arrays;

public class TestDataStructures3 {

    public void compareMoviesLists(ArrayList<Movie> expected,ArrayList<Movie> actual){
        assertTrue("The ArrayLists are not the same size", expected.size() == actual.size());
        for(int i = 0 ; i < expected.size();i++) {
            Movie expected_movie = expected.get(i);
            Movie actual_movie = actual.get(i);
            assertEquals(expected_movie.getTitle(), actual_movie.getTitle());
            assertEquals(expected_movie.getCast(), actual_movie.getCast());
        }
    }

    @Test
    public void testNonexistentMoviesFile(){
        String filename = "data/sikeee.csv";
        ArrayList<Movie> actual = FileReader.readMovies(filename);
        ArrayList<Movie> expected = new ArrayList<>();
        compareMoviesLists(expected,actual);

    }
    @Test
    public void testSingleMovie(){
        String filename = "data/SingleMovie.csv";
        ArrayList<Movie> actual = FileReader.readMovies(filename);
        ArrayList<Movie> expected = new ArrayList<>();

        ArrayList<String> BadBoysIIActors = new ArrayList<>(Arrays.asList("Martin Lawrence","Will Smith","Jordi Molla","Gabrielle Union","Peter Stormare","Theresa Randle","Joe Pantoliano","Michael Shannon","Jon Seda","Yul Vazquez","Henry Rollins","Antoni Corone","Oleg Taktarov","Michael Bay","Ron Madoff","Peter Donald Badalamenti II"));
        Movie BadBoysII = new Movie("Bad Boys II",BadBoysIIActors);
        expected.add(BadBoysII);

        compareMoviesLists(expected,actual);
        //compareMoviesActors(expected,actual);

    }
    @Test
    public void testMultipleMovies(){
        String filename = "data/MultipleMovies.csv";
        ArrayList<Movie> actual = FileReader.readMovies(filename);
        ArrayList<Movie> expected = new ArrayList<>();

        ArrayList<String> BadBoysIIActors = new ArrayList<>(Arrays.asList("Martin Lawrence","Will Smith","Jordi Molla", "Gabrielle Union",
                "Peter Stormare","Theresa Randle","Joe Pantoliano","Michael Shannon", "Jon Seda","Yul Vazquez","Henry Rollins","Antoni Corone",
                "Oleg Taktarov", "Michael Bay","Ron Madoff","Peter Donald Badalamenti II"));
        Movie BadBoysII = new Movie("Bad Boys II",BadBoysIIActors);
        expected.add(BadBoysII);

        ArrayList<String> SWATActors = new ArrayList<>(Arrays.asList("Samuel L. Jackson","Colin Farrell","Michelle Rodriguez","LL Cool J",
                "Josh Charles","Jeremy Renner","Brian Van Holt","Olivier Martinez","Reg E. Cathey","Domenick Lombardozzi","James DuMont",
                "Denis Arndt","Octavia Spencer"));
        Movie SWAT = new Movie("S.W.A.T.",SWATActors);
        expected.add(SWAT);

        ArrayList<String> TheGuardianActors = new ArrayList<>(Arrays.asList("Kevin Costner","Ashton Kutcher","Sela Ward","Omari Hardwick",
                "Clancy Brown","Neal McDonough","Damon Lipari","Travis Willingham"));
        Movie TheGuardian = new Movie("The Guardian",TheGuardianActors);
        expected.add(TheGuardian);

        ArrayList<String> FastFuriousActors = new ArrayList<>(Arrays.asList("Paul Walker","Tyrese Gibson","Eva Mendes","Cole Hauser",
                "Ludacris","James Remar","Devon Aoki","Thom Barry","Amaury Nolasco","Michael Ealy","Mark Boone Junior","Mo Gallini",
                "John Cenatiempo","Troy Brown","Troy Robinson","Kerry Rossall","Marc Macaulay"));
        Movie FastFurious = new Movie("2 Fast 2 Furious",FastFuriousActors);
        expected.add(FastFurious);

        ArrayList<String> SpeedActors = new ArrayList<>(Arrays.asList("Keanu Reeves","Dennis Hopper","Sandra Bullock","Joe Morton","Jeff Daniels",
                "Alan Ruck","Glenn Plummer","Richard Lineback","Beth Grant","Jordan Lund","Patrick Fischler","Susan Barnes","Beau Starr",
                "John Capodice","Thomas Rosales Jr.","James DuMont","Richard Schiff","Sandy Martin"));
        Movie Speed = new Movie("Speed",SpeedActors);
        expected.add(Speed);

        ArrayList<String> SmithsActors = new ArrayList<>(Arrays.asList("Angelina Jolie","Brad Pitt","Vince Vaughn","Adam Brody","Kerry Washington",
                "Keith David","Michelle Monaghan","Jennifer Morrison","Perrey Reeves"));
        Movie Smiths = new Movie("Mr. & Mrs. Smith",SmithsActors);
        expected.add(Smiths);


        compareMoviesLists(expected,actual);

    }
}
