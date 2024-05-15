package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MoviesPanel extends  JPanel  {

	private JTextField searchField;
	private JLabel headerTitle;
	private JLabel searchLabel;
	private JButton searchButton;

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
    }

}