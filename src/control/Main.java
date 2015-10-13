package control;

public class Main {

    public static final int F_WIDTH = 1000;
    public static final int F_HEIGHT = 600;
    public static final int C_WIDTH = 800;
    public static final int C_HEIGHT = 600;
    public static final int TILE_SIZE = 40;
    public static final boolean TEST_MODE = true;
    public static final String TITLE = "ECS BETA";
    public static final int PLAYER_SIZE = 45;
    public static final int ITEM_SIZE = 30;
    public static final int PLAYER_ONE_START_LOCATION_X = 74;//800;	//75
    public static final int PLAYER_ONE_START_LOCATION_Y = 74;//2000; // 75
    public static final int PLAYER_TWO_START_LOCATION_X = 100;//760; // 125
    public static final int PLAYER_TWO_START_LOCATION_Y = 100;//1960;  // 75
    public static final boolean DRAW_HITBOXES = true;
    public static final int PORT = 32768;
    public static int ROTATION = 0;
    private static ClientControl socketClient;
    private static ServerControl socketServer;
    //private static PlayerController playerController;


    /*public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                boolean isServer = false;
                *//**CONTROL**//*
                //SERVER STUFF

                //Set up Host/Join screen display
                BufferedImage image = null;
                try {
                    image = ImageIO.read(new File("LoginScreen.png"));
                } catch (IOException e) {

                    e.printStackTrace();
                }        UIManager UI=new UIManager();

                UI.put("OptionPane.showInputDialog.message", Color.white);
                UI.put("OptionPane.background", Color.black);
                UI.put("Panel.background", Color.black);
                Object[] options = { "Host", "Join" };
                if (JOptionPane.showOptionDialog(null, null, null,
                       JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,    new ImageIcon(image),
                       options, options[0]) == 0) {
                   isServer = true;
                   socketServer = new ServerControl();
                   socketServer.start();
               }


                GameState gameState = new GameState(isServer);
                if (isServer) {
                    socketServer.setGame(gameState);
                }

                socketClient = new ClientControl("localhost", gameState);
                socketClient.start();

                PlayerController playerController = new PlayerController(socketClient);

                // Get player username
                UI.put("Panel.background", Color.white);
                String username = "00" + JOptionPane.showInputDialog(null, "enter username");

                //TODO fix up packet system
                PacketLogin loginPacket = new PacketLogin(username.getBytes());
                loginPacket.writeData(socketClient);

                *//**VIEW*//*
                // Set up the gameFrame

                GameFrame gameFrame = new GameFrame(TITLE, F_WIDTH, F_HEIGHT, socketServer);
                //Setup UI
                GameCanvas gameCanvas = new GameCanvas(gameFrame, C_WIDTH, C_HEIGHT, socketClient);
                gameFrame.getContentPane().add(gameCanvas);
                gameFrame.pack();
                gameFrame.addKeyListener(playerController);

                GameCamera camera = new GameCamera(gameState, socketClient);
                Renderer renderer = new Renderer(camera, gameCanvas);
                EnemyController enemyController = new EnemyController(gameState);
                GameTimer gameTimer = new GameTimer(camera, renderer*//*, enemyController*//*);
                gameTimer.start();
            }

        });

    }*/
}
