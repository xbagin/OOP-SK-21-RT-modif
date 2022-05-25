package sk.stuba.fei.uim.oop.gui;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.logic.UniversalAdapter;
import sk.stuba.fei.uim.oop.shapes.Colors;
import sk.stuba.fei.uim.oop.shapes.Tree;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MyCanvas extends JPanel {
    private final List<Tree> trees;
    @Getter @Setter
    private Tree tree;
    private Colors rotatingColor;

    public MyCanvas(UniversalAdapter listener) {
        this.trees = new ArrayList<>();
        this.tree = null;
        this.rotatingColor = Colors.FIRST;
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        this.setBackground(Color.LIGHT_GRAY);
    }

    public Tree findMostFrontTree(int x, int y) {
        Tree frontTree = null;
        for (Tree tree : this.trees) {
            if (tree.isInCoordinates(x, y)) {
                frontTree = tree;
            }
        }
        return frontTree;
    }

    public void finishTree() {
        this.trees.add(this.tree);
        this.tree = null;
    }

    public Color getColor() {
        return this.rotatingColor.getColor();
    }

    public Color nextColor() {
        this.rotatingColor = this.rotatingColor.next();
        return this.rotatingColor.getColor();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.trees.forEach(tree -> tree.draw(g));
        if (this.tree != null && !this.trees.contains(this.tree)) {
            this.tree.draw(g);
        }
    }
}
