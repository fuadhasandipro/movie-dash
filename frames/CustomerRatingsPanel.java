package frames;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import entities.*;
import repositories.*;

public class CustomerRatingsPanel extends JPanel {
    private User currentUser;
    private JPanel reviewPanel;
    private ReviewRepo reviewRepo;
    private MovieRepo movieRepo;

    public CustomerRatingsPanel(User currentUser) {
        this.currentUser = currentUser;
        this.reviewRepo = new ReviewRepo();
        this.movieRepo = new MovieRepo();

        setBackground(new Color(0x201f2d));
        setLayout(null);

        JLabel headerTitle = new JLabel();
        headerTitle.setText("Reviews by " + currentUser.getName());
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.PLAIN, 16));
        add(headerTitle);
        headerTitle.setBounds(30, 40, 300, headerTitle.getPreferredSize().height);

        reviewPanel = createReviewPanel();
        JScrollPane scrollPane = new JScrollPane(reviewPanel);
        scrollPane.setBounds(30, 105, 660, 325);
        add(scrollPane);
    }

    private JPanel createReviewPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x3a3854));
        panel.setLayout(null);

        Review[] reviews = reviewRepo.searchReviewByCustomerId(currentUser.getUserId());

        if (reviews.length == 0) {
            JLabel noReviewLabel = new JLabel("No reviews found.");
            noReviewLabel.setForeground(Color.WHITE);
            noReviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            noReviewLabel.setBounds(0, 0, 660, 30);
            panel.add(noReviewLabel);
        } else {
            int yOffset = 10;
            for (Review review : reviews) {
                if (review != null) {
                    Movie movie = movieRepo.searchMovieByMovieId(review.getMovieId());
                    if (movie != null) {
                        JPanel reviewCard = createReviewCard(review, movie);
                        reviewCard.setBounds(10, yOffset, 640, 100);
                        panel.add(reviewCard);
                        yOffset += 110;
                    } else {
                        System.err.println("Movie not found for review ID: " + review.getReviewId());
                    }
                }
            }
            panel.setPreferredSize(new Dimension(660, yOffset));
        }
        return panel;
    }

    private JPanel createReviewCard(Review review, Movie movie) {
        JPanel card = new JPanel();
        card.setLayout(null);
        card.setBackground(new Color(0x2e2d3d));

        JLabel movieLabel = new JLabel("Movie: " + movie.getTitle());
        movieLabel.setForeground(Color.WHITE);
        movieLabel.setBounds(10, 10, 300, 20);
        card.add(movieLabel);

        JLabel ratingLabel = new JLabel("Rating: " + review.getRating());
        ratingLabel.setForeground(Color.WHITE);
        ratingLabel.setBounds(10, 40, 100, 20);
        card.add(ratingLabel);

        return card;
    }
}
