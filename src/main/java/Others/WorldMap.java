package Others;

import Elements.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

public class WorldMap {
    public int width;
    public int height;

    public int level;

    public Object[][] map;


    public List<Ghost> ghosts;
    public PacMan pacMan;


    int[][] walls = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    // 0 to punkt
    // 1 to sciana
    // 2 to pacman
    // 3 to duchy
    // 4 to wiśnia


    public WorldMap() {
        this.height = 20;
        this.width = 20;

        this.map = new Object[this.width][this.height];

        this.ghosts = new LinkedList();

        this.pacMan = new PacMan(new Vector2d(0,0));

        this.level=0;

        this.hardReset();
    }

    public Object objectAt(Vector2d position){
        return this.map[position.x][position.y];
    }

    public void run(){
        if (!(this.objectAt(this.pacMan.getPosition().add(this.pacMan.getDirection().toUnitVector())) instanceof Wall)){
            this.pacMan.setPosition(this.pacMan.getPosition().add(this.pacMan.getDirection().toUnitVector()));
        }

        Vector2d pacPosition=this.pacMan.getPosition();

        for (Ghost ghost : ghosts){
            if (pacPosition.equals(ghost.getPosition())){
                if (!this.pacMan.isOP())
                {
                    this.pacMan.die();
                    this.reset();
                }
                else{
                    ghost.setPosition(ghost.startPosition);
                }
                break;
            }
        }

        if (this.objectAt(pacPosition) instanceof Point){
            this.map[pacPosition.x][pacPosition.y]=null;
            this.pacMan.eat();
        }

        if (this.objectAt(pacPosition) instanceof Cherry){
            this.map[pacPosition.x][pacPosition.y]=null;
            this.pacMan.eatCherry();
        }

        for (Ghost ghost : ghosts){
            Random rand = new Random();
            MapDirection direction;
            Vector2d diff = ghost.getPosition().subtract(this.pacMan.getPosition());
            int x=diff.x;
            int y=diff.y;
            if (this.pacMan.isOP()){
                x=-x;
                y=-y;
            }
            int czy=0;
            if (abs(x)>abs(y)){
                if (x>0){
                    direction=MapDirection.WEST;
                }
                else{
                    direction=MapDirection.EAST;
                }
                if (this.objectAt(ghost.getPosition().add(direction.toUnitVector())) instanceof Wall){
                    if (y>0){
                        direction=MapDirection.SOUTH;
                    }
                    else{
                        direction=MapDirection.NORTH;
                    }
                    if (y==0){
                        czy=1;
                    }
                }
            }
            else{
                if (y>0){
                    direction=MapDirection.SOUTH;
                }
                else{
                    direction=MapDirection.NORTH;
                }
                if (this.objectAt(ghost.getPosition().add(direction.toUnitVector())) instanceof Wall){
                    if (x>0){
                        direction=MapDirection.WEST;
                    }
                    else{
                        direction=MapDirection.EAST;
                    }
                    if (x==0){
                        czy=1;
                    }
                }
            }
            if (!(this.objectAt(ghost.getPosition().add(direction.toUnitVector())) instanceof Wall) && czy==0) {
                ghost.move(direction);
            }
        }

        for (Ghost ghost : ghosts){
            if (pacPosition.equals(ghost.getPosition())){
                if (!this.pacMan.isOP())
                {
                    this.pacMan.die();
                    this.reset();
                }
                else{
                    ghost.setPosition(ghost.startPosition);
                }
                break;
            }
        }

        if (this.pacMan.isOP()){
            this.pacMan.OP-=1;
        }


        if (this.isEmpty()){
            hardReset();
        }
    }

    public void reset(){
        this.ghosts.clear();
        for (int i=0; i<this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                switch (this.walls[j][i]){
                    case 2:
                        this.pacMan.setPosition(new Vector2d(i,this.height-1-j));
                        break;
                    case 3:
                        Ghost ghost = new Ghost(new Vector2d(i,this.height-1-j));
                        ghosts.add(ghost);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void hardReset(){
        this.ghosts.clear();
        this.pacMan.OP=0;
        this.level+=1;
        for (int i=0; i<this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                switch (this.walls[j][i]){
                    case 0:
                        Point point0 = new Point(new Vector2d(i,this.height-1-j));
                        this.map[i][this.height-1-j] = point0;
                        break;
                    case 1:
                        Wall wall = new Wall(new Vector2d(i,this.height-1-j));
                        this.map[i][this.height-1-j] = wall;
                        break;
                    case 2:
                        this.pacMan.setPosition(new Vector2d(i,this.height-1-j));
                        break;
                    case 3:
                        Ghost ghost = new Ghost(new Vector2d(i,this.height-1-j));
                        ghosts.add(ghost);
                        Point point3 = new Point(new Vector2d(i,this.height-1-j));
                        this.map[i][this.height-1-j] = point3;
                        break;
                    case 4:
                        Cherry cherry = new Cherry(new Vector2d(i,this.height-1-j));
                        this.map[i][this.height-1-j] = cherry;
                    default:
                        break;
                }
            }
        }
    }

    public boolean isEmpty(){
        for (int i=0; i<this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.map[i][j] instanceof Point){
                    return false;
                }
            }
        }
        return true;
    }
}
