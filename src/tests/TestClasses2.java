package tests;

import org.junit.Test;
import ratings.Movie;
import ratings.Rating;
import ratings.Song;
import ratings.datastructures.Comparator;
import ratings.datastructures.LinkedListNode;
import ratings.datastructures.SongBayesianRatingComparator;
import ratings.datastructures.SongTitleComparator;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestClasses2 {

    public class TestCaseSongComparator{
        private Song Song1;
        private Song Song2;
        private boolean expectedOutput;

        public TestCaseSongComparator(Song Song1, Song Song2, boolean expectedOutput){
            this.Song1 = Song1;
            this.Song2 = Song2;
            this.expectedOutput = expectedOutput;
        }

        public Song getInput1() {
            return Song1;
        }

        public Song getInput2() {
            return Song2;
        }

        public boolean getExpectedOutput() {
            return expectedOutput;
        }

    }


    public class TestCase{
        private int input1;
        private int input2;
        private double expectedOutput;

        public TestCase(int input1, int input2, double expectedOutput){
            this.input1 = input1;
            this.input2 = input2;
            this.expectedOutput = expectedOutput;
        }

        public int getInput1() {
            return input1;
        }

        public int getInput2() {
            return input2;
        }

        public double getExpectedOutput() {
            return expectedOutput;
        }

    }

    public void addAllMovieRatings(Movie movie, LinkedListNode<Rating> ratings) {
        if (ratings != null) {
            movie.addRating(ratings.getValue());
            addAllMovieRatings(movie, ratings.getNext());
        }
    }
    public void addAllSongRatings(Song song, LinkedListNode<Rating> ratings) {
        if (ratings != null) {
            song.addRating(ratings.getValue());
            addAllSongRatings(song, ratings.getNext());
        }
    }
    public void compareRatings(Rating computed, Rating expected) {
        assertTrue(computed.getRating() == expected.getRating());
        assertTrue(computed.getReviewerID().equals(expected.getReviewerID()));
    }

    public void checkRatingsList(LinkedListNode<Rating> computed, LinkedListNode<Rating> expected) {
        if (expected == null) {
            assertTrue(computed == null);
        } else {
            assertTrue(computed != null);
            compareRatings(computed.getValue(), expected.getValue());
            checkRatingsList(computed.getNext(), expected.getNext());
        }
    }
    public void compareActorsLists(ArrayList<String> expected, ArrayList<String> computed){
        assertTrue("The ArrayLists are not the same size", expected.size() == computed.size());
        for(int i = 0 ; i < expected.size();i++){
            String expected_actor = expected.get(i);
            String computed_actor = computed.get(i);
            assertTrue("Values are different at index: " + i, expected_actor.equalsIgnoreCase(computed_actor));
        }
    }
    @Test
    public void testBayesianAverageWithNoRatings(){
        Song NoRatingSong = new Song("No Ratings","Jesse", "pleasepass");
        LinkedListNode<Rating> ratings = NoRatingSong.getRatings();
        assertEquals(null,ratings);
        checkRatingsList(ratings,null);

        ArrayList<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase(0,0,0.0));
        testCases.add(new TestCase(2,3,3.0));
        testCases.add(new TestCase(0,3,0.0));


        for(TestCase testCase : testCases){
            double expectedOut = testCase.getExpectedOutput();
            double answerOut = NoRatingSong.bayesianAverageRating(testCase.getInput1(), testCase.getInput2());
            assertEquals("returns:"+ answerOut +"  " + "expected:" + expectedOut, expectedOut , answerOut, .001);
        }


    }


    @Test
    public void testBayesianAverageRating1(){
        //Creating a Song with ratings:

        Song Example1 = new Song("Streatham","Dave","Ex1");

        LinkedListNode<Rating> ratings = new LinkedListNode<>(new Rating("Marko",5),null);
        ratings = new LinkedListNode<>(new Rating("Marija",5),ratings);
        ratings = new LinkedListNode<>(new Rating("Cane",1),ratings);
        ratings = new LinkedListNode<>(new Rating("Danijela",3),ratings);

        addAllSongRatings(Example1,ratings);

        LinkedListNode<Rating> computed = Example1.getRatings();

        checkRatingsList(ratings,computed);

        //See if the Bayesian Average method works:

        ArrayList<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase(0,3,3.5));
        testCases.add(new TestCase(2,2,3.0));
        testCases.add(new TestCase(3,5,4.142));
        testCases.add(new TestCase(8,6,5.166));
        testCases.add(new TestCase(2,1,2.666));
        testCases.add(new TestCase(8,2,2.5));
        testCases.add(new TestCase(3,4,3.714));


        for(TestCase testCase : testCases){
            double expectedOut = testCase.getExpectedOutput();
            double answerOut = Example1.bayesianAverageRating(testCase.getInput1(), testCase.getInput2());
            assertEquals( "numberOf:" + testCase.getInput1() + "  " + "valueOf:" +testCase.getInput2() + "  " +
                    "Math 5+5+1+3+"+(testCase.getInput1()*testCase.getInput2())+"/"+(4+ testCase.getInput1())+ "  "+
                    "returns:"+ answerOut +"  " + "expected:" + expectedOut, expectedOut , answerOut, .001);
        }
    }
    @Test
    public void testMovieClass(){
        ArrayList<String> actors = new ArrayList<>(Arrays.asList("Will Smith","Martin Lawrence","Gabrielle Union"));
        Movie BadBoys2 = new Movie("Bad Boys 2",actors);

        assertTrue(BadBoys2.getTitle().equals("Bad Boys 2"));
        ArrayList<String> cast = BadBoys2.getCast();

        assertTrue("The ArrayLists are not the same size", actors.size() == cast.size());
        for(int i = 0 ; i < actors.size();i++){
            String expected_actor = actors.get(i);
            String computed_actor = cast.get(i);
            assertTrue("Values are different at index: " + i, expected_actor.equalsIgnoreCase(computed_actor));
        }

    }

    @Test
    public void testBayesianAverageRating2(){
        //Creating a Movie with ratings:

        ArrayList<String> actors = new ArrayList<>(Arrays.asList("Will Smith","Martin Lawrence","Gabrielle Union"));
        Movie Example1 = new Movie("Bad Boys 2",actors);

        LinkedListNode<Rating> ratings = new LinkedListNode<>(new Rating("Marko",5),null);
        ratings = new LinkedListNode<>(new Rating("Marija",5),ratings);
        ratings = new LinkedListNode<>(new Rating("Cane",1),ratings);
        ratings = new LinkedListNode<>(new Rating("Danijela",3),ratings);

        addAllMovieRatings(Example1,ratings);

        LinkedListNode<Rating> computed = Example1.getRatings();

        checkRatingsList(ratings,computed);

        //See if the Bayesian Average method works:

        ArrayList<TestCase> testCases = new ArrayList<>();
        testCases.add(new TestCase(2,2,3.0));
        testCases.add(new TestCase(3,5,4.142));
        testCases.add(new TestCase(8,6,5.166));
        testCases.add(new TestCase(2,1,2.666));
        testCases.add(new TestCase(8,2,2.5));
        testCases.add(new TestCase(3,4,3.714));

        for(TestCase testCase : testCases){
            double expectedOut = testCase.getExpectedOutput();
            double answerOut = Example1.bayesianAverageRating(testCase.getInput1(), testCase.getInput2());
            assertEquals( "numberOf:" + testCase.getInput1() + "  " + "valueOf:" +testCase.getInput2() + "  " +
                    "Math 5+5+1+3+"+(testCase.getInput1()*testCase.getInput2())+"/"+(4+ testCase.getInput1())+ "  "+
                    "returns:"+ answerOut +"  " + "expected:" + expectedOut, expectedOut , answerOut, .001);
        }
    }
    @Test
    public void testAddingMovieRatings() {
        LinkedListNode<Rating> expected = new LinkedListNode<>(new Rating("Jesse", 4), null);
        expected = new LinkedListNode<>(new Rating("Paul", 5), expected);
        expected = new LinkedListNode<>(new Rating("Carl", 3), expected);

        ArrayList<String> actors = new ArrayList<>(Arrays.asList("Will Smith","Martin Lawrence","Gabrielle Union"));
        Movie movie = new Movie("Bad Boys 2",actors);


        addAllMovieRatings(movie, expected);
        addAllMovieRatings(movie, expected);
        addAllMovieRatings(movie, expected);

        LinkedListNode<Rating> computed = movie.getRatings();
        checkRatingsList(computed, expected);
    }
    @Test
    public void testSongTitleComparator() {
        Song FML = new Song("FML","Kanye West","fml-ye");
        Song UseME = new Song("Use Me", "Future","use me-hendrix");
        Song BigA = new Song("A","BigA","BigA");
        Song Lila = new Song("a","Lila","Lila");
        Song BigB = new Song("B","BigB","BigB");
        Song Lilb = new Song("b","Lilb","Lilb");
        Song twoa = new Song("aa","Two","2a");
        Song threea = new Song("aaa","Three","3a");
        Song empty = new Song("","empty","emptystring");

        SongTitleComparator testcompare = new SongTitleComparator();


        ArrayList<TestCaseSongComparator> testCases = new ArrayList<>();
        testCases.add(new TestCaseSongComparator(FML,UseME,true));
        testCases.add(new TestCaseSongComparator(UseME,FML,false));
        testCases.add(new TestCaseSongComparator(FML,FML,false));
        testCases.add(new TestCaseSongComparator(Lila,BigB,true));
        testCases.add(new TestCaseSongComparator(BigB,Lila,false));
        testCases.add(new TestCaseSongComparator(Lilb,BigA,false));
        testCases.add(new TestCaseSongComparator(BigA,Lilb,true));
        testCases.add(new TestCaseSongComparator(twoa,threea,true));
        testCases.add(new TestCaseSongComparator(empty,FML,true));
        testCases.add(new TestCaseSongComparator(empty,Lilb,true));
        testCases.add(new TestCaseSongComparator(empty,threea,true));


        for(TestCaseSongComparator testCase : testCases) {
            boolean expectedOut = testCase.getExpectedOutput();
            boolean computed = testcompare.compare(testCase.Song1,testCase.Song2);
            assertEquals("Song1:"+testCase.Song1.getTitle() + "  " +"Song2:" + testCase.Song2.getTitle() + "  " + "We expected:" + expectedOut + " "+"We computed:" + computed, expectedOut,computed);

        }
    }
    @Test
    public void testBayesianComparator() {
        Song lower = new Song("Streatham", "Dave", "streathdave");

        LinkedListNode<Rating> ratings_lower = new LinkedListNode<>(new Rating("Marko", 5), null);
        ratings_lower = new LinkedListNode<>(new Rating("Marija", 5), ratings_lower);
        ratings_lower = new LinkedListNode<>(new Rating("Cane", 5), ratings_lower);
        ratings_lower = new LinkedListNode<>(new Rating("Danijela", 5), ratings_lower);

        addAllSongRatings(lower, ratings_lower);
        LinkedListNode<Rating> computed_lower = lower.getRatings();
        checkRatingsList(ratings_lower, computed_lower);

        Song higher = new Song("Spin Bout U", "Drake", "spinboutu");

        LinkedListNode<Rating> ratings_higher = new LinkedListNode<>(new Rating("Marko", 5), null);
        ratings_higher = new LinkedListNode<>(new Rating("Marija", 5), ratings_higher);
        ratings_higher = new LinkedListNode<>(new Rating("Cane", 5), ratings_higher);
        ratings_higher = new LinkedListNode<>(new Rating("Danijela", 5), ratings_higher);
        ratings_higher = new LinkedListNode<>(new Rating("Jon", 5), ratings_higher);
        ratings_higher = new LinkedListNode<>(new Rating("Chris", 5), ratings_higher);
        ratings_higher = new LinkedListNode<>(new Rating("Bill", 5), ratings_higher);
        ratings_higher = new LinkedListNode<>(new Rating("Vanja", 5), ratings_higher);
        ratings_higher = new LinkedListNode<>(new Rating("Deki", 5), ratings_higher);
        ratings_higher = new LinkedListNode<>(new Rating("Vee", 5), ratings_higher);

        addAllSongRatings(higher, ratings_higher);
        LinkedListNode<Rating> computed_higher = higher.getRatings();
        checkRatingsList(ratings_higher, computed_higher);

        SongBayesianRatingComparator testcompare = new SongBayesianRatingComparator();

        ArrayList<TestCaseSongComparator> testCases = new ArrayList<>();
        testCases.add(new TestCaseSongComparator(lower, higher, false));
        testCases.add(new TestCaseSongComparator(lower, lower, false));
        testCases.add(new TestCaseSongComparator(higher, higher, false));
        testCases.add(new TestCaseSongComparator(higher, lower, true));


        for (TestCaseSongComparator testCase : testCases) {
            boolean expectedOut = testCase.getExpectedOutput();
            boolean computed = testcompare.compare(testCase.Song1, testCase.Song2);
            assertEquals(expectedOut, computed);


        }
    }
}