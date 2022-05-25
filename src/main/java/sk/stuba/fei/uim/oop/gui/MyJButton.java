package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MyJButton extends JButton {
    @Getter
    private final MyCanvas canvas;

    public MyJButton(String text, ActionListener listener, MyCanvas canvas) {
        super(text);
        this.canvas = canvas;
        this.addActionListener(listener);
        this.setFocusable(false);
    }
}
