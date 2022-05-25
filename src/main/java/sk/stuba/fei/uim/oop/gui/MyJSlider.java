package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;

import javax.swing.*;
import javax.swing.event.ChangeListener;

public class MyJSlider extends JSlider {
    @Getter
    private final MyCanvas canvas;

    public MyJSlider(int min, int max, int value, ChangeListener listener, MyCanvas canvas) {
        super(JSlider.HORIZONTAL, min, max, value);
        this.canvas = canvas;
        this.addChangeListener(listener);
        //this.setPaintTicks(true);
        this.setPaintLabels(true);
        this.setMinorTickSpacing(1);
        this.setMajorTickSpacing(1);
        this.setSnapToTicks(true);
    }
}
