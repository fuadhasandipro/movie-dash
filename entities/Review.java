package entities;

public class Review {
    private String reviewId, movieId, customerId;
    private int rating;

    public Review() {}

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String toStringReview() {
        return reviewId + "," + movieId + "," + customerId + "," + rating + "\n";
    }

    public Review formReview(String str) {
        String[] parts = str.split(",");
        Review review = new Review();
        review.setReviewId(parts[0]);
        review.setMovieId(parts[1]);
        review.setCustomerId(parts[2]);
        review.setRating(Integer.parseInt(parts[3]));
        return review;
    }
}
