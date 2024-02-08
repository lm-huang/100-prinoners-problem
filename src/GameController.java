import java.awt.event.MouseEvent;
public class GameController {
    static private GameFrame view;
    static GameModel model;

    //the index of the mouse inside the matrix area in the widget
    static int mouseX;
    static int mouseY;

    //the index of the mouse outside the matrix area in the widget
    private static int outMouseX;
    private static int outMouseY;

    //0 is for gaming, 1 is for win, 2 is for failure
    static int gameState = 0;

    //0 is for free choosing, 1 is for one opening boxes using the best strategy
    // 2 is for all prisoners, 3 is for 300 times attempts
    static int gameMode = 0;

    //set a fixed offset for matrix area from the window's edge
    final int OFFSET = 50;

    //the status of the mouse click
    static boolean click = false;

    private MapBottom mapBottom;
    private MapTop mapTop;

    public GameController(GameModel m) {
        model = m;
    }

    public void launch(GameFrame v){
        view = v;
      //  view.ring =1;
        view.go();

    }

    public static void handleMouseClick(MouseEvent e) {
        //get the index of mouse clicking
        if (e.getButton() == 1) {
            outMouseX = e.getX();
            outMouseY = e.getY();
            mouseX = e.getX();
            mouseY = e.getY();
            click = true;
        }

        //switch to mode 0 and reset the game if its button is clicked
        if (outMouseX >= 50 && outMouseX <= 200 && outMouseY >= 570 && outMouseY <= 640) {
            gameMode = 0;
            view.mapTop.resetAll();
            model.reGame();
            gameState = 0;
        }

        //switch to mode 1 and reset the game if its button is clicked
        if (outMouseX >= 230 && outMouseX <= 470 && outMouseY >= 570 && outMouseY <= 640) {
            gameMode = 1;
            view.mapTop.resetAll();
            model.reGame();
            gameState = 0;
        }

        //switch to mode 2 and reset the game if its button is clicked
        if (outMouseX >= 500 && outMouseX <= 740 && outMouseY >= 570 && outMouseY <= 640) {
            gameMode = 2;
            view.mapTop.resetAll();
            model.reGame();
            gameState = 0;
        }

        //switch to mode 3 and reset the game if its button is clicked
        if (outMouseX >= 770 && outMouseX <= 840 && outMouseY >= 570 && outMouseY <= 640) {
            gameMode = 3;
            view.mapTop.resetAll();
            model.reGame();
            gameState = 0;
            model.clearNum();
        }
    }

    //cannot implement this functionality here
   /* public static void action(){
        mapTop = new MapTop();
        mapBottom = new MapBottom();
        mapBottom.initSlips(view.gImage);
        mapTop.initBox(view.gImage);
    }*/

}
