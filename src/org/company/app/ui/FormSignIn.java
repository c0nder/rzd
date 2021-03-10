package org.company.app.ui;

import org.company.app.Application;
import org.company.app.database.entity.UserEntity;
import org.company.app.database.manager.UserEntityManager;
import org.company.app.util.BaseForm;
import org.company.app.util.BaseSubForm;
import org.company.app.util.DialogUtil;
import org.company.app.util.Registry;

import javax.swing.*;
import java.sql.SQLException;

public class FormSignIn extends BaseSubForm<MainForm> {
    private JTextField FieldEmail;
    private JPasswordField FieldPassword;
    private JButton ButtonCancel;
    private JButton ButtonSignIn;
    private JPanel FormSignIn;

    private final UserEntityManager userEntityManager = Application.getUserEntityManager();
    private final Registry container = Application.getContainer();

    public FormSignIn(MainForm mainForm) {
        super(mainForm);

        setContentPane(FormSignIn);
        setVisible(true);

        initButtons();
    }

    private void initButtons() {
        ButtonCancel.addActionListener(e -> {
            closeSubForm();
        });

        ButtonSignIn.addActionListener(e -> {
            try {
                UserEntity userEntity = userEntityManager.getByEmailAndPassword(FieldEmail.getText(), FieldPassword.getText());

                if (userEntity == null) {
                    DialogUtil.showInfo("Пользователь с такими данными не был найден");
                } else {
                    DialogUtil.showInfo("Вы успешно авторизовались");

                    container.set("user", userEntity);

                    mainForm.hidePanelNotLoggedIn();
                    mainForm.showPanelLoggedIn();

                    closeSubForm();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    @Override
    public int getFormWidth() {
        return 640;
    }

    @Override
    public int getFormHeight() {
        return 580;
    }
}
