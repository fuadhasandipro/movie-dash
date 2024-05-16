package interfaces;
import entities.Review;

public interface IReviewRepo {
    void addReview(Review review);
    void removeReview(String reviewId);
    void updateReview(Review review);
    Review searchReviewById(String reviewId);
    Review[] searchReviewByCustomerId(String customerId);
    Review[] getReviewsByMovieId(String movieId);
    Review hasUserReviewedMovie(String movieId, String customerId);
    Review[] getAllReviews();
}
