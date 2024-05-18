package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import entities.*;
import repositories.*;

public class GenresPanel extends JPanel implements ActionListener {
    private JTextField searchField;
    private JLabel headerTitle;
    private JLabel searchLabel;
    private JButton searchButton;
    private JTable genresTable;
    private JScrollPane genresTableSP;

    private JLabel genreIdLabel;
    private JTextField genreIdField;
    private JLabel genreNameLabel;
    private JTextField genreNameField;

    private JButton addButton;
    private JButton fillFormButton;
    private JButton updateButton;

    private GenreRepo genreRepo;

    public GenresPanel() {
        genreRepo = new GenreRepo();
        setBackground(new Color(0x201f2d));
        setLayout(null);

        // Header components
        headerTitle = new JLabel("Genres");
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        headerTitle.setBounds(30, 10, 230, 30);
        add(headerTitle);

        searchField = new JTextField();
        searchField.setForeground(Color.white);
        searchField.setBackground(new Color(0x3a3854));
        searchField.setBounds(260, 10, 405, 25);
        add(searchField);

        searchLabel = new JLabel("Enter Genre to Search");
        searchLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        searchLabel.setForeground(Color.white);
        searchLabel.setBounds(260, 35, 200, 25);
        add(searchLabel);

        ImageIcon searchIcon = new ImageIcon(getClass().getResource("../assets/img/dashboard/searchicon.png"));
        searchButton = new JButton(searchIcon);
        searchButton.setBounds(670, 10, 30, 25);
        add(searchButton);

        genreIdLabel = createLabel("Genre ID");
        genreIdLabel.setBounds(30, 70, 60, 25);
        add(genreIdLabel);

        genreIdField = createTextField();
        genreIdField.setBounds(100, 70, 120, 25);
        add(genreIdField);

        genreNameLabel = createLabel("Genre Name");
        genreNameLabel.setBounds(240, 70, 100, 25);
        add(genreNameLabel);

        genreNameField = createTextField();
        genreNameField.setBounds(350, 70, 120, 25);
        add(genreNameField);

        addButton = new JButton("Add Genre");
        addButton.setBounds(30, 140, 120, 25);
        addButton.addActionListener(this);
        add(addButton);

        fillFormButton = new JButton("Fill Form");
        fillFormButton.setBounds(160, 140, 120, 25);
        fillFormButton.addActionListener(this);
        add(fillFormButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(290, 140, 120, 25);
        updateButton.addActionListener(this);
        add(updateButton);

        String[] genreColumns = {"Genre ID", "Genre Name"};
        Genre[] genres = genreRepo.getAllGenres();
        String[][] genreData = new String[genres.length][2];

        for (int i = 0; i < genres.length; i++) {
            if (genres[i] != null) {
                genreData[i][0] = genres[i].getGenreId();
                genreData[i][1] = genres[i].getGenreName();
            }
        }

        genresTable = new JTable(genreData, genreColumns);
        genresTable.setBackground(new Color(0xb8b3fc));
        genresTableSP = new JScrollPane(genresTable);
        genresTableSP.setBounds(30, 180, 720, 325);
        add(genresTableSP);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String genreId = genreIdField.getText().trim();
            String genreName = genreNameField.getText().trim();

            if (genreRepo.searchGenreById(genreId) != null) {
                JOptionPane.showMessageDialog(this, "Genre already exists.", "Adding Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Genre genre = new Genre();
            genre.setGenreId(genreId);
            genre.setGenreName(genreName);

            genreRepo.addGenre(genre);
            JOptionPane.showMessageDialog(this, "Genre added successfully!");

            genreIdField.setText("");
            genreNameField.setText("");
            refreshTable();
        }

        if (e.getSource() == fillFormButton) {
            int selectedRow = genresTable.getSelectedRow();
            if (selectedRow != -1) {
                String genreId = genresTable.getValueAt(selectedRow, 0).toString();
                String genreName = genresTable.getValueAt(selectedRow, 1).toString();

                genreIdField.setText(genreId);
                genreNameField.setText(genreName);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to fill the form.");
            }
        }

        if (e.getSource() == updateButton) {
            int selectedRow = genresTable.getSelectedRow();
            if (selectedRow != -1) {
                String genreId = genresTable.getValueAt(selectedRow, 0).toString();
                String genreName = genreNameField.getText().trim();

                Genre genre = new Genre();
                genre.setGenreId(genreId);
                genre.setGenreName(genreName);

                genreRepo.updateGenre(genre);
                JOptionPane.showMessageDialog(this, "Genre updated successfully!");

                genreIdField.setText("");
                genreNameField.setText("");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update.");
            }
        }
    }

    private void refreshTable() {
        String[] genreColumns = {"Genre ID", "Genre Name"};
        Genre[] genres = genreRepo.getAllGenres();
        String[][] genreData = new String[genres.length][2];

        for (int i = 0; i < genres.length; i++) {
            if (genres[i] != null) {
                genreData[i][0] = genres[i].getGenreId();
                genreData[i][1] = genres[i].getGenreName();
            }
        }

        remove(genresTableSP);

        genresTable = new JTable(genreData, genreColumns);
        genresTable.setBackground(new Color(0xb8b3fc));
        genresTableSP = new JScrollPane(genresTable);
        genresTableSP.setBounds(30, 180, 720, 325);
        add(genresTableSP);

        revalidate();
        repaint();
    }
}
