import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;


    public Game(){
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new
                    DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            Position p = new Position(10,10);
            Hero h = new Hero(p);
            arena = new Arena(40,20);
            arena.set_hero(h);
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }



    private void processKey(KeyStroke key) throws IOException {
        arena.processKey(key, screen);

    }
    public void run() throws IOException {
        while(true){
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if (key.getKeyType() == KeyType.EOF){
                break;
            }
        }
    }

}