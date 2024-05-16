package repositories;
import entities.*;
import interfaces.*;

public class ReviewRepo implements IReviewRepo {

    private static final String DB_PATH = "repositories/db/reviews.txt";
    private static final int MAX_AMOUNT = 100; 

    public void addReview(Review review) {
        Review[] reviewList = getAllReviews();

        for (int i = 0; i < reviewList.length; i++) {
            if (reviewList[i] == null) {
                reviewList[i] = review;
                break;
            }
        }

        write(reviewList);
    }

    public void removeReview(String reviewId) {
        Review[] reviewList = getAllReviews();

        for (int i = 0; i < reviewList.length; i++) {
            if (reviewList[i] != null && reviewList[i].getReviewId().equals(reviewId)) {
                reviewList[i] = null;
                break;
            }
        }

        write(reviewList);
    }

    public void updateReview(Review review) {
        Review[] reviewList = getAllReviews();

        for (int i = 0; i < reviewList.length; i++) {
            if (reviewList[i] != null && reviewList[i].getReviewId().equals(review.getReviewId())) {
                reviewList[i] = review;
                break;
            }
        }

        write(reviewList);
    }

    public Review searchReviewById(String reviewId) {
        Review[] reviewList = getAllReviews();

        for (Review review : reviewList) {
            if (review != null && review.getReviewId().equals(reviewId)) {
                return review;
            }
        }

        return null;
    }

    public Review[] searchReviewByCustomerId(String customerId) {
        Review[] reviewList = getAllReviews();
        Review[] reviewListFromCustomerId = new Review[MAX_AMOUNT];

        int index = 0;
        
        for (Review review : reviewList) {
            if (review != null && review.getCustomerId().equals(customerId)) {
                reviewListFromCustomerId[index] = review;
                index++;
            }
        }

        return reviewListFromCustomerId;
    }

    public Review[] getReviewsByMovieId(String movieId) {
        Review[] reviewList = getAllReviews();
        Review[] reviewListFromMovieId = new Review[MAX_AMOUNT];

        int index = 0;
        
        for (Review review : reviewList) {
            if (review != null && review.getMovieId().equals(movieId)) {
                reviewListFromMovieId[index] = review;
                index++;
            }
        }

        return reviewListFromMovieId;
    }

    public Review hasUserReviewedMovie(String movieId, String customerId) {
        Review[] reviewList = getAllReviews();

        for (Review review : reviewList) {
            if (review != null && review.getMovieId().equals(movieId) && review.getCustomerId().equals(customerId)) {
                return review;
            }
        }

        return null;
    }

    public Review[] getAllReviews() {
        FileIO fileIO = new FileIO();
        String[] data = fileIO.readFile(DB_PATH);

        Review[] reviewList = new Review[MAX_AMOUNT];
        int index = 0;
        for (String str : data) {
            if (str != null && !str.isEmpty()) {
                Review review = new Review().formReview(str);
                reviewList[index] = review;
                index++;
            }
        }

        return reviewList;
    }

    public void write(Review[] reviewList) {
        String[] data = new String[MAX_AMOUNT];
        for (int i = 0; i < reviewList.length; i++) {
            if (reviewList[i] != null) {
                data[i] = reviewList[i].toStringReview();
            }
        }

        FileIO fileIO = new FileIO();
        fileIO.writeFile(data, DB_PATH);
    }
}
