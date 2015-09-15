package control;

/**
 * Created by cuan on 9/9/15.
 */
public class GameTimer extends Thread {
    private final GameController gameController;

    public GameTimer(GameController gameController){
        this.gameController = gameController;
    }

    public void run(){
        while(true){
            try {
                Thread.sleep(10);
                gameController.executeAllSystems();
            }catch (Exception e){
                System.out.println("GameTimer Error");
                e.printStackTrace();
            }
        }
    }
}
