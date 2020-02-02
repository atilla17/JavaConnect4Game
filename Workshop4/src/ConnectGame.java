import javafx.scene.control.CheckBox;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.Console;
import java.util.Scanner;

public class ConnectGame {

    int gridWidth = 7;
    int gridHeight = 6;
    int currentPlayer = 1;

    boolean gameOn = true;


     ConnectGameDisk[][] gameGrid = new ConnectGameDisk[gridWidth][gridHeight];
    GameGridSection[] cols = new GameGridSection[gridWidth];


    //starts the game
    public void StartGame()
    {
        //initialize columbs
        for (int i = 0; i < gridWidth; i++)
        {
            cols[i] = new GameGridSection(i, gridHeight);
        }

        System.out.println("-----Connect-4 Game Start-----\n Plyaer 1 has red disks player 2 has yellow disks\n Connect 4 to win!");
        DrawGameGridText();


        while (gameOn)
        {
            CycleGame();
        }
        System.out.println("Game over");

    }

    void checkTie()
    {
        int fullStacks = 0;
        for(int i = 0; i< cols.length; i++)
        {
            if(cols[i].GetStackHeight() == gridHeight)
            {
             fullStacks++;
            }
        }
        if(fullStacks == gridWidth)
        {
            System.out.println("Grid full Tie Game!");
            gameOn = false;
        }
    }

    void DropDisk(int player, int col)
    {

        //Find the correct Y position to insert into based on the columb we are inserting into

      int height_ = cols[col].GetStackHeight();
      cols[col].countStack();

      ConnectGameDisk disk = new ConnectGameDisk(player, col, height_);

      gameGrid[col][height_] = disk;
      CheckWin(col, height_, player);


    }

    void CheckWin(int x, int y, int playerId)
    {
        countVerticleDecs(x, y, playerId);
        countHorizontal(x, y, playerId);
        countDiag(x, y, playerId);
    }

    void countDiag(int x, int y, int playerId)
    {

        int ldCount = 0;
        int ruCount = 0;

        int luCount = 0;
        int rdCount = 0;

        //left down
        int xh = x;
        int yv = y;





        //loop Left Down
        for(xh = x, yv = y; xh >= 0 && yv >= 0; xh--, yv --)
        {
            if(gameGrid[xh][yv] != null && gameGrid[xh][yv].GetColor() == playerId)
            {
                ldCount++;
            }
            else
            {
                break;
            }
        }
        //right up loop
        for(xh = x, yv = y; xh < gridWidth && yv < gridHeight; xh++, yv ++)
        {
            if(gameGrid[xh][yv] != null && gameGrid[xh][yv].GetColor() == playerId)
            {
                ruCount++;
            }
            else
            {
                break;
            }
        }

        //left up loop
        for(xh = x, yv = y; xh > 0 && yv < gridHeight; xh--, yv ++)
        {
            if(gameGrid[xh][yv] != null && gameGrid[xh][yv].GetColor() == playerId)
            {
                luCount++;
            }
            else
            {
                break;
            }
        }

        //right down loop
        for(xh = x, yv = y; xh < gridWidth && yv > 0; xh++, yv--)
        {
            if(gameGrid[xh][yv] != null && gameGrid[xh][yv].GetColor() == playerId)
            {
                rdCount++;
            }
            else
            {
                break;
            }
        }

        if(luCount + rdCount >=  5 || ruCount + ldCount >= 5)
        {
            System.out.println("player " + playerId + "has connected 4 diagnaly");
            gameOn = false;
        }



    }

    void countHorizontal(int x, int y, int playerId)
    {
        int lCount = 0;
        int rCount = 0;

        //left Loop
        for (int x_ = x; x_ >= 0; x_--)
        {
            if(gameGrid[x_][y] != null && gameGrid[x_][y].GetColor() == playerId)
            {
                lCount++;
            }
            else
            {
                break;
            }
        }
        //right loop
        for(int xr = x; xr <= gridWidth; xr++)
        {
            if(gameGrid[xr][y] != null && gameGrid[xr][y].GetColor() == playerId)
            {
                rCount++;
            }
            else
            {
                break;
            }
        }

        if(lCount + rCount >= 5)
        {
            System.out.println("Player " + playerId + " has connected 4 lines horizontaly");
            gameOn = false;
        }



    }
    void countVerticleDecs(int x, int y, int playerId)
    {
        int vCount = 0;

        for(int i =  y; i >= 0; i--)
        {
            if(gameGrid[x][i].GetColor() == playerId)
            {
                vCount++;
            }
            else
            {
                break;
            }
        }

        if(vCount >= 4)
        {
            System.out.println("player " + playerId + " has connected 4 lines verticly");
            gameOn = false;
        }

    }



    int TextSelection()
    {
        try {
            Scanner in = new Scanner(System.in);
            int sel = in.nextInt();
            if (cols[sel].GetStackHeight() == gridHeight) {
                System.out.println("The selected row is full please try another");
                return TextSelection();
            }
            return sel;
        }
        catch (Exception e)
        {
            System.out.println("Invalid selection please chose another");
            return  TextSelection();

        }
    }

    void CycleGame()
    {
        //a game cycle starts with a players input;
        System.out.println("player " + currentPlayer + " Select a columb to drop a disk into");

        //Allow the player to select a col to drop into
        int selection = TextSelection();
        DropDisk(currentPlayer, selection);
        DrawGameGridText();


        //Evaluate if game can continue

        //if game can continue cycle the players for next turn
        if(currentPlayer == 1)
        {
            currentPlayer = 2;
        }
        else
        {
            currentPlayer = 1;
        }
    }

    void DrawGameGridText()
    {
        System.out.println("|0|1|2|3|4|5|6|");
        String textGridRepresentation = "";

        for(int i = gridHeight - 1; i >= 0; i--)
        {
            for(int j = 0; j < gridWidth; j++)
            {
                textGridRepresentation += "|";
                if(gameGrid[j][i] != null)
                {
                    if(gameGrid[j][i].GetColor() == 1)
                    {
                        textGridRepresentation += "R";
                    }
                    else
                    {
                        textGridRepresentation += "Y";
                    }
                }
                else
                {
                    textGridRepresentation += " ";
                }
                if(j == gridWidth - 1) {
                    textGridRepresentation += "|";
                }

            }
            textGridRepresentation += '\n';
        }
        textGridRepresentation += "---------------";
        System.out.println(textGridRepresentation );


    }
    void DragGameImage()
    {
        //Future javaFX implementation
    }




    private class GameGridSection{

        private int id;
        private int max;
        private int current;

        public GameGridSection(int id_, int max_)
        {
            id = id_;
            max = max_;
            current = 0;
        }

        public void countStack()
        {
            current++;
        }
        public int GetStackHeight()
        {
            return current;
        }
    }

}



