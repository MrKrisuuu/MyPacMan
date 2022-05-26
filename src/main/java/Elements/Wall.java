package Elements;

import Others.Vector2d;

public class Wall{
    private Vector2d position;

    public Wall(Vector2d position){
        this.position=position;
    }

    public Vector2d getPosition(){
        return this.position;
    }
}
