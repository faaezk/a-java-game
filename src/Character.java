import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;


public abstract class Character implements Collidable {

    protected Point position;
    protected double speed;
    protected Image characterIMGRight;
    protected Image characterIMGLeft;

    public Character(String rightImage, String leftImage, double speed, double x, double y) {

        this.position = new Point(x, y);
        this.speed = speed;
        this.characterIMGRight = new Image(rightImage);
        this.characterIMGLeft = new Image(leftImage);

    }

    public Point getPosition() {
        return this.position;
    }

    @Override
    public Rectangle getRect() {
        return this.characterIMGRight.getBoundingBoxAt(position);
    }

}
