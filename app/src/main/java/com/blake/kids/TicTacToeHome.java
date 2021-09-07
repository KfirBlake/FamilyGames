package com.blake.kids;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TicTacToeHome extends AppCompatActivity
{

    EditText TextViewName1;
    EditText TextViewName2;
    Button btnStartGame;
    Button btnTakePicutre1;
    Button btnTakePicutre2;
    ImageView imageViewPlayer1;
    ImageView imageViewPlayer2;

    Bitmap bitmapPlayer1;
    Bitmap bitmapPlayer2;
    int playerPicture = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe_home);

        TextViewName1 = findViewById(R.id.editTextTicTacToeName1);
        TextViewName2 = findViewById(R.id.editTextTicTacToeName2);

        imageViewPlayer1 = findViewById(R.id.imgTicTacToePlayer1);
        imageViewPlayer2 = findViewById(R.id.imgTicTacToePlayer2);

        btnStartGame = findViewById(R.id.btnTicaTacToeStartPlay);
        btnStartGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String firstName = TextViewName1.getText().toString();
                String secondName = TextViewName2.getText().toString();
                Intent intent = new Intent(TicTacToeHome.this, TicTacToeGame.class);
                intent.putExtra("PLAYERS_NAME", new String[]{firstName, secondName});
                intent.putExtra("PICTURE_PLAYER1", bitmapPlayer1);
                intent.putExtra("PICTURE_PLAYER2", bitmapPlayer2);
                startActivity(intent);
            }
        });

        btnTakePicutre1 = findViewById(R.id.btnTakePicture1);
        btnTakePicutre1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                playerPicture = 1;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        btnTakePicutre2 = findViewById(R.id.btnTakePicture2);
        btnTakePicutre2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                playerPicture = 2;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0)
        {
            if (playerPicture == 1)
            {
                bitmapPlayer1 = (Bitmap) data.getExtras().get("data");
                imageViewPlayer1.setImageBitmap(bitmapPlayer1);
            }
            else
            {
                bitmapPlayer2 = (Bitmap) data.getExtras().get("data");
                imageViewPlayer2.setImageBitmap(bitmapPlayer2);
            }
        }
    }
}