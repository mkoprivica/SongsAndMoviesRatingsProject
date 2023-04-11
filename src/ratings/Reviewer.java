package ratings;

import ratings.Rating;

public class Reviewer {
    private String id;

    public Reviewer (String id){
        this.setReviewerID(id);
    }
    public String getReviewerID(){
        return id;
    }
    public void setReviewerID(String id){
        this.id = id;
    }
    public Rating rateSong (int rating){
        Rating new_rate = new Rating(this.id,rating);
        return new_rate;
    }
}
