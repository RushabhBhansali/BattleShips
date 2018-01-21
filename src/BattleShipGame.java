import java.util.Scanner;
//import java.util.Random;

public class BattleShipGame {

    //this method is called at the start of the game
    public static void startGame() {
        //int[][] oceanMap = new int[10][10];
        //clearing the oceanmap a the start of the game
        int r = main.oceanMap.length;
        for (int i = 0; i < r; i++) {
            for (int c = 0; c < main.oceanMap[i].length; c++) {
                main.oceanMap[i][c] = 0;
            }
        }

        System.out.println("*** Welcome to Battle Ships game *** \n \n ");
        System.out.println("Right now, the see is empty \n");
        BattleShipGame.printMap();
    }

    //this method takes 2d array as parameter and prints it to the console whenever it is called.
    public static void printMap() {
        int r = main.oceanMap.length;
        System.out.println("    0 1 2 3 4 5 6 7 8 9   ");
        for (int i = 0; i < r; i++) {
            System.out.print(i + " |");
            for (int c = 0; c < main.oceanMap[i].length; c++) {
                if(main.oceanMap[i][c] == 1){
                    System.out.print(" @");
                }
                else if(main.oceanMap[i][c] == 3 || main.oceanMap[i][c] == 6){
                    System.out.print(" x");

                }else if(main.oceanMap[i][c] == 4 || main.oceanMap[i][c] == 7){
                    System.out.print(" !");
                }
                else if(main.oceanMap[i][c] == 5 || main.oceanMap[i][c] == 9){
                    System.out.print(" -");
                }
                else{
                    System.out.print("  ");
                }
            }
            System.out.println("| " + i);
        }
        System.out.println("    0 1 2 3 4 5 6 7 8 9   ");
    }

    //prints raw values of the game map, used for debugging purpose
    public static void printMapValues(){
        int r = main.oceanMap.length;
        System.out.println("    0 1 2 3 4 5 6 7 8 9   ");
        for (int i = 0; i < r; i++) {
            System.out.print(i + " |");
            for (int c = 0; c < main.oceanMap[i].length; c++) {
                System.out.print((" "+main.oceanMap[i][c]));
            }
            System.out.println("| " + i);
        }
        System.out.println("    0 1 2 3 4 5 6 7 8 9   ");
    }

    public static int deployUserPlayerShips(){
        Scanner input = new Scanner(System.in);
        System.out.println("Deploy Player Ships");
        int counter  = 1;
        do{
            System.out.println("Enter X coordinate for your ship "+counter+":");
            int x = input.nextInt();
            System.out.println("Enter Y coordinate for your ship "+counter+":");
            int y = input.nextInt();
            if(x<0 || x>main.oceanMap[0].length-1 || y<0 || y>main.oceanMap.length-1){
                System.out.println("you can’t place ships outside the "+main.oceanMap[0].length+" by "+main.oceanMap.length+" grid");
            }
            else if(main.oceanMap[x][y] == 1){
                System.out.println("you can NOT place two or more ships on the same location.");
            }
            else{
                main.oceanMap[x][y] = 1;
                counter++;
            }
        }while(counter<=main.noOfplayerShip);
        return 1;
    }

    public static int deployComputerShips(){
        System.out.println("Deploying Computer Ships");
        int counter = 1;
        do{
            int x = (int)(Math.random()*9+1);
            int y = (int)(Math.random()*9+1);
            if(x>0 && x<main.oceanMap[0].length-1 && y>0 && y<main.oceanMap.length-1 && (main.oceanMap[x][y] != 2) && (main.oceanMap[x][y]!=1)){
                main.oceanMap[x][y] = 2;
                System.out.println("Computer Ship "+counter+" deployed.");
                counter++;
            }
        }
        while(counter <= main.noOfcomputerShip);
        return 1;
    }

