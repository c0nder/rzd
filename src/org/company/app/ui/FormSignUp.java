package org.company.app.ui;

import org.company.app.util.BaseSubForm;

import javax.swing.*;

public class FormSignUp extends BaseSubForm<FormSignIn> {
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JTextField textField3;
    private JTextField textField4;
    private JButton ButtonCancel;
    private JButton ButtonSignUp;
    private JPanel FormSignUp;

    private FormSignIn formSignIn;

    public FormSignUp(FormSignIn formSignIn) {
        super(formSignIn);

        this.formSignIn = formSignIn;
        setContentPane(FormSignUp);
        setVisible(true);

        initButtons();
    }

    private void initButtons() {
        ButtonCancel.addActionListener(e -> {
            closeSubForm();
        });
    }



    @Override
    public int getFormWidth() {
        return 500;
    }

    @Override
    public int getFormHeight() {
        return 500;
    }
}
