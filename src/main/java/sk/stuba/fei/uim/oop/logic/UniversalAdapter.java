package sk.stuba.fei.uim.oop.logic;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public abstract class UniversalAdapter implements ActionListener, MouseListener, MouseMotionListener, ChangeListener, ItemListener {

    @Override
    public void actionPerformed(ActionEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) { }

    @Override
    public void stateChanged(ChangeEvent e) { }

    @Override
    public void itemStateChanged(ItemEvent e) { }
}
