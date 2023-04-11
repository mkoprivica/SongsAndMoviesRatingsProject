package ratings;

import ratings.datastructures.LinkedListNode;

public class Ratable {

    private String title;
    private LinkedListNode<Rating> ratings = null;
    private double times_rated = 0;
    private double ratings_added = 0;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void addRating (Rating rating){
        if (this.ratings == null){
            this.ratings = new LinkedListNode<>(rating,null);
            if (rating.getRating() >= 1 && rating.getRating() <= 5 ){
                ratings_added += rating.getRating();
                times_rated++;
            }
        }else{
            if (!didReviewerRateSong(rating.getReviewerID())){
                this.ratings.append(rating);
                if (rating.getRating() >= 1 && rating.getRating() <= 5 ){
                    ratings_added += rating.getRating();
                    times_rated++;
                }
            }

        }

    }

    public LinkedListNode<Rating> getRatings(){
        return ratings;
    }

    public double averageRating(){
        if (ratings_added == 0){
            return 0;
        }else{
            return  ratings_added / times_rated;
        }
    }
    public double bayesianAverageRating(int numberOf, int valueOf){
        if (ratings == null && numberOf == 0){
            return 0.0;
        }else{
            double total_ratings = ratings_added + (numberOf * valueOf);
            double total_times_rated = times_rated + numberOf;
            return total_ratings/total_times_rated;
        }
    }
    public boolean didReviewerRateSong(String id) {
        if (ratings == null){
            return false;
        }else{
            return didReviewerHelper(ratings,id);
        }


    }
    private boolean didReviewerHelper(LinkedListNode<Rating> node,String id){
        if (node == null){
            return false;
        }else{
            if(node.getValue().getReviewerID().equals(id)){
                return true;
            }else{
                return didReviewerHelper(node.getNext(), id);
            }
        }
    }
    public void removeRatingByReviewer(Reviewer reviewer){
        String reviewer_id = reviewer.getReviewerID();
        if (didReviewerRateSong(reviewer_id)){
            if (this.ratings.getNext() == null){
                this.ratings = null;
            }else {
                removeRatingHelper(this.ratings , reviewer_id);
            }
        }else {
            return;
        }
    }
    private void removeRatingHelper(LinkedListNode<Rating> node,String id){
        if (node.getValue().getReviewerID().equals(id)){
            this.ratings = node.getNext();
        } else if (node.getNext().getValue().getReviewerID().equals(id)) {
            node.setNext(node.getNext().getNext());
        }else{
            removeRatingHelper(node.getNext(),id);
        }

    }

}
