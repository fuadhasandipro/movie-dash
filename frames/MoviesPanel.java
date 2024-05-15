package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import entities.*;
import repositories.*;

public class MoviesPanel extends  JPanel  {

	private JTextField searchField;
	private JLabel headerTitle;
	private JLabel searchLabel;
	private JButton searchButton;
    private JTable movieTable;
    private JScrollPane movieTableSP;

    public MoviesPanel() {

		searchField = new JTextField();
		headerTitle = new JLabel();
		searchLabel = new JLabel();
		searchButton = new JButton();

        setBackground(new Color(0x201f2d));
        setLayout(null);

        //---- searchField ----
        searchField.setForeground(Color.white);
        searchField.setBackground(new Color(0x3a3854));
        add(searchField);
        searchField.setBounds(260, 45, 405, 25);

        //---- headerTitle ----
        headerTitle.setText("Movies");
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", headerTitle.getFont().getStyle() | Font.BOLD, headerTitle.getFont().getSize() + 12));
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

        String movieColumns[]={"Movie ID", "Genre ID", "Director ID", "Title", "Release Date", "Duration", "Poster Image"};

        MovieRepo arp = new MovieRepo();
		Movie[] adminList = arp.getAllMovies();

		String movieData[][]=new String[adminList.length][8];

		for(int i=0;i<adminList.length;i++) {
			if(adminList[i]!=null) {
				movieData[i][0]=adminList[i].getMovieId();
				movieData[i][1]=adminList[i].getGenreId();
				movieData[i][2]=adminList[i].getDirectorId();
				movieData[i][3]=adminList[i].getTitle();
				movieData[i][4]=adminList[i].getReleaseDate().toString();
				movieData[i][5]=String.valueOf(adminList[i].getDuration());
				movieData[i][6]=adminList[i].getPosterImage();
			}
		}

		movieTable = new JTable(movieData,movieColumns);
        movieTable.setBackground(new Color(0xb8b3fc));

		this.movieTableSP = new JScrollPane(movieTable);
		this.movieTableSP.setBounds(30, 105, 660, 325);
		this.movieTable.setEnabled(false);

		add(movieTableSP);

        
        JButton addButton = createButton("add.png", "Add");
        addButton.setBounds(30, 440, 80, 80); 
        add(addButton);

        JButton editButton = createButton("update.png", "Edit");
        editButton.setBounds(120, 440, 80, 80); 
        add(editButton);

        JButton refreshButton = createButton("refresh.png", "Refresh");
        refreshButton.setBounds(210, 440, 80, 80);
        add(refreshButton);
    }

    public JButton createButton(String iconFilename, String buttonText) {
        JButton button = new JButton();

        ImageIcon icon = new ImageIcon(getClass().getResource("../assets/img/movies/" + iconFilename));
        Image image = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(image));

        button.setText(buttonText);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        return button;
    }

}