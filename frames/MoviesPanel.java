package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;
import javax.imageio.ImageIO;

import entities.*;
import repositories.*;

public class MoviesPanel extends JPanel implements ActionListener {

    private static final String POSTERS_DIRECTORY = "assets/img/posters/";

    private JTextField searchField;
    private JLabel headerTitle;
    private JLabel searchLabel;
    private JButton searchButton;
    private JTable movieTable;
    private JScrollPane movieTableSP;
    private JLabel movieIdLabel;
    private JTextField movieIdField;
    private JLabel genreIdLabel;
    private JTextField genreIdField;
    private JLabel titleLabel;
    private JTextField titleField;
    private JLabel descriptionLabel;
    private JTextField descriptionField;
    private JLabel releaseDateLabel;
    private JTextField releaseDateField;
    private JLabel durationLabel;
    private JTextField durationField;
    private JLabel posterImageLabel;
    private JButton uploadButton;
    private File posterImageFile;
    private JButton addButton;
    private MovieRepo movieRepo;
    private JButton fillFormButton;
    private JButton updateButton;
    private GenreRepo genreRepo;

    public MoviesPanel() {
        genreRepo = new GenreRepo();
        movieRepo = new MovieRepo();
        setBackground(new Color(0x201f2d));
        setLayout(null);

        // Move search and movie label to the top
        headerTitle = new JLabel("Movies");
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        headerTitle.setBounds(30, 10, 230, 30);
        add(headerTitle);

        searchField = new JTextField();
        searchField.setForeground(Color.white);
        searchField.setBackground(new Color(0x3a3854));
        searchField.setBounds(260, 10, 405, 25);
        add(searchField);

        searchLabel = new JLabel("Enter Movie to Search");
        searchLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        searchLabel.setForeground(Color.white);
        searchLabel.setBounds(260, 35, 150, 25);
        add(searchLabel);

        ImageIcon searchIcon = new ImageIcon(getClass().getResource("../assets/img/dashboard/searchicon.png"));
        searchButton = new JButton(searchIcon);
        searchButton.setBounds(670, 10, 30, 25);
        add(searchButton);

        // Movie fields setup
        movieIdLabel = createLabel("Movie ID");
        movieIdLabel.setBounds(30, 70, 60, 25);
        add(movieIdLabel);

        movieIdField = createTextField();
        movieIdField.setBounds(100, 70, 120, 25);
        add(movieIdField);

        genreIdLabel = createLabel("Genre ID");
        genreIdLabel.setBounds(240, 70, 60, 25);
        add(genreIdLabel);

        genreIdField = createTextField();
        genreIdField.setBounds(310, 70, 120, 25);
        add(genreIdField);

        titleLabel = createLabel("Title");
        titleLabel.setBounds(450, 70, 60, 25);
        add(titleLabel);

        titleField = createTextField();
        titleField.setBounds(520, 70, 120, 25);
        add(titleField);

        descriptionLabel = createLabel("Description");
        descriptionLabel.setBounds(30, 105, 80, 25);
        add(descriptionLabel);

        descriptionField = createTextField();
        descriptionField.setBounds(120, 105, 520, 25);
        add(descriptionField);

        releaseDateLabel = createLabel("Release");
        releaseDateLabel.setBounds(30, 140, 80, 25);
        add(releaseDateLabel);

        releaseDateField = createTextField();
        releaseDateField.setBounds(120, 140, 120, 25);
        add(releaseDateField);

        durationLabel = createLabel("Duration (min)");
        durationLabel.setBounds(260, 140, 100, 25);
        add(durationLabel);

        durationField = createTextField();
        durationField.setBounds(370, 140, 120, 25);
        add(durationField);

        posterImageLabel = createLabel("Poster");
        posterImageLabel.setBounds(510, 140, 80, 25);
        add(posterImageLabel);

        uploadButton = new JButton("Upload");
        uploadButton.setBounds(600, 140, 80, 25);
        uploadButton.addActionListener(e -> uploadImage());
        add(uploadButton);

        addButton = new JButton("Add Movie");
        addButton.setBounds(30, 175, 100, 25); 
        addButton.addActionListener(this);
        add(addButton);

        fillFormButton = new JButton("Fill Form");
        fillFormButton.setBounds(140, 175, 120, 25);
        fillFormButton.addActionListener(this);
        add(fillFormButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(270, 175, 120, 25);
        updateButton.addActionListener(this);
        add(updateButton);

        String[] movieColumns = {"Movie ID", "Genre ID", "Title", "Description", "Release Date", "Duration", "Poster Image"};
        Movie[] movies = movieRepo.getAllMovies();
        String[][] movieData = new String[movies.length][7];



        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null) {
                movieData[i][0] = movies[i].getMovieId();
                
                movieData[i][1] = movies[i].getGenreId();
                movieData[i][2] = movies[i].getTitle();
                movieData[i][3] = movies[i].getDescription();
                movieData[i][4] = movies[i].getReleaseDate().toString();
                movieData[i][5] = String.valueOf(movies[i].getDuration());
                movieData[i][6] = movies[i].getPosterImage();
            }
        }

