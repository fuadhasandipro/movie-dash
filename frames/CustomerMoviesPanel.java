package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import entities.*;
import repositories.*;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class CustomerMoviesPanel extends JPanel {
    private User currentUser;
    private JTextField searchField;
	private JLabel headerTitle;
	private JLabel searchLabel;
	private JButton searchButton;
	private JPanel moviePanel;
    private JPanel buttonPanel;
    private JButton submitButton;
    private JButton reviewButton;
    private ButtonGroup starGroup;
    private JDialog reviewDialog;
    private JPanel card;
    private JFrame parentFrame;

    public CustomerMoviesPanel(User currentUser, JFrame parentFrame) {

		searchField = new JTextField();
		headerTitle = new JLabel();
		searchLabel = new JLabel();
		searchButton = new JButton();
        this.currentUser = currentUser;
        this.parentFrame = parentFrame;

        setBackground(new Color(0x201f2d));
        setLayout(null);

        //---- searchField ----
        searchField.setForeground(Color.white);
        searchField.setBackground(new Color(0x3a3854));
        add(searchField);
        searchField.setBounds(260, 45, 405, 25);

        //---- headerTitle ----
        headerTitle.setText("Listed Movies for " + currentUser.getName());
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.PLAIN, 16));
        add(headerTitle);
			headerTitle.setBounds(30, 40, 230, headerTitle.getPreferredSize().height);

        //---- searchLabel ----
        searchLabel.setText("Enter Movie to Search");
        searchLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        searchLabel.setForeground(Color.white);
        add(searchLabel);
        searchLabel.setBounds(new Rectangle(new Point(260, 25), searchLabel.getPreferredSize()));

        //---- searchButton ----
        ImageIcon searchIcon = new ImageIcon(getClass().getResource("../assets/img/dashboard/searchicon.png"));
        searchButton.setIcon(searchIcon);
        add(searchButton);
        searchButton.setBounds(670, 45, 30, 25);

        MovieRepo movieRepo = new MovieRepo();
        moviePanel = new JPanel(new GridLayout(0, 3, 10, 10)); 

        Movie[] movies = movieRepo.getAllMovies();

        for (Movie movie : movies) {
            if(movie != null) {
                JPanel card = createMovieCard(movie);
                moviePanel.add(card);
            }
        }

        JScrollPane scrollPane = new JScrollPane(moviePanel);

        add(scrollPane);
        scrollPane.setBounds(30, 105, 660, 425);
    }

    private JPanel createMovieCard(Movie movie) {
        GenreRepo genreRepo = new GenreRepo();

        card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1), 
                BorderFactory.createEmptyBorder(4, 4, 4, 4)));

        JLabel titleLabel = new JLabel(movie.getTitle());
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLabel);

        Genre genreInfo = genreRepo.searchGenreById(movie.getGenreId());
        JLabel genreLabel = new JLabel("Genre: " + genreInfo.getGenreName());
        genreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(genreLabel);

        JLabel yearLabel = new JLabel("Year: " + movie.getReleaseDate().format(DateTimeFormatter.ofPattern("yyyy")));
        yearLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(yearLabel);

        card.add(Box.createVerticalStrut(10));

        ImageIcon posterIcon = new ImageIcon("./assets/img/posters/" + movie.getPosterImage());
        Image scaledPoster = posterIcon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
        JLabel posterLabel = new JLabel(new ImageIcon(scaledPoster));
        posterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(posterLabel);

        card.add(Box.createVerticalStrut(10));

        ReviewRepo newRp = new ReviewRepo();
        Review existingReview = newRp.hasUserReviewedMovie(movie.getMovieId(), currentUser.getUserId());

        reviewButton = new JButton();

        if(existingReview != null) {
            reviewButton.setText("Update Rating");
        } else {
            reviewButton.setText("Add Rating");  
        }

        reviewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reviewButton.setActionCommand(movie.getMovieId());

        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movieId = e.getActionCommand();
                showReviewDialog(movieId);
            }
        });
        card.add(reviewButton);


        card.add(Box.createVerticalStrut(10));

        JButton reserveButton = new JButton("Reserve Screening");
        reserveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookingStepsDialog bookingDialog = new BookingStepsDialog(parentFrame);
                bookingDialog.setVisible(true);
            }
        });
        card.add(reserveButton);

        return card;
    }

    private void showReviewDialog(String movieId) {

        reviewDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Rate Movie", true);
        reviewDialog.setLayout(new GridLayout(2, 1));

        JPanel starsPanel = new JPanel();
        starGroup = new ButtonGroup();

        for (int i = 1; i <= 10; i++) {
            JRadioButton starButton = new JRadioButton(Integer.toString(i));
            starButton.setActionCommand(Integer.toString(i));
            starGroup.add(starButton);
            starsPanel.add(starButton);
        }

        buttonPanel = new JPanel();
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReviewRepo newRp = new ReviewRepo();
                Review existingReview = newRp.hasUserReviewedMovie(movieId, currentUser.getUserId());

                if (starGroup.getSelection() != null) {
                    String selectedRating = starGroup.getSelection().getActionCommand();

                    if (existingReview == null) {
                        Review review = new Review();

                        review.setReviewId(UUID.randomUUID().toString());
                        review.setMovieId(movieId);
                        review.setCustomerId(currentUser.getUserId());
                        review.setRating(Integer.parseInt(selectedRating));

                        newRp.addReview(review);

                        JOptionPane.showMessageDialog(reviewDialog, "You rated the movie " + selectedRating + " stars.", "Rating Submitted ", JOptionPane.INFORMATION_MESSAGE);

                    } else {
                        existingReview.setRating(Integer.parseInt(selectedRating));
                        newRp.updateReview(existingReview);
                        JOptionPane.showMessageDialog(reviewDialog, "Your rating has been updated to " + selectedRating + " stars.", "Rating Updated", JOptionPane.INFORMATION_MESSAGE);

                        card.revalidate();
                        card.repaint();
                    }

                    reviewDialog.dispose();

                } else {
                    JOptionPane.showMessageDialog(reviewDialog, "Please choose a rating");
                }
            }
        });
        buttonPanel.add(submitButton);

        reviewDialog.add(starsPanel);
        reviewDialog.add(buttonPanel);
        reviewDialog.pack();
        reviewDialog.setLocationRelativeTo(this);
        reviewDialog.setVisible(true);
    }

}