    public static void playerTurn(){
        System.out.println("Player Turn:");
        Scanner input = new Scanner(System.in);
        int x1,y1;

        //checking that player's guess falls within the grid and the cordinates has not been guessed before.
        while(true){
            System.out.print("Enter X coordinate:");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate:");
            int y = input.nextInt();
            if(x<0 || x>main.oceanMap[0].length-1 || y<0 || y>main.oceanMap.length-1){
                System.out.println("coordinates are outside of the "+main.oceanMap[0].length+" by "+main.oceanMap.length+" grid");
            }

            else if(main.oceanMap[x][y] == 3 || main.oceanMap[x][y] == 4 || main.oceanMap[x][y] == 5 ||  main.oceanMap[x][y] == 6 || main.oceanMap[x][y] == 7 || main.oceanMap[x][y] == 9){
                System.out.println("These coordinates are already guessed");
            }

            else{
                x1 = x;
                y1 = y;
                break;
            }
        }
        //player wins = 3, player destroys own ship = 4 player misses = 5
        if(main.oceanMap[x1][y1]==2)
        {
            main.oceanMap[x1][y1] = 3;
            System.out.println("Boom! You sunk the ship!");
            main.noOfComputerShipAlive--;

        }
        else if(main.oceanMap[x1][y1] == 1)
        {
            main.oceanMap[x1][y1] = 4;
            System.out.println("Oh no, you sunk your own ship :(");
            main.noOfplayershipAlive--;
        }
        else if(main.oceanMap[x1][y1] == 8)
        {
            main.oceanMap[x1][y1] = 9;
            System.out.println("Sorry, you missed");
        }

        else
        {
            main.oceanMap[x1][y1] = 5;
            System.out.println("Sorry, you missed");
        }

        printMap();
        printMapValues();
        System.out.println("Your ships: "+main.noOfplayershipAlive+" | Computer Ships: "+main.noOfComputerShipAlive);
        System.out.println("------------------------------------------");

    }

    public static void computerTurn(){
        System.out.println("\n Computer's Turn:");
        int x1,y1;
        //checking that player's guess falls within the grid and the cordinates has not been guessed before.
        while(true){
            int x = (int)(Math.random()*9+1);
            int y = (int)(Math.random()*9+1);
            if(x<0 || x>main.oceanMap[0].length-1 || y<0 || y>main.oceanMap.length-1){
                //do nothing. the loop will repeat
            }
            else if(main.oceanMap[x][y] == 6 || main.oceanMap[x][y] == 7 || main.oceanMap[x][y] == 8 || main.oceanMap[x][y] == 9 || main.oceanMap[x][y] == 3 || main.oceanMap[x][y] == 4){
                //do nothing the loop will repeat
            }

            else{
                x1 = x;
                y1 = y;
                break;
            }
        }
        //computer wins = 6, computer destroys own ship = 7 player misses = 8
        if(main.oceanMap[x1][y1]==1)
        {
            main.oceanMap[x1][y1] = 6;
            System.out.println("The Computer sunk one of your ships!");
            main.noOfplayershipAlive--;

        }
        else if(main.oceanMap[x1][y1] == 2)
        {
            main.oceanMap[x1][y1] = 7;
            System.out.println("The Computer sunk one of its own ships");
            main.noOfComputerShipAlive--;
        }
        else if(main.oceanMap[x1][y1] == 5)
        {
            main.oceanMap[x1][y1] = 9;
            System.out.println("Computer missed");
        }
        else
        {
            main.oceanMap[x1][y1] = 8;
            System.out.println("Computer missed");
        }
        //return 1;
        printMap();
        //printMapValues();
        System.out.println("Your ships: "+main.noOfplayershipAlive+" | Computer Ships: "+main.noOfComputerShipAlive);
        System.out.println("------------------------------------------");
    }

    public static int gameOverCheck(){
        int returnValue = 0;
        if(main.noOfComputerShipAlive == 0){
            System.out.println("Your ships: "+main.noOfplayershipAlive+" | Computer Ships: "+main.noOfComputerShipAlive);
            System.out.println("hooray! you win the battle");
            returnValue = 1;
        }

        else if(main.noOfplayershipAlive == 0){
            System.out.println("Your ships: "+main.noOfplayershipAlive+" | Computer Ships: "+main.noOfComputerShipAlive);
            System.out.println("Computer Wins :(");
            returnValue = 2;
        }
        return returnValue;
    }

}
