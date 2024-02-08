import java.awt.*;

// Components in the top layer
public class MapTop extends GameController{

    // a flag to control the recursion and while loop
    private int goOn;


    //the 10*10 matrix with 0 and 1 to control the open and close for boxes, 0 is for closed.
    private int[][] switchMatrix = new int[10][10];

    public MapTop(GameModel m) {
        super(m);
    }


    //a logic to get the index of clicking and change the status of the switchMatrix
    void logic() {
        //convert the mouse index to the matrix index, ignoring invalid mouse clicking.
        //initialize them with invalid values at first.

        //temporary valuables to get the index for matrix converted from index of mouse-clicking
        int tempX = -1;
        int tempY = -1;

        if (GameController.mouseX >= OFFSET && GameController.mouseY >= OFFSET) {
            tempX = (GameController.mouseX - OFFSET) / 50;
            tempY = (GameController.mouseY - OFFSET) / 50;
        }

        //get the corresponding cube's index if the cube is clicked
        if (tempX >= 0 && tempX <= 9 && tempY <= 9 && tempY >= 0) {
            if (click) {
                if (switchMatrix[tempX][tempY] == 0) {
                    switchMatrix[tempX][tempY] = 1;
                }
                // free choose
                if (gameMode == 0) {
                    System.out.println("free choosing mode");
                    click = false;
                    // open one man's boxes with the best strategy
                } else if (gameMode == 1) {
                    System.out.println("one person mode");
                    closeBox();
                    cleverOpen1(0, 1);
                    click = false;
                    // check the result
                } else if (gameMode == 2) {
                    System.out.println("100 prisoners mode");
                    resetRound();
                    closeBox();
                    seeResult();
                    click = false;
                } else if(gameMode ==3){
                    System.out.println("300 trials mode");
                    threeHundreds();
                    click =false;
                }
            }
        }
    }

    //if the box has not been clicked, import the relative image to make it a box
    //if not, do nothing and expose the MapBottom, which are slips.
    void initBox(Graphics g) {
        logic();
        // create the flag matrix
        model.drawOrderedMatrix(model.orderedArr, model.orderedMatrix);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // when choosing free
                if (gameMode == 0 && switchMatrix[j][i] == 0) {
                    int num = model.orderedMatrix[i][j];
                    //import images in order
                    Image pic = Toolkit.getDefaultToolkit().getImage("CS5004Image/image" + num + ".png");
                    g.drawImage(pic,
                            50 * j + OFFSET,
                            50 * i + OFFSET,
                            50, 50, null);
                }
                // when using the best strategy for one prisoner, different index.
                if ((gameMode == 1 || gameMode == 2 || gameMode ==3)
                        && switchMatrix[i][j] == 0) {
                    int num = model.orderedMatrix[i][j];
                    //import images in order
                    Image pic = Toolkit.getDefaultToolkit().getImage("CS5004Image/image" + num + ".png");
                    g.drawImage(pic,
                            50 * j + OFFSET,
                            50 * i + OFFSET,
                            50, 50, null);
                }

            }
        }
    }

    //open the box recursively using the best strategy
    void cleverOpen1(int row, int column) {
        //a prisoner will pass the test if he finds a box opened, where slip writing with his number must in it.
        //firstly check if the prisoner fails
        //e.g. the 51st box is opened, so 50th box is the slip

        //open 51 boxes if it has been opened, terminate the recursion, if not, terminate with the result of failure
        if (model.steps == 51) {
            if (switchMatrix[row][column] == 1) {
                return;
            }
            gameState = 2;
            return;
        }

        //return if one finds a box opened
        if (switchMatrix[row][column] == 1) {
            return;
        }

        //recursively open boxes
        if (switchMatrix[row][column] == 0) {
            System.out.println(model.steps + "   row:" + row + "   colunm:" + column);
            model.steps++;
            switchMatrix[row][column] = 1;
            int num = model.randomMatrix[row][column];
            row = num / 10;
            column = num % 10;
            cleverOpen1(row, column);
        }
    }

    //recursively open boxes for mode 2 and 3. Immediately return and terminate all recursion once we get result
    int cleverOpen2(int row, int column) {

        if (model.steps == 51) {
            if (switchMatrix[row][column] == 1) {
                return 1;
            }
            gameState = 2;
            System.out.println("game over, the one executed:"+ model.steps);
            return -1;
        }

        if (switchMatrix[row][column] == 1) {
            return 1;
        }

        //recursively open boxes
        if (switchMatrix[row][column] == 0) {
            model.steps++;
            switchMatrix[row][column] = 1;
            int num = model.randomMatrix[row][column];
            row = num / 10;
            column = num % 10;
            goOn = cleverOpen2(row, column); // check the return value of the recursion
            if (goOn == 1) return 1;
            if (goOn == -1) return -1;
        }

        return 0;
    }

    //return the outcome for 100 prisoners
    void seeResult() {

        int rowFast = 0;
        int columnFast = 1;

        while (model.cleverPrisonNum <= 99) {
            closeBox();
            goOn = cleverOpen2(rowFast, columnFast);
            if (gameState == 2) {
                return;
            }
            if (goOn == 1) resetRound();
            rowFast = model.cleverPrisonNum / 10;
            columnFast = model.cleverPrisonNum % 10;
        }

        //the index for the 100th box is (0,0)
        if (model.cleverPrisonNum == 100) {
            resetRound();
            model.cleverPrisonNum = 100;
            closeBox();
            System.out.println("numbers of prisoners who passed: " + model.cleverPrisonNum);
            goOn = cleverOpen2(0, 0);
            if (goOn == 1) {
                gameState = 1;
            }
        }
    }

    //if the last prisoner succeed, close all the box, reset the step counter and add one who has been passed test
    void resetRound() {
        model.steps = 1;
        model.cleverPrisonNum++;
        goOn = 0;
    }

    //close all boxes
    void closeBox() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switchMatrix[i][j] = 0;
            }
        }
    }

    //reset the game
    void resetAll() {
        model.cleverPrisonNum = 0;
        resetRound();
        model.steps =0;
        closeBox();
    }

    //repeat the 100-prisoners trail 300 times
    void threeHundreds(){
        model.clearNum();
        while(model.attempt <300){
            model.attempt ++;
            resetAll();
            model.reGame();
            gameState =0;
            seeResult();
            if(gameState ==1){
                model.success ++;
            }
            model.countRate();
        }
        System.out.println("attempt:"+ model.attempt);
        System.out.println("success"+ model.success);
        System.out.println("successRate"+ model.successRate);
    }
}
