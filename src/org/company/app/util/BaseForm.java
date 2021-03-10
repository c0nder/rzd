package org.company.app.util;

import javax.swing.*;
import java.awt.*;

public abstract class BaseForm extends JFrame
{
    private static String baseApplicationTitle = "Train Tickets";

    private static Image baseApplicationIcon = null;

    public BaseForm()
    {
        setTitle(baseApplicationTitle);
        if(baseApplicationIcon != null) {
            setIconImage(baseApplicationIcon);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(getFormWidth(), getFormHeight()));
        setLocation(
                Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getFormWidth() / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getFormHeight() / 2
        );
        pack();
    }

    public abstract int getFormWidth();

    public abstract int getFormHeight();

    public static String getBaseApplicationTitle() {
        return baseApplicationTitle;
    }

    public static void setBaseApplicationTitle(String baseApplicationTitle) {
        BaseForm.baseApplicationTitle = baseApplicationTitle;
    }

    public static Image getBaseApplicationIcon() {
        return baseApplicationIcon;
    }

    public static void setBaseApplicationIcon(Image baseApplicationIcon) {
        BaseForm.baseApplicationIcon = baseApplicationIcon;
    }
}
