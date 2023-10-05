public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int get_x(){
        return x;
    }
    public int get_y(){
        return y;
    }

    public void set_x(int x){
        this.x = x;
    }

    public void set_y(int y){
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Position p = (Position) o;
        return x == p.get_x() && y == p.get_y();
    }
}