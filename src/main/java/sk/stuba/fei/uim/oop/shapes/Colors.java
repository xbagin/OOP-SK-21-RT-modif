package sk.stuba.fei.uim.oop.shapes;

import lombok.Getter;

import java.awt.*;

public enum Colors {
    RED(Color.RED),
    GREEN(Color.GREEN),
    BLUE(Color.BLUE);

    @Getter
    private final Color color;
    public static final Colors FIRST = Colors.RED;

    Colors(Color color) {
        this.color = color;
    }

    public Colors next() {
        return Colors.values()[(this.ordinal() + 1) % Colors.values().length];
    }
}
