import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coin extends Element {


    public Coin(int c, int i) {
        super(c, i);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(super.get_position().get_x(), super.get_position().get_y()), "$");

    }
}
