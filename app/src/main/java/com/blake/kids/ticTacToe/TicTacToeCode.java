package com.blake.kids.ticTacToe;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blake.kids.R;
import com.blake.kids.util.GameHelper;

import java.util.Random;

/**
 * Created by Kfir Blake on 03/09/2021.
 */
public class TicTacToeCode
{
    TicTacToeBoard ticTacToeBoard;
    Button playAgain;
    Button home;
    TextView textViewDisplay;
    String[] names = {"שחקן X", "שחקן O"};
    ImageView playerImage;
    Bitmap bitmapPlaer1;
    Bitmap bitmapPlaer2;

    private int[][] gameBoard;
    private int player = 1;
    private int[] winType = {-1, -1, -1};

    public TicTacToeCode(TicTacToeBoard ticTacToeBoard)
    {
        this.ticTacToeBoard = ticTacToeBoard;
        gameBoard = new int[3][3];
    }

    public int[][] getGameBoard()
    {
        return gameBoard;
    }

    public int[] getWinType()
    {
        return winType;
    }

    public int getPlayer()
    {
        return player;
    }

    public void setPlayer(int player)
    {
        this.player = player;
    }

    public void setPlayAgain(Button playAgain)
    {
        this.playAgain = playAgain;
    }

    public void setHome(Button home)
    {
        this.home = home;
    }

    public void setTextViewDisplay(TextView textViewDisplay)
    {
        this.textViewDisplay = textViewDisplay;
    }

    public void setNames(String[] names)
    {
        this.names = names;
    }

    public void setPlayerImage(ImageView playerImage)
    {
        this.playerImage = playerImage;
    }

    public void setBitmapPlaer1(Bitmap bitmapPlaer1)
    {
        this.bitmapPlaer1 = bitmapPlaer1;
    }

    public void setBitmapPlaer2(Bitmap bitmapPlaer2)
    {
        this.bitmapPlaer2 = bitmapPlaer2;
    }

    public void newGame()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                gameBoard[i][j] = 0;
            }
        }
        playAgain.setVisibility(View.GONE);
        home.setVisibility(View.GONE);

        Random r = new Random();
        int i = r.nextInt(2);
        switch (i)
        {
            case 0:
                player = 1;
                textViewDisplay.setText("התור של " + names[0] + " X");
                playerImage.setImageBitmap(bitmapPlaer1);
                break;
            case 1:
                player = 2;
                textViewDisplay.setText("התור של " + names[1] + " O");
                playerImage.setImageBitmap(bitmapPlaer2);
                break;

        }


    }

    public boolean updateGameBoard(int row, int col)
    {
        if (gameBoard[row-1][col-1] == 0)
        {
            gameBoard[row-1][col-1] = player;
            if(player == 1)
            {
                textViewDisplay.setText("התור של " + names[1] + " O");
                playerImage.setImageBitmap(bitmapPlaer2);
            }
            else
            {
                textViewDisplay.setText("התור של " + names[0] + " X");
                playerImage.setImageBitmap(bitmapPlaer1);
            }
            return true;
        }
        return false;
    }

    public boolean checkWinner()
    {
        boolean winner = false;
        for (int i=0; i < 3; i++)
        {
            if(gameBoard[i][0]>0 && gameBoard[i][0]==gameBoard[i][1] && gameBoard[i][1]==gameBoard[i][2])
            {
                winType = new int[]{i, 0, 1};
                winner = true;
            }
            if(gameBoard[0][i]>0 && gameBoard[0][i]==gameBoard[1][i] && gameBoard[1][i]==gameBoard[2][i])
            {
                winType = new int[]{0, i, 2};
                winner = true;
            }

        }
        if(gameBoard[0][0]>0 && gameBoard[0][0]==gameBoard[1][1] && gameBoard[1][1]==gameBoard[2][2])
        {
            winType = new int[]{0, 2, 3};
            winner = true;
        }
        if(gameBoard[0][2]>0 && gameBoard[0][2]==gameBoard[1][1] && gameBoard[1][1]==gameBoard[2][0])
        {
            winType = new int[]{2, 0, 4};
            winner = true;
        }

        if (winner)
        {
            playAgain.setVisibility(View.VISIBLE);
            home.setVisibility(View.VISIBLE);
            textViewDisplay.setText(names[player-1] + " ניצח/ה!!!");
            GameHelper.getInstance().playSound(ticTacToeBoard.getContext(), R.raw.success_music);
            if(player == 1)
            {
                playerImage.setImageBitmap(bitmapPlaer1);
            }
            else
            {
                playerImage.setImageBitmap(bitmapPlaer2);
            }
            return true;
        }
        else
        {
            int tie = 0;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (gameBoard[i][j] != 0)
                        tie++;
                }
            }

            if (tie == 9)
            {
                playAgain.setVisibility(View.VISIBLE);
                home.setVisibility(View.VISIBLE);
                textViewDisplay.setText("אף אחד לא ניצח");
                GameHelper.getInstance().playSound(ticTacToeBoard.getContext(), R.raw.explosion);
                return false;
            }
            else
                return false;
        }
    }

}
