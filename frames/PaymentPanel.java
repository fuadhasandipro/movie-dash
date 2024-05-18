package frames;

import javax.swing.*;
import java.awt.*;
import entities.*;
import repositories.*;

public class PaymentPanel extends JPanel {

    private JLabel headerTitle;
    private JTable paymentTable;
    private JScrollPane paymentTableScrollPane;
    private PaymentRepo paymentRepo;

    public PaymentPanel() {
        setBackground(new Color(0x201f2d));
        setLayout(null);
        paymentRepo = new PaymentRepo();
        initializeComponents();
        initializePaymentTable();
    }

    private void initializeComponents() {
        headerTitle = new JLabel("Transactions");
        headerTitle.setForeground(new Color(0xb8b3fc));
        headerTitle.setFont(new Font("Verdana", Font.BOLD, 24));
        headerTitle.setBounds(30, 10, 250, 30);
        add(headerTitle);
    }

    private void initializePaymentTable() {
        String[] paymentColumns = {"Payment ID", "Reservation ID", "Amount", "Payment Date"};
        Payment[] payments = paymentRepo.getAllPayments();
        String[][] paymentData = new String[payments.length][4];

        for (int i = 0; i < payments.length; i++) {
            if (payments[i] != null) {
                paymentData[i][0] = payments[i].getPaymentId();
                paymentData[i][1] = payments[i].getReservationId();
                paymentData[i][2] = String.valueOf(payments[i].getAmount());
                paymentData[i][3] = payments[i].getPaymentDate().toString();
            }
        }

        paymentTable = new JTable(paymentData, paymentColumns);
        paymentTable.setBackground(new Color(0xb8b3fc));
        paymentTableScrollPane = new JScrollPane(paymentTable);
        paymentTableScrollPane.setBounds(30, 50, 660, 500);
        paymentTable.setEnabled(false);
        add(paymentTableScrollPane);
    }
}
