import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    private Position position;

    public Element(Position position){
        this.position = position;
    }

    public Element(int c, int i) {
        position = new Position(c,i);
    }

    public Position get_position(){
        return position;
    }


    public void set_position(Position position){
        this.position.set_x(position.get_x());
        this.position.set_y(position.get_y());
    }

    public abstract void draw(TextGraphics graphics);
}