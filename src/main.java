public class main {
    //defining variables of the game
    public static int mapSizeRow = 10;
    public static int mapSizeColumn = 10;
    public static int noOfplayerShip = 5;
    public static int noOfcomputerShip = 5;


    public static int[][] oceanMap = new int[mapSizeRow][mapSizeColumn];
    public static int noOfplayershipAlive = noOfplayerShip;
    public static int noOfComputerShipAlive = noOfcomputerShip;

    public static void main(String[] args){
        //GlobalVariables


        BattleShipGame.startGame();

        BattleShipGame.deployUserPlayerShips();

        BattleShipGame.printMap();

        BattleShipGame.deployComputerShips();

        BattleShipGame.printMap();

        //game play starts
        while(true){
            BattleShipGame.playerTurn();
            if (BattleShipGame.gameOverCheck() != 0)
            {
                System.out.println(" ---- GAME OVER ---- ");
                BattleShipGame.printMap();
                break;
            }
            BattleShipGame.computerTurn();
            if (BattleShipGame.gameOverCheck() != 0)
            {
                System.out.println(" ---- GAME OVER ---- ");
                BattleShipGame.printMap();
                break;
            }
        }
        //BattleShipGame.printMapValues(oceanMap);



    }



}

