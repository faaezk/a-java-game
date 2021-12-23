import bagel.*;
import bagel.util.Colour;
import bagel.util.Rectangle;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class Program extends AbstractGame {

    private final Font FONT = new Font("res/conformable.otf", 50);
    private final int LEVEL1_SCORE = 10;
    private final int LEVEL2_SCORE = 20;
    private final int LEVEL3_SCORE = 30;

    private Player player1;
    private Enemy enemy1;
    private ArrayList<Goal> goals = new ArrayList<>();
    private PlayArea arena;

    private Image healthbar = new Image("res/healthbar.png");
    private Image healthBorder = new Image("res/healthborder.png");

    private String gameState = "start";
    private int level = 1;
    private int counter;
    private int score = 0;

    public Program() {
        super(1200, 750, "A game");
        player1 = new Player("res/playerRight.png", "res/playerLeft.png", 5.0, 600, 100);
        enemy1 = new Enemy("res/fishRight.png", "res/fishLeft.png", 5.0, 300, 600);
        arena = new PlayArea(level);
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        Program game = new Program();
        game.run();
    }

    public boolean updateGoalList(Player player, ArrayList<Goal> goals, int counter, Rectangle arena) {

        boolean flag = false;
        ArrayList<Goal> temp = new ArrayList<>();

        if (goals.size() == 0 || counter == 100) {
            double randX = ThreadLocalRandom.current().nextInt((int) arena.left() + 20, (int) arena.right() - 20);
            double randY = ThreadLocalRandom.current().nextInt((int) arena.top() + 20, (int) arena.bottom() - 20);

            goals.add(new Goal(randX, randY));
            flag = true;
        }

        for (Goal goal : goals) {
            goal.update();
            if (player.getRect().intersects(goal.getRect())) {
                goal.die();
                score++;
            }

            if (goal.getLifetime() == 0) {
                temp.add(goal);
            }
        }

        for (Goal goal : temp) {
            goals.remove(goal);
        }

        return flag;
    }

    public void updateHealthBar() {

        double health = player1.getHealth();
        DrawOptions stuff = new DrawOptions();
        Colour healthStatus = new Colour(1, 0, 0);

        if (health > 80) {
            healthStatus = new Colour(0, 1, 0);
        }
        else if (health > 50) {
            healthStatus = new Colour(1, 1, 0);
        }
        else if (health > 20) {
            healthStatus = new Colour(1, 0.64, 0);
        }

        stuff.setSection(0, 0, health * 2.5, 25);
        healthbar.drawFromTopLeft(25, 45, stuff.setBlendColour(healthStatus));
    }

    public void drawMidScreen(String message) {
        FONT.drawString(message,
                (Window.getWidth() - FONT.getWidth(message))/2.0, Window.getHeight()/2.0);
    }

    @Override
    public void update(Input input) {

        if (gameState.equals("start")) {
            drawMidScreen("Press SPACE to Start");

            if (input.wasPressed(Keys.SPACE)) {
                gameState = "level";
            }
        }
        else {
            FONT.drawString("Score: " + score, 20, 110);
            updateHealthBar();
            healthBorder.drawFromTopLeft(20, 40);
            arena.update();
        }

        if ((score == LEVEL1_SCORE && level == 1) || (score == LEVEL2_SCORE && level == 2)) {
            gameState = "level-up";
            counter = 0;
            level++;
        }

        if (score == LEVEL3_SCORE && level == 3) {
            gameState = "win";
            counter = 0;
            level++;
        }

        if (player1.getHealth() == 0 && gameState.equals("level")) {
            gameState = "dead";
        }

        switch (gameState) {

            case "level":
                player1.update(input, arena);
                enemy1.update(player1.getPosition());

                if (updateGoalList(player1, goals, counter, arena.getRect())) {
                    counter = 0;
                }

                if (player1.getRect().intersects(enemy1.getRect())) {
                    FONT.drawString("MONKEYMAN!", 600, 600);
                    player1.gotHit();
                }

                break;

            case "level-up":
                drawMidScreen("LEVEL UP!");
                if (counter > 150) {
                    gameState = "level";
                    arena = new PlayArea(level);
                    player1.resetPosition(level);
                    enemy1.resetPosition(level);
                    goals.clear();
                }

                break;

            case "win":
                drawMidScreen("congrats you won");
                break;

            case "dead":
                drawMidScreen("you lost");
                break;
        }

        counter++;

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }
    }
}
