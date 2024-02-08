public class GameGo {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameFrame frame = new GameFrame();
        GameController controller = new GameController(model);
        controller.launch(frame);
    }
}
