import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        hero = new Hero(10, 10); // Initial position of the hero
        walls = createWalls(); // Create walls in the arena
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    // Other methods

    public void draw(TextGraphics textGraphics) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        textGraphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        hero.draw(textGraphics);

        // Draw the walls
        for (Wall wall : walls) {
            wall.draw(textGraphics);
        }
    }

    public boolean canHeroMove(Position position) {
        int newX = position.getX();
        int newY = position.getY();
        if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
            return false;
        }
        return !walls.contains(position);
    }

    // Other methods
}