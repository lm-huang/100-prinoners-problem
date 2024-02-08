import java.awt.*;


//Components used in the bottom
public class MapBottom extends GameController{

    public MapBottom(GameModel m) {
        super(m);
    }

    {
        GameModel.reGame();
    }

    void initSlips(Graphics g) {
        //add the slip-images,labeling them with the numbers in the matrix
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int num = model.randomMatrix[i][j];
                Image pic = Toolkit.getDefaultToolkit().getImage("CS5004Image 2/image" + num + ".png");
                g.drawImage(pic,
                        50 * j + OFFSET,
                        50 * i + OFFSET,
                        50, 50, null);
            }
        }

        //change the status of Banana Wolf/蕉太狼's pictures to show the status of the game
        switch (gameState) {
            case 0 -> g.drawImage(model.picGaming, 600, 55, 60, 60, null);
            case 1 -> g.drawImage(model.picWin, 600, 55, 60, 60, null);
            case 2 -> g.drawImage(model.picFail, 600, 55, 60, 60, null);
        }


        g.drawImage(model.billBoard, 550, 270, 420,300,null);

        //draw all buttons
        g.drawImage(model.freePlay, 50, 570, 150,70,null);
        g.drawImage(model.manyTrial, 770, 570, 150,70,null);
        g.drawImage(model.hundredMen, 500, 570, 240,70,null);
        g.drawImage(model.oneMan, 230, 570, 240,70,null);

        switch (gameMode) {
            case 0 -> g.drawImage(model.arrow, 90, 620, 50, 100, null);
            case 1 -> g.drawImage(model.arrow, 320, 620, 50, 100, null);
            case 2 -> g.drawImage(model.arrow, 590, 620, 50, 100, null);
            case 3 -> g.drawImage(model.arrow, 810, 620, 50, 100, null);
        }

        //draw all counters
        g.setColor(Color.getColor("black",10));
        g.setFont(new Font("Chalkboard",Font.BOLD,20));

        if(gameMode ==3) {
            g.drawString("Attempts: " + model.attempt, OFFSET * 12, OFFSET * 4);
            g.drawString("Successful Times: " + model.success, OFFSET * 15, OFFSET * 4);
            g.drawString("Successful Rate: " + model.successRateDisplayed, OFFSET * 12, OFFSET * 5);

            g.drawString("For Best Strategy:", OFFSET * 13, OFFSET * 7);
            g.drawString("Current Prisoner Number: " + model.cleverPrisonNum, OFFSET * 13, OFFSET * 8);
            g.drawString("Steps: " + model.steps, OFFSET * 13, OFFSET * 9);
        }else{
            g.drawString("Attempts " , OFFSET * 12, OFFSET * 4);
            g.drawString("Successful Times " , OFFSET * 15, OFFSET * 4);
            g.drawString("Successful Rate", OFFSET * 12, OFFSET * 5);

            g.drawString("For Best Strategy:", OFFSET * 13, OFFSET * 7);
            g.drawString("Current Prisoner Number: " + model.cleverPrisonNum, OFFSET * 13, OFFSET * 8);
            g.drawString("Steps: " + model.steps, OFFSET * 13, OFFSET * 9);
        }

    }

}
