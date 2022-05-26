package Others;

import Visualising.MyFrame;

public class Game {
    public static void main(String[] args){
        WorldMap map = new WorldMap();
        MyFrame frame = new MyFrame(map);
        int time=400;
        frame.draw();
        while (true){
            try {
                Thread.sleep((int)(time*(2.0/(1+map.level))));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                if (!map.pacMan.isDead()){
                    map.run();
                    frame.draw();
                    frame.actualize();
                }

        }
    }
}
