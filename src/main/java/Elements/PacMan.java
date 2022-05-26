package Elements;

import Others.MapDirection;
import Others.Vector2d;

public class PacMan {
    private Vector2d position;
    public int lives;
    private MapDirection direction;
    public int points;

    public int OP;

    public PacMan(Vector2d position){
        this.position=position;
        this.lives=3;
        this.direction=MapDirection.NORTH;
        this.points=0;
        this.OP=0;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public void setPosition(Vector2d position){
        this.position=position;
    }

    public MapDirection getDirection(){
        return this.direction;
    }

    public void setDirection(MapDirection direction){
        this.direction=direction;
    }

    public boolean isDead(){
        return this.lives<0;
    }

    public void die(){
        this.lives-=1;
    }

    public void eat(){
        this.points+=1;
    }

    public boolean isOP(){
        return this.OP>0;
    }

    public void eatCherry(){
        this.OP+=50;
    }
}
