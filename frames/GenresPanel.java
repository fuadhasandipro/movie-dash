package frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

import entities.*;
import repositories.*;

public class GenresPanel extends JPanel implements ActionListener {

    private JLabel headerTitle;

    public GenresPanel() {
        setBackground(new Color(0x201f2d));
        setLayout(null);

        headerTitle = new JLabel("Genres");
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        headerTitle.setBounds(30, 10, 230, 30);
        add(headerTitle);
    }


    public void actionPerformed(ActionEvent e) {

    }
}
