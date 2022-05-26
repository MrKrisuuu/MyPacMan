package Others;

import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y){
        this.x=x;
        this.y=y;
    }

    public String toString(){
        String string="(";
        string+=this.x;
        string+=",";
        string+=this.y;
        string+=")";
        return string;
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x, this.y+other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x, this.y-other.y);
    }

    public boolean equals(Object other){
        if (this==other){
            return true;
        }
        else if (!(other instanceof Vector2d)){
            return false;
        }
        else {
            Vector2d that= (Vector2d) other;
            return (this.x==that.x && this.y==that.y);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

}