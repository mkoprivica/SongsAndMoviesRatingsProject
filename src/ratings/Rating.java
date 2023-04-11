package ratings;

public class Rating {
    private String id;
    private int rating;

    public Rating (String id, int rating){
        this.setReviewerID(id);
        this.setRating(rating);
    }
    public String getReviewerID(){
        return id;
    }
    public void setReviewerID(String id){
        this.id = id;
    }

    public int getRating(){
        return rating;
    }
    public void setRating(int rating) {
        if (rating <= 0 || rating > 5) {
            this.rating = -1;
        }else{
            this.rating = rating;
        }
    }
}
