package frames;

import javax.swing.*;
import java.awt.*;

public class CreditsPage extends JPanel {

    public CreditsPage() {
        setBackground(new Color(0x201f2d));
        setLayout(null);

        JLabel headerTitle = new JLabel("Project Credits");
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.PLAIN, 16));
        add(headerTitle);
        headerTitle.setBounds(30, 40, 300, headerTitle.getPreferredSize().height);

        JPanel creditsContentPanel = createCreditsContentPanel();
        add(creditsContentPanel);
    }

    private JPanel createCreditsContentPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x3a3854));
        panel.setLayout(null);

        String[] creditsInfo = {
            "Name: Fuad Hasan Dipro",
            "ID: 23-51947-2",
            "GitHub: https://github.com/fuadhasandipro",
            "University: American International University - Bangladesh",
            "Department: CSE",
            "Semester: 4th"
        };

        int yOffset = 10;
        for (String info : creditsInfo) {
            JLabel infoLabel = new JLabel(info);
            infoLabel.setForeground(Color.WHITE);
            infoLabel.setBounds(10, yOffset, 640, 20);
            panel.add(infoLabel);
            yOffset += 30;
        }

        panel.setBounds(30, 105, 660, 200);
        return panel;
    }
}
