package sk.stuba.fei.uim.oop.gui;

import sk.stuba.fei.uim.oop.logic.EventHandler;

import javax.swing.*;
import java.awt.*;

public class App {
    public App() throws HeadlessException {
        JFrame frame = new JFrame("OOP-SK-21-RT-modified");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        EventHandler handler = new EventHandler();

        MyCanvas canvas = new MyCanvas(handler);

        JMenuBar menubar = new JMenuBar();

        JMenu modes = new JMenu("Modes");
        MyJButton nextColor = new MyJButton(EventHandler.NEXT_COLOR_CMD, handler, canvas);

        MyJMenuItem tree = new MyJMenuItem(EventHandler.TREE_CMD, handler);
        MyJMenuItem move = new MyJMenuItem(EventHandler.MOVE_CMD, handler);

        modes.add(tree);
        modes.add(move);

        MyJSlider slider = new MyJSlider(1, 5, 3, handler, canvas);

        JComboBox<String> dropDown = new JComboBox<>(new String[]{"White", "Black"});
        dropDown.addItemListener(handler);
        dropDown.setFocusable(false);

        menubar.add(modes);
        menubar.add(nextColor);
        menubar.add(handler.getLabel());
        menubar.add(slider);
        menubar.add(dropDown);

        frame.setJMenuBar(menubar);
        frame.add(canvas, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
