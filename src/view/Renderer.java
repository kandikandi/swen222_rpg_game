package view;


import control.Main;
import model.*;
import ui.GameCanvas;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cuan on 9/8/15.
 */
public final class Renderer {

    private final GameCamera camera;
    private final GameCanvas gameCanvas;
    private BufferedImage buffImg;
    private Shape blackFill;
    private Graphics2D g2d;

    public Renderer( GameCanvas gameCanvas) {
        camera = new GameCamera();
        this.gameCanvas = gameCanvas;
        buffImg = new BufferedImage(Main.C_WIDTH, Main.C_HEIGHT,
                BufferedImage.TYPE_INT_ARGB);
        g2d = buffImg.createGraphics();
        blackFill = new Rectangle(Main.C_WIDTH, Main.C_HEIGHT);

    }

    /**
     * Flushes the buffered imageName by filling it with black
     */
    private void drawBackground() {
        g2d.setColor(Color.BLACK);
        g2d.fill(blackFill);
    }

    public void renderScene(GameState gameState, int playerNum) {
        //System.out.println("Renderer renderscene()");
        // paint scene background black
        drawBackground();

        Player player = null;

        while(player==null){
        	player = gameState.findPlayer(playerNum);
        	System.out.println(playerNum);
        	System.out.println(player==null);

        }


        int playerX = player.getPosition().getxPos();
        int playerY = player.getPosition().getyPos();
        int playerWidth = ActorAssets.PLAYER.getWidth();
        int playerHeight = ActorAssets.PLAYER.getHeight();
        int halfCWidth = Main.C_WIDTH/2;
        int halfCHeight = Main.C_HEIGHT/2;
        int offsetX = playerX/*+(playerWidth/2)*/ - halfCWidth ;
        int offsetY = playerY/*+(playerHeight/2)*/ - halfCHeight;

       // int y = actor.getPosition().getyPos() - playerY + HALF_C_HEIGHT;


        // Look for all entities that contain the two components required to
        // draw it
        camera.getTileView(gameState, playerNum)
                .stream().sorted(RotationCalculator.getTileComparator())
                .forEach(tile -> {
                    Image image = TileAssets.getAssetImage(tile.getAsciiCode());
                    int width = Main.TILE_SIZE;
                    int height = Main.TILE_SIZE;
                    int relativeX = tile.getPosition().getxPos()/*+(width/2)*/ - offsetX;
                    int relativeY = tile.getPosition().getyPos()/*+(height/2)*/ - offsetY;
                    int x = RotationCalculator.getScreenX(relativeX, relativeY, width, height, halfCWidth, halfCHeight);
                    int y = RotationCalculator.getScreenY(relativeX, relativeY, width, height, halfCWidth, halfCHeight);
                    g2d.drawImage(image, x, y, width, height,null);
                });

        camera.getActorView(gameState, playerNum)
                .stream().sorted(RotationCalculator.getActorComparator())
                .filter(actor -> actor.isDrawable())
                .forEach(actor -> {
                    Image image = ActorAssets.getAssetImage(actor.getAsciiCode());
                    int width = ActorAssets.getAssetName(actor.getAsciiCode()).getWidth();
                    int height = ActorAssets.getAssetName(actor.getAsciiCode()).getHeight();
                    //int x = actor.getPosition().getxPos() - offsetX;
                    //int y = actor.getPosition().getyPos() - offsetY;
                    int relativeX = actor.getPosition().getxPos()/*+(width/2)*/ - offsetX;
                    int relativeY = actor.getPosition().getyPos()/*+(height/2)*/ - offsetY;
                    int x = RotationCalculator.getScreenX(relativeX, relativeY, width, height, halfCWidth, halfCHeight);
                    int y = RotationCalculator.getScreenY(relativeX, relativeY, width, height, halfCWidth, halfCHeight);
                    g2d.drawImage(image, x, y, width, height, null);

                    if (Main.DRAW_HITBOXES) {
                        g2d.setColor(new Color(255, 126, 241));
                        int w1 = actor.getBoundingBox().width;
                        int h1 = actor.getBoundingBox().height;
                        int rX = actor.getBoundingBox().x/*+(w1/2)*/ - offsetX;
                        int rY = actor.getBoundingBox().y/*+(h1/2)*/ - offsetY;
                        int x2 = RotationCalculator.getScreenX(rX, rY, w1, h1, halfCWidth, halfCHeight);
                        int y2 = RotationCalculator.getScreenY(rX, rY, w1, h1, halfCWidth, halfCHeight);
                        g2d.fillRect(x2,y2,w1,h1);
                    }

                });
        gameCanvas.receiveBuffImage(buffImg);
    }
}
