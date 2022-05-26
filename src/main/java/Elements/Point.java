package Elements;

import Others.Vector2d;

public class Point{
    private Vector2d position;

    public Point(Vector2d position){
        this.position=position;
    }

    public Vector2d getPosition(){
        return this.position;
    }
}
