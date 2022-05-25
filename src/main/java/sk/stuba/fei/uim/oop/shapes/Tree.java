package sk.stuba.fei.uim.oop.shapes;

import lombok.Getter;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Tree {
    private int x;
    private int y;
    private int width;
    private int height;
    @Getter
    private final Color color;

    public Tree(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.width = 0;
        this.height = 0;
        this.color = color;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(this.color);
        g.fillOval(this.x, this.y, this.width, this.height * 2 / 3);
        g.fillRect(
                this.x + this.width / 3,
                this.y + this.height / 2,
                this.width / 3,
                this.height / 2
        );
        g.setColor(c);
    }

    public void resize(int x0, int y0, int x2, int y2) {
        this.x = Math.min(x0, x2);
        this.y = Math.min(y0, y2);
        this.width = Math.abs(x2 - x0);
        this.height = Math.abs(y2 - y0);
    }

    public boolean isInCoordinates(int x, int y) {
        Rectangle rectangle = new Rectangle(
                this.x + this.width / 3,
                this.y + this.height / 2,
                this.width / 3,
                this.height / 2
        );
        Ellipse2D ellipse = new Ellipse2D.Double(this.x, this.y, this.width, this.height * 2 / 3.);
        return rectangle.contains(x, y) || ellipse.contains(x, y);
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}