        movieTable = new JTable(movieData, movieColumns);
        movieTable.setBackground(new Color(0xb8b3fc));
        movieTableSP = new JScrollPane(movieTable);
        movieTableSP.setBounds(30, 220, 660, 325);
        // movieTable.setEnabled(false);
        add(movieTableSP);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.white);
        label.setFont(new Font("Rubik Light", Font.BOLD, 14));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(new Color(0x3a3854));
        textField.setForeground(Color.white);
        textField.setCaretColor(Color.white);
        return textField;
    }

    private JButton createButton(String iconFilename, String buttonText) {
        JButton button = new JButton();
        ImageIcon icon = new ImageIcon(getClass().getResource("../assets/img/movies/" + iconFilename));
        Image image = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));
        button.setText(buttonText);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String movieId = movieIdField.getText().trim();
            String genreId = genreIdField.getText().trim();
            String title = titleField.getText().trim();
            String description = descriptionField.getText().trim();
            String releaseDateStr = releaseDateField.getText().trim();
            String durationStr = durationField.getText().trim();

            LocalDate releaseDate;
            int duration;

            if (movieRepo.searchMovieByMovieId(movieId) != null) {
                JOptionPane.showMessageDialog(this, "Movie ID already exists.", "Adding Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (genreRepo.searchGenreById(genreId) == null) {
                JOptionPane.showMessageDialog(this, "Genre doesn't exists.", "Adding Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                releaseDate = LocalDate.parse(releaseDateStr);
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                duration = Integer.parseInt(durationStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Duration must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (posterImageFile == null) {
                JOptionPane.showMessageDialog(this, "Please upload a poster image.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String originalPosterImageName = posterImageFile.getName();

            Movie movie = new Movie();
            movie.setMovieId(movieId);
            movie.setGenreId(genreId);
            movie.setTitle(title);
            movie.setDescription(description);
            movie.setReleaseDate(releaseDate);
            movie.setDuration(duration);
            movie.setPosterImage(originalPosterImageName);

            movieRepo.addMovie(movie);
            JOptionPane.showMessageDialog(this, "Movie added successfully!");

            movieIdField.setText("");
            genreIdField.setText("");
            titleField.setText("");
            descriptionField.setText("");
            releaseDateField.setText("");
            durationField.setText("");
            refreshTable();
        }

        if (e.getSource() == fillFormButton) {
            int selectedRow = movieTable.getSelectedRow();
            if (selectedRow != -1) {
                String movieId = movieTable.getValueAt(selectedRow, 0).toString();
                String genreId = movieTable.getValueAt(selectedRow, 1).toString();
                String title = movieTable.getValueAt(selectedRow, 2).toString();
                String description = movieTable.getValueAt(selectedRow, 3).toString();
                String releaseDateStr = movieTable.getValueAt(selectedRow, 4).toString();
                String durationStr = movieTable.getValueAt(selectedRow, 5).toString();

                genreIdField.setText(genreId);
                titleField.setText(title);
                descriptionField.setText(description);
                releaseDateField.setText(releaseDateStr);
                durationField.setText(durationStr);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a movie to update.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == updateButton) {
            int selectedRow = movieTable.getSelectedRow();
            if (selectedRow != -1) {
                String movieId = movieTable.getValueAt(selectedRow, 0).toString();
                String genreId = genreIdField.getText().trim();
                String title = titleField.getText().trim();
                String description = descriptionField.getText().trim();
                String releaseDateStr = releaseDateField.getText().trim();
                String durationStr = durationField.getText().trim();
                String exPosterImage = movieTable.getValueAt(selectedRow, 6).toString();

                LocalDate releaseDate;
                int duration;

            if (genreRepo.searchGenreById(genreId) == null) {
                JOptionPane.showMessageDialog(this, "Genre doesn't exists.", "Update Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

                try {
                    releaseDate = LocalDate.parse(releaseDateStr);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    duration = Integer.parseInt(durationStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Duration must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String originalPosterImageName = "";

                if (posterImageFile != null) {
                    originalPosterImageName = posterImageFile.getName();
                } else {
                    originalPosterImageName = exPosterImage;
                }

                Movie movie = new Movie();
                movie.setMovieId(movieId);
                movie.setGenreId(genreId);
                movie.setTitle(title);
                movie.setDescription(description);
                movie.setReleaseDate(releaseDate);
                movie.setDuration(duration);
                
                movie.setPosterImage(originalPosterImageName);

                movieRepo.updateMovie(movie);
                JOptionPane.showMessageDialog(this, "Movie updated successfully!");

                movieIdField.setText("");
                genreIdField.setText("");
                titleField.setText("");
                descriptionField.setText("");
                releaseDateField.setText("");
                durationField.setText("");
                posterImageFile = null;

                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a movie to update.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            posterImageFile = fileChooser.getSelectedFile();
            try {
                BufferedImage originalImage = ImageIO.read(posterImageFile);
                BufferedImage resizedImage = new BufferedImage(150, 200, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, 150, 200, null);
                g.dispose();

                String outputDir = POSTERS_DIRECTORY;
                if (!Files.exists(Paths.get(outputDir))) {
                    Files.createDirectories(Paths.get(outputDir));
                }

                File outputfile = new File(outputDir + posterImageFile.getName());
                ImageIO.write(resizedImage, "jpg", outputfile);
                JOptionPane.showMessageDialog(this, "Image uploaded successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error uploading image.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void refreshTable() {
        Movie[] movies = movieRepo.getAllMovies();
        String[][] movieData = new String[movies.length][7];

        GenreRepo genreRepo = new GenreRepo();

        for (int i = 0; i < movies.length; i++) {
            if (movies[i] != null) {
                movieData[i][0] = movies[i].getMovieId();
                
                movieData[i][1] = movies[i].getGenreId();
                movieData[i][2] = movies[i].getTitle();
                movieData[i][3] = movies[i].getDescription();
                movieData[i][4] = movies[i].getReleaseDate().toString();
                movieData[i][5] = String.valueOf(movies[i].getDuration());
                movieData[i][6] = movies[i].getPosterImage();
            }
        }

        String[] movieColumns = {"Movie ID", "Genre", "Title", "Description", "Release Date", "Duration", "Poster Image"};

        remove(movieTableSP);

        movieTable = new JTable(movieData, movieColumns);
        movieTable.setBackground(new Color(0xb8b3fc));
        movieTableSP = new JScrollPane(movieTable);
        movieTableSP.setBounds(30, 220, 660, 325);
        add(movieTableSP);

        revalidate();
        repaint();
    }


}
