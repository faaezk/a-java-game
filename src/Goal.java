import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Goal implements Collidable{

    private final Image goalIMG = new Image("res/coin.png");
    private Point position;
    private int lifetime = 250;

    public Goal(double x, double y) {
        this.position = new Point(x, y);
    }

    public int getLifetime() {
        return lifetime;
    }

    public void die() {
        lifetime = 0;
    }

    public void update() {

        if (lifetime-- > 0) {
            goalIMG.draw(position.x, position.y);
        }
    }

    @Override
    public Rectangle getRect() {
        return goalIMG.getBoundingBoxAt(position);
    }
}
