package sk.stuba.fei.uim.oop.gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MyJMenuItem extends JMenuItem {
    public MyJMenuItem(String text, ActionListener listener) {
        super(text);
        this.addActionListener(listener);
    }
}
