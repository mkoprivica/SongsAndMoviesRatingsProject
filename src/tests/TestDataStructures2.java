package tests;

import org.junit.Test;
import ratings.*;
import ratings.datastructures.LinkedListNode;
import ratings.datastructures.SongBayesianRatingComparator;
import ratings.datastructures.SongTitleComparator;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestDataStructures2 {
    public <T> void compareLinkedLists(LinkedListNode<T> l1, LinkedListNode<T> l2) {
        if(!(l1 == null && l2 == null)){
            assertTrue("l1 was null, but l2 was not", l1 != null);
            assertTrue("l2 was null, but l1 was not", l2 != null);
            assertTrue("node values are not equal", l1.getValue().equals(l2.getValue()));
            compareLinkedLists(l1.getNext(), l2.getNext());
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

    @Test
    public void testSortByTitle(){
        //SongTitleComparator testcompare = new SongTitleComparator();
        Playlist TitlePlaylist = new Playlist(new SongTitleComparator());

        Song a = new Song("a","a","a");
        TitlePlaylist.addSong(a);

        Song b = new Song("b","b","b");
        TitlePlaylist.addSong(b);

        Song c = new Song("c","c","c");
        TitlePlaylist.addSong(c);

        Song d = new Song("d","d","d");
        TitlePlaylist.addSong(d);

        Song e = new Song("e","e","e");
        TitlePlaylist.addSong(e);

        Song f = new Song("f","f","f");
        TitlePlaylist.addSong(f);


        LinkedListNode<Song> expected = new LinkedListNode<>(f,null);
        expected = new LinkedListNode<>(e,expected);
        expected = new LinkedListNode<>(d,expected);
        expected = new LinkedListNode<>(c,expected);
        expected = new LinkedListNode<>(b,expected);
        expected = new LinkedListNode<>(a,expected);

        LinkedListNode<Song> computed= TitlePlaylist.getSongList();
        compareLinkedLists(expected ,computed);




    }
    @Test
    public void testSortByRating(){
        Playlist RatingPlaylist = new Playlist(new SongBayesianRatingComparator());

        Song first = new Song("a","a","a");
        LinkedListNode<Rating> ratings_first = new LinkedListNode<>(new Rating("Marko", 5), null);
        ratings_first = new LinkedListNode<>(new Rating("Marija", 5), ratings_first);
        ratings_first = new LinkedListNode<>(new Rating("Cane", 5), ratings_first);
        ratings_first = new LinkedListNode<>(new Rating("Danijela", 5), ratings_first);
        addAllSongRatings(first, ratings_first);
        RatingPlaylist.addSong(first);

        Song second = new Song("b","b","b");
        LinkedListNode<Rating> ratings_second = new LinkedListNode<>(new Rating("Marko", 5), null);
        ratings_second = new LinkedListNode<>(new Rating("Marija", 5), ratings_second);
        ratings_second = new LinkedListNode<>(new Rating("Cane", 5), ratings_second);
        addAllSongRatings(second, ratings_second);
        RatingPlaylist.addSong(second);

        Song third = new Song("c","c","c");
        LinkedListNode<Rating> ratings_third = new LinkedListNode<>(new Rating("Marko", 5), null);
        ratings_third = new LinkedListNode<>(new Rating("Marija", 5), ratings_third);
        addAllSongRatings(third, ratings_third);
        RatingPlaylist.addSong(third);

        Song fourth = new Song("d","d","d");
        LinkedListNode<Rating> ratings_fourth = new LinkedListNode<>(new Rating("Marko", 5), null);
        addAllSongRatings(fourth, ratings_fourth);
        RatingPlaylist.addSong(fourth);


        LinkedListNode<Song> expected = new LinkedListNode<>(fourth,null);
        expected = new LinkedListNode<>(third,expected);
        expected = new LinkedListNode<>(second,expected);
        expected = new LinkedListNode<>(first,expected);


        LinkedListNode<Song> computed= RatingPlaylist.getSongList();
        System.out.println(expected);
        System.out.println(computed);
        compareLinkedLists(expected ,computed);




    }


    }
