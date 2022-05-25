package sk.stuba.fei.uim.oop.logic;

import lombok.Getter;
import sk.stuba.fei.uim.oop.gui.App;
import sk.stuba.fei.uim.oop.gui.MyCanvas;
import sk.stuba.fei.uim.oop.gui.MyJButton;
import sk.stuba.fei.uim.oop.gui.MyJSlider;
import sk.stuba.fei.uim.oop.shapes.Colors;
import sk.stuba.fei.uim.oop.shapes.Tree;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.Objects;

public class EventHandler extends UniversalAdapter {
    @Getter
    private final JLabel label;
    private int x0;
    private int y0;
    public static final String TREE_CMD = "Tree";
    public static final String MOVE_CMD = "Move";
    public static final String NEXT_COLOR_CMD = "Next color";


    public EventHandler(){
        super();
        this.label = new JLabel(Modes.DRAWING.name(), JLabel.CENTER);
        this.label.setOpaque(true);
        this.label.setForeground(Color.WHITE);
        this.label.setBackground(Colors.FIRST.getColor());
        this.x0 = 0;
        this.y0 = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (this.commandIs(e, EventHandler.TREE_CMD)) {
            this.label.setText(Modes.DRAWING.name());
        }
        if (this.commandIs(e, EventHandler.MOVE_CMD)) {
            this.label.setText(Modes.MOVING.name());
        }
        if (this.commandIs(e, EventHandler.NEXT_COLOR_CMD)) {
            if(e.getSource() instanceof MyJButton) {
                MyJButton button = (MyJButton) e.getSource();
                if (button.getCanvas() != null) {
                    this.label.setBackground(button.getCanvas().nextColor());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if (Objects.equals(e.getButton(), MouseEvent.BUTTON1) && e.getSource() instanceof MyCanvas) {
            MyCanvas canvas = (MyCanvas) e.getSource();
            this.x0 = e.getX();
            this.y0 = e.getY();
            if (this.modeIs(Modes.DRAWING)) {
                canvas.setTree(new Tree(this.x0, this.y0, canvas.getColor()));
            }
            if (this.modeIs(Modes.MOVING)) {
                canvas.setTree(canvas.findMostFrontTree(this.x0, this.y0));
            }
            canvas.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if (Objects.equals(e.getButton(), MouseEvent.BUTTON1) && e.getSource() instanceof MyCanvas) {
            MyCanvas canvas = (MyCanvas) e.getSource();
            if (this.modeIs(Modes.DRAWING)) {
                canvas.finishTree();
            }
            if (this.modeIs(Modes.MOVING)) {
                canvas.setTree(null);
            }
            canvas.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if (e.getSource() instanceof MyCanvas) {
            MyCanvas canvas = (MyCanvas) e.getSource();
            Tree tree = canvas.getTree();
            if (tree != null) {
                if (this.modeIs(Modes.DRAWING)) {
                    tree.resize(this.x0, this.y0, e.getX(), e.getY());
                }
                if (this.modeIs(Modes.MOVING)) {
                    tree.move(e.getX() - this.x0, e.getY() - this.y0);
                    this.x0 = e.getX();
                    this.y0 = e.getY();
                }
            }
            canvas.repaint();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        super.stateChanged(e);
        if (e.getSource() instanceof MyJSlider) {
            MyJSlider slider = (MyJSlider) e.getSource();
            if (!slider.getValueIsAdjusting()) {
                Color c = Color.LIGHT_GRAY.brighter().brighter();
                for (int i = slider.getMinimum(); i < slider.getValue(); i += slider.getMajorTickSpacing()) {
                    c = c.darker();
                }
                slider.getCanvas().setBackground(c);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        super.itemStateChanged(e);
        if (Objects.equals(e.getStateChange(), ItemEvent.SELECTED)) {
            this.label.setForeground(
                    Objects.equals(e.getItem(), "White") ? Color.WHITE :
                            Objects.equals(e.getItem(), "Black") ? Color.BLACK : Color.CYAN
            );
        }
    }

    private boolean commandIs(ActionEvent e, String command) {
        return Objects.equals(e.getActionCommand(), command);
    }

    private boolean modeIs(Modes mode) {
        try {
            return Objects.equals(Modes.valueOf(this.label.getText().toUpperCase(Locale.ROOT)), mode);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
