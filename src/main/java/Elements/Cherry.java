package Elements;

import Others.MapDirection;
import Others.Vector2d;

public class Cherry {
    private Vector2d position;

    public Cherry(Vector2d position){
        this.position=position;
    }

    public Vector2d getPosition(){
        return this.position;
    }
}
