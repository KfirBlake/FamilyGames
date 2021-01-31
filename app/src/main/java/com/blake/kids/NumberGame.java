package com.blake.kids;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blake.kids.util.GameHelper;

import java.util.Random;

import static android.widget.Toast.makeText;

public class NumberGame extends AppCompatActivity
{

    private Integer num1 = 1;
    private Integer num2 = 2;
    private Integer level = 3;
    private boolean boolMathSignAdd = true;

    EditText resultEditText;
    Button sendButton;
    ImageView numberOneImageView;
    ImageView numberTwoImageView;
    ImageView mathSighImageView;

    private Integer score = 0;
    TextView scoreTextView;
    ImageView[] scoreArrayImageList = new ImageView[5];

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(NumberGame.this);
        //builder.setTitle("אתה בטוח שאתה רוצה לצאת");
        builder.setMessage("האם אתה בטוח שאתה רוצה לצאת?");
        builder.setPositiveButton("כן", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                NumberGame.super.onBackPressed();
            }
        });
        builder.setNegativeButton("לא", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        builder.create().show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_game);

        resultEditText = findViewById(R.id.ResultInputEditText);
        sendButton = findViewById(R.id.ResultButton);
        numberOneImageView = findViewById(R.id.NumberOneImageView);
        numberTwoImageView = findViewById(R.id.NumberTwoImageView);
        mathSighImageView = findViewById((R.id.MathSignImageView));

        scoreTextView = findViewById(R.id.ScoreTextView);
        scoreArrayImageList[0] = findViewById(R.id.Score1_ImageView);
        scoreArrayImageList[1] = findViewById(R.id.Score2_ImageView);
        scoreArrayImageList[2] = findViewById(R.id.Score3_ImageView);
        scoreArrayImageList[3] = findViewById(R.id.Score4_ImageView);
        scoreArrayImageList[4] = findViewById(R.id.Score5_ImageView);

        score = 0;
        GameHelper.getInstance().setScore(NumberGame.this, scoreTextView, scoreArrayImageList, score);

        generateNewGame();

        sendButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    if (resultEditText.getText().toString().equals(""))
                    {
                        makeText(NumberGame.this, "הכנס תוצאה", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Integer answer = 0;
                    try
                    {
                        answer = Integer.valueOf(resultEditText.getText().toString());
                    }
                    catch (Exception e)
                    {
                        makeText(NumberGame.this, "Value is not a Number", Toast.LENGTH_LONG).show();
                    }

                    //Check Result
                    int result;
                    if (boolMathSignAdd)
                        result = num1 + num2;
                    else
                        result = num1 - num2;


                    if (answer.equals(result))
                    {
                        score = GameHelper.getInstance().correctAnswer(NumberGame.this, scoreTextView, scoreArrayImageList, score);
                        generateNewGame();
                    }
                    else
                        score = GameHelper.getInstance().wrongAnswer(NumberGame.this, scoreTextView, scoreArrayImageList, score);
                }
                catch (Exception e)
                {
                    makeText(NumberGame.this, "Problem happen: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void generateNewGame()
    {
        Random r = new Random();
        int bound = 10;
        if (boolMathSignAdd)
        {
            switch (level)
            {
                case 1:
                    bound = 5;
                    break;
                case 2:
                    bound = 7;
                    break;
                case 3:
                    bound = 10;
                    break;
            }
            num1 = r.nextInt(bound) + 1;
            num2 = r.nextInt(bound) + 1;
        }
        else
        {
            num1 = r.nextInt(9) + 2;
            num2 = r.nextInt(9) + 1;
            while (num1 < num2)
            {
                num2 = r.nextInt(9) + 1;
            }
        }

        numberOneImageView.setImageResource(getImageByNumber(num1));
        numberTwoImageView.setImageResource(getImageByNumber(num2));

        resultEditText.setText("");
    }

    public void onRadioButtonClicked(View view)
    {
        if (((RadioButton) view).isChecked())
        {
            switch (view.getId())
            {
                case R.id.LevelOneRadioButton:
                    level = 1;
                    break;
                case R.id.LevelTwoRadioButton:
                    level = 2;
                    break;
                case R.id.LevelThreeRadioButton:
                    level = 3;
                    break;
            }
            generateNewGame();
        }
    }

    public void onRadioButtonClickedMathSign(View view)
    {
        if (((RadioButton) view).isChecked())
        {
            switch (view.getId())
            {
                case R.id.MathSignAdd:
                    boolMathSignAdd = true;
                    mathSighImageView.setImageResource(R.drawable.math_sign_add);
                    break;
                case R.id.MathSignMinus:
                    boolMathSignAdd = false;
                    mathSighImageView.setImageResource(R.drawable.math_sign_minus);
                    break;
            }
            generateNewGame();
        }
    }

    private int getImageByNumber(Integer id)
    {
        switch (id)
        {
            case 1:
                return R.drawable.number1;
            case 2:
                return R.drawable.number2;
            case 3:
                return R.drawable.number3;
            case 4:
                return R.drawable.number4;
            case 5:
                return R.drawable.number5;
            case 6:
                return R.drawable.number6;
            case 7:
                return R.drawable.number7;
            case 8:
                return R.drawable.number8;
            case 9:
                return R.drawable.number9;
            case 10:
                return R.drawable.number10;
            default:
                return R.drawable.ic_launcher_background;
        }
    }

}
