import java.awt.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class GameModel {

    //the array used to generate the random matrix
    static int[] randomArr = new int[100];

    //the 10*10 matrix with integers from 1-100 randomly
    static int[][] randomMatrix = new int[10][10];

    //the array used to generate the ordered matrix
    int[] orderedArr = new int[100];

    //the 10*10 matrix with ordered integers from 1-100
    int[][] orderedMatrix = new int[10][10];


    //steps and prisoners who pass.
    int steps = 0;
    int cleverPrisonNum = 0;


    //all counters shown
    int attempt = 0;

    int success = 0;

    double successRate =0;

    String successRateDisplayed; // successful rate in the String format


    //import three cute pictures of Banana Wolf/蕉太狼 to be used to show the state of the game
    Image picFail = Toolkit.getDefaultToolkit().getImage("status/fail.png");
    Image picWin = Toolkit.getDefaultToolkit().getImage("status/win.png");
    Image picGaming = Toolkit.getDefaultToolkit().getImage("status/gaming.png");


    //import all buttons
    Image freePlay = Toolkit.getDefaultToolkit().getImage("buttons/freeplay.png");
    Image hundredMen = Toolkit.getDefaultToolkit().getImage("buttons/knowfate.png");
    Image oneMan= Toolkit.getDefaultToolkit().getImage("buttons/onetrial.png");
     Image manyTrial = Toolkit.getDefaultToolkit().getImage("buttons/bestidea.png");
     Image arrow = Toolkit.getDefaultToolkit().getImage("buttons/arrow.png");
     Image billBoard = Toolkit.getDefaultToolkit().getImage("buttons/billboard.png");


    //generate a 10*10 matrix with ordered numbers from 1 to 99 and end with 0.
     void drawOrderedMatrix(int[] tempArr, int[][] data) {
        for (int i = 0; i < 100; i++) {
            tempArr[i] = i;
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                data[i][j] = tempArr[i * 10 + j];
            }
        }

    }

    //generate a 10*10 matrix with random numbers in [0,100).
     static void drawRandomMatrix(int[] tempArr, int[][] data) {
        for (int i = 0; i < 100; i++) {
            tempArr[i] = i;
        }

        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        for (int i = 0; i < tempArr.length; i++) {
            data[i / 10][i % 10] = tempArr[i];
        }

        for (int[] row : data) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("-------");
    }

    //mix up all slips
     static void reGame() {
        drawRandomMatrix(randomArr, randomMatrix);
    }

    //reset all counters
     void clearNum(){
        attempt = 0 ;
        success =0;
        successRate =0;
        countRate();
    }

    //set the format of the successful rate
     void countRate(){
        successRate = (double) success/ attempt;
        DecimalFormat df = new DecimalFormat("0.00%");
        String sR = df.format(successRate);
        successRateDisplayed = sR;
    }
}
