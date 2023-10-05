import com.googlecode.lanterna.Terminal;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Game {
    private Arena arena;
    private Screen screen;

    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            arena = new Arena(40, 20); // Initialize the arena with width and height
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveHero(Position position) {
        if (arena.canHeroMove(position)) {
            arena.getHero().setPosition(position);
        }
    }

    private void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowUp) {
            moveHero(arena.getHero().moveUp());
        } else if (key.getKeyType() == KeyType.ArrowDown) {
            moveHero(arena.getHero().moveDown());
        } else if (key.getKeyType() == KeyType.ArrowLeft) {
            moveHero(arena.getHero().moveLeft());
        } else if (key.getKeyType() == KeyType.ArrowRight) {
            moveHero(arena.getHero().moveRight());
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            screen.close();
        } else if (key.getKeyType() == KeyType.EOF) {
            break;
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() {
        while (true) {
            try {
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}