import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public void processKey(KeyStroke key, Screen screen) throws IOException {
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowUp){
            moveHero(hero.moveUp());
            moveMonsters();
            verifyMonsterCollisions(screen);

        }
        if (key.getKeyType() == KeyType.ArrowDown){
            moveHero(hero.moveDown());
            moveMonsters();
            verifyMonsterCollisions(screen);

        }
        if (key.getKeyType() == KeyType.ArrowLeft){
            moveHero(hero.moveLeft());
            moveMonsters();
            verifyMonsterCollisions(screen);

        }
        if (key.getKeyType() == KeyType.ArrowRight){
            moveHero(hero.moveRight());
            moveMonsters();
            verifyMonsterCollisions(screen);

        }

        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
            screen.close();
        }


    }

    public void moveMonsters(){
        for (Monster monster : monsters){
            Position p = monster.move();
            if(canMonsterMove(p)){
                monster.set_position(p);
            }
        }
    }



    public void moveHero(Position position) {
        if (!coins.isEmpty()) {
            for (Coin coin : coins) {
                if (canHeroMove(position) && !position.equals(coin.get_position())) {
                    hero.set_position(position);
                }
                if (canHeroMove(position) && position.equals(coin.get_position())) {
                    hero.set_position(position);
                    retrieveCoins(coin);
                    break;
                }
            }
        }
        else{
            if (canHeroMove(position)) {
                hero.set_position(position);
            }
        }
    }

    public void verifyMonsterCollisions(Screen screen) throws IOException {
        for (Monster monster : monsters){
            if (monster.get_position().equals(hero.get_position()) || adjacent(monster.get_position(), hero.get_position())){
                screen.close();
                System.out.println("You Won!");
            }
        }
    }

    public boolean adjacent(Position a, Position b){
        int x = Math.abs(a.get_x() - b.get_x());
        int y = Math.abs(a.get_y() - b.get_y());

        return ((x == 1 && y == 0) || (x == 0 && y == 1));
    }

    public void retrieveCoins(Coin coin) {
        coins.remove(coin);
    }

    private boolean canMonsterMove(Position position) {
        if (!coins.isEmpty()) {
            for(Coin coin : coins){
                if (coin.get_position().equals(position)){
                    return false;
                }
            }
            for (Wall wall : walls){
                if (wall.get_position().equals(position)){
                    return false;
                }
            }
            return (position.get_x()< width && position.get_x()>=0 && position.get_y()<height && position.get_y()>=0 );
        }
        else{
            for (Wall wall : walls){
                if (wall.get_position().equals(position)){
                    return false;
                }
            }
            return (position.get_x()< width && position.get_x()>=0 && position.get_y()<height && position.get_y()>=0 );
        }

    }

    private boolean canHeroMove(Position position) {
        for (Wall wall : walls){
            if (wall.get_position().equals(position)){
                return false;
            }
        }
        return (position.get_x()< width && position.get_x()>=0 && position.get_y()<height && position.get_y()>=0 );
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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            int a = random.nextInt(width - 2) + 1;
            int b = random.nextInt(height - 2) + 1;

            if (a == 10 && b == 10){
                i--;
            }
            else if (aux(coins,a,b)) {
                coins.add(new Coin(a, b));
            }
            else{
                i--;
            }
        }
        return coins;
    }

    private List<Monster> createMonsters() {
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add (new Monster(25,15));
        return monsters;
    }

    private boolean aux(ArrayList<Coin> coins, int a, int b){
        for(Coin coin : coins){
            if(coin.get_position().get_x() == a && coin.get_position().get_x() == b){
                return false;
            }
        }
        return true;
    }

    public Arena(int width, int height){
        this.width=width;
        this.height=height;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters=createMonsters();

    }


    public void set_hero(Hero hero){
        this.hero = hero;
    }



    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#f25252"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new
                TerminalSize(width, height), ' ');

        hero.draw(graphics);

        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
        for (Monster monster: monsters){
            monster.draw(graphics);
        }


    }
}