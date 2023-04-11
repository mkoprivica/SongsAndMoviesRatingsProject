package ratings;

import ratings.datastructures.LinkedListNode;

public class Song extends Ratable{

    private String artist;
    private String id;



    public Song (String title, String artist, String id){
        super.setTitle(title);
        this.setArtist(artist);
        this.setSongID(id);

    }

    public String getArtist(){
        return artist;
    }
    public void setArtist(String artist){
        this.artist = artist;
    }
    public String getSongID(){
        return id;
    }
    public void setSongID(String id){
        this.id = id;
    }


}
