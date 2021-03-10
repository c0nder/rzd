package org.company.app.ui;

import javax.swing.*;

public class FormClientTicketBuy {
    private JTextPane doYouWantToTextPane;
    private JTextPane pricePane;
    private JButton cancelButton;
    private JButton buyButton;

    public void FormClientTicketBuy(int price){
        pricePane.setText(String.format("Price: %d", price));
    }
}
