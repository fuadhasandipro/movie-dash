package frames; 

import javax.swing.*;
import java.awt.*;
import entities.*;
import repositories.*;

public class ReviewPanel extends JPanel {

    private JLabel headerTitle;
    private JTable reviewTable;
    private JScrollPane reviewTableScrollPane;
    private ReviewRepo reviewRepo;

    public ReviewPanel() {
        setBackground(new Color(0x201f2d));
        setLayout(null);
        reviewRepo = new ReviewRepo();
        initializeComponents();
        initializeReviewTable();
    }

    private void initializeComponents() {
        headerTitle = new JLabel("User Reviews");
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        headerTitle.setBounds(30, 10, 350, 30);
        add(headerTitle);
    }

    private void initializeReviewTable() {
        String[] reviewColumns = {"Review ID", "Movie Name", "Customer", "Rating"};
        Review[] reviews = reviewRepo.getAllReviews();
        String[][] reviewData = new String[reviews.length][4];

        UserRepo userRp = new UserRepo();
        MovieRepo movieRp = new MovieRepo();

        for (int i = 0; i < reviews.length; i++) {
            if (reviews[i] != null) {
                User user = userRp.searchUserByUserId(reviews[i].getCustomerId());
                Movie movie = movieRp.searchMovieByMovieId(reviews[i].getMovieId());

                reviewData[i][0] = reviews[i].getReviewId();
                if (movie != null) {
                    reviewData[i][1] = movie.getTitle();
                } else {
                    reviewData[i][1] = reviews[i].getMovieId();
                }

                if (user != null) {
                    reviewData[i][2] = user.getName();
                } else {
                    reviewData[i][2] = reviews[i].getCustomerId();
                }
                reviewData[i][3] = String.valueOf(reviews[i].getRating());
            }
        }

        reviewTable = new JTable(reviewData, reviewColumns);
        reviewTable.setBackground(new Color(0xb8b3fc));
        reviewTableScrollPane = new JScrollPane(reviewTable);
        reviewTableScrollPane.setBounds(30, 50, 660, 500);
        reviewTable.setEnabled(false);
        add(reviewTableScrollPane);
    }

}
