package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.UserEntity;
import org.company.app.database.manager.UserEntityManager;
import org.company.app.util.BaseSubForm;
import org.company.app.util.DialogUtil;
import org.company.app.util.Registry;

import javax.swing.*;
import java.sql.SQLException;

public class FormSignUp extends BaseSubForm<MainForm> {
    private JTextField FieldName;
    private JTextField FieldSurname;
    private JTextField FieldEmail;
    private JButton ButtonCancel;
    private JButton ButtonSignUp;
    private JPanel FormSignUp;
    private JTextField FieldPassport;
    private JPasswordField FieldPassword;

    private final UserEntityManager userEntityManager = Application.getUserEntityManager();
    private final Registry container = Application.getContainer();

    public FormSignUp(MainForm mainForm) {
        super(mainForm);

        setContentPane(FormSignUp);
        setVisible(true);

        initButtons();
    }

    private void initButtons() {
        ButtonCancel.addActionListener(e -> {
            closeSubForm();
        });

        ButtonSignUp.addActionListener(e -> {
            String firstName = FieldName.getText();
            String lastName = FieldSurname.getText();
            String passport = FieldPassport.getText();
            String password = FieldPassword.getText();
            String email = FieldEmail.getText();

            if (firstName.isEmpty()
                    || lastName.isEmpty()
                    || passport.isEmpty()
                    || password.isEmpty()
                    || email.isEmpty()) {
                DialogUtil.showInfo("Заполните все поля");
                return;
            }

            if (passport.length() != 10) {
                DialogUtil.showInfo("Поле с серией и номером должно содержать минимум 10 символов");
                return;
            }

            if (!passport.matches("[0-9]+")) {
                DialogUtil.showInfo("Поле с серией и номером должно содержать только цифры");
                return;
            }

            if (password.length() < 6 || password.length() > 10) {
                DialogUtil.showInfo("Пароль должен содержать от 6 до 10 символов");
                return;
            }

            if (!email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")) {
                DialogUtil.showInfo("Email введен неправильно");
                return;
            }

            try {
                if (userEntityManager.getByEmail(email) != null) {
                    DialogUtil.showInfo("Пользователь с таким email уже существует");
                    return;
                }

                UserEntity userEntity = new UserEntity(
                        firstName,
                        lastName,
                        passport,
                        password,
                        email,
                        false
                );

                userEntityManager.add(userEntity);

                container.set("user", userEntity);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            mainForm.hidePanelNotLoggedIn();
            mainForm.showPanelLoggedIn();
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
