import com.googlecode.lanterna.graphics.TextGraphics;

import javax.swing.text.Position;

public class Hero extends Element {
    private Position position;

    public Hero(int initialX, int initialY) {
        position = new Position(initialX, initialY);
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }

    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    public void draw(TextGraphics textGraphics) {
        // Draw the hero on the screen using TextGraphics
        // You can customize the appearance of the hero here
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}