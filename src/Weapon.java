import bagel.DrawOptions;
import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

public class Weapon {

    private final Image WEAPON_IMG = new Image("res/waterball.png");

    private final double SPEED = 10;
    private final Point ORIGIN;
    private final Point DESTINATION;

    private Point location;
    private double angle;
    private double yChange;
    private double xChange;

    private int counter = 0;

    public Weapon(Enemy shooter, Player target) {

        this.ORIGIN = shooter.getRect().centre();
        this.location = ORIGIN;
        this.DESTINATION = target.getRect().centre();
        setAngle();
    }

    public void setAngle() {

        double run = DESTINATION.x - ORIGIN.x;
        double rise = ORIGIN.y - DESTINATION.y;

        if (run > 0 && rise > 0) {
            System.out.println(java.lang.Math.atan(rise / run));
            this.angle = java.lang.Math.toRadians(360 - java.lang.Math.toDegrees(java.lang.Math.atan(rise / run)));
            this.yChange = java.lang.Math.sin(angle) * SPEED;
            this.xChange = java.lang.Math.cos(angle) * SPEED;
        }

        else if (run > 0 && rise < 0) {
            System.out.println(java.lang.Math.atan(-rise / run));
            this.angle = java.lang.Math.toRadians(java.lang.Math.toDegrees(java.lang.Math.atan(-rise / run)));
            this.yChange = java.lang.Math.sin(angle) * SPEED;
            this.xChange = java.lang.Math.cos(angle) * SPEED;
        }

        else if (run < 0 && rise < 0) {
            System.out.println(java.lang.Math.atan(rise / run));
            this.angle = java.lang.Math.toRadians(180 - java.lang.Math.toDegrees(java.lang.Math.atan(rise / run)));
            this.yChange = java.lang.Math.sin(angle) * SPEED;
            this.xChange = java.lang.Math.cos(angle) * SPEED;
        }

        else if (run < 0 && rise > 0) {
            System.out.println(java.lang.Math.atan(rise / -run));
            this.angle = java.lang.Math.toRadians(180 + java.lang.Math.toDegrees(java.lang.Math.atan(rise / -run)));
            this.yChange = java.lang.Math.sin(angle) * SPEED;
            this.xChange = java.lang.Math.cos(angle) * SPEED;
        }
    }

    public Rectangle getRect() {
        return WEAPON_IMG.getBoundingBoxAt(location);
    }

    public boolean update() {

        counter++;
        if (counter > 100) {
            return false;
        }

        WEAPON_IMG.draw(location.x, location.y, new DrawOptions().setRotation(angle));
        location = new Point(location.x + xChange, location.y + yChange);

        return true;
    }

}
