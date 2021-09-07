package com.blake.kids;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blake.kids.ticTacToe.TicTacToeBoard;

public class TicTacToeGame extends AppCompatActivity
{
    TicTacToeBoard ticTacToeBoard;
    Button btnNewGame;
    Button btnHome;
    TextView textViewDisplay;
    ImageView imgTicTacToeCurrentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_game);

        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);
        btnNewGame = findViewById(R.id.btnNewGame);
        btnHome = findViewById(R.id.btnHome);
        textViewDisplay = findViewById(R.id.textViewDisplay);
        imgTicTacToeCurrentPlayer = findViewById(R.id.imgTicTacToeCurrentPlayer);

        btnNewGame.setVisibility(View.GONE);
        btnHome.setVisibility(View.GONE);

        String[] names = getIntent().getStringArrayExtra("PLAYERS_NAME");
        if(names == null)
        {
            names = new String[] {"עדן", "מעיין"};
        }
        Bitmap bitmap1 = (Bitmap) getIntent().getParcelableExtra("PICTURE_PLAYER1");
        Bitmap bitmap2 = (Bitmap) getIntent().getParcelableExtra("PICTURE_PLAYER2");
        textViewDisplay.setText("התור של " + names[0] + " X");
        imgTicTacToeCurrentPlayer.setImageBitmap(bitmap1);
        ticTacToeBoard.setUpGame(btnNewGame, btnHome, textViewDisplay, names, imgTicTacToeCurrentPlayer, bitmap1, bitmap2);

    }

    public void ticTacToeNewGame(View view)
    {
        ticTacToeBoard.newGame();

    }
}