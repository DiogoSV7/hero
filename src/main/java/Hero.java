import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element{


    public Position moveUp(){
        return new Position(super.get_position().get_x(), super.get_position().get_y() - 1);
    }
    public Position moveRight(){
        return new Position(super.get_position().get_x()+1, super.get_position().get_y());
    }
    public Position moveDown(){
        return new Position(super.get_position().get_x(), super.get_position().get_y() + 1);
    }
    public Position moveLeft(){
        return new Position(super.get_position().get_x()-1, super.get_position().get_y());
    }



    public Hero(Position position) {
        super(position);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(get_position().get_x(), get_position().get_y()), "X");
    }

}