package Elements;

import Others.MapDirection;
import Others.Vector2d;

public class Ghost {
    private Vector2d position;
    public Vector2d startPosition;

    public Ghost(Vector2d position){
        this.position=position;
        this.startPosition=position;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public void setPosition(Vector2d position){
        this.position=position;
    }

    public void move(MapDirection direction){
        this.position=this.position.add(direction.toUnitVector());
    }
}
