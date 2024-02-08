import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameFrame extends JFrame {

    private MapBottom mapBottom = new MapBottom(GameController.model);
    MapTop mapTop = new MapTop(GameController.model);

    //initialize the widget
    void go() {
        this.setVisible(true);
        this.setSize(1000, 700);

        this.setLocationRelativeTo(null);
        this.setTitle("Demonstration for 100 prisoners problem");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GameController.handleMouseClick(e);
            }
        });

        //repaint the frame every 0.04s
        while (true) {
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // double buffering to eliminate graphics flicker
    @Override
    public void paint(Graphics g) {
        Image offScreenImage = this.createImage(1000, 700);
        Graphics gImage = offScreenImage.getGraphics();
      // Tried to implement a method in GameController, but could not do it: GameController.action();
        mapBottom.initSlips(gImage);
        mapTop.initBox(gImage);

        g.drawImage(offScreenImage, 0, 0, null);
    }
}