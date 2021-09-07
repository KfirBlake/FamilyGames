package com.blake.kids.ticTacToe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blake.kids.R;

/**
 * Created by Kfir Blake on 03/09/2021.
 */
public class TicTacToeBoard extends View
{
    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int winningLineColor;

    private boolean winningLine = false;

    private final Paint paint = new Paint();
    private int cellSize = getWidth() / 3;

    private final TicTacToeCode ticTacToeCode;

    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);

        ticTacToeCode = new TicTacToeCode(this);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TicTacToeBoard, 0, 0);
        try
        {
            boardColor = array.getInteger(R.styleable.TicTacToeBoard_boardColor, 0);
            XColor = array.getInteger(R.styleable.TicTacToeBoard_XColor, 0);
            OColor = array.getInteger(R.styleable.TicTacToeBoard_OColor, 0);
            winningLineColor = array.getInteger(R.styleable.TicTacToeBoard_winningLineColor, 0);
        }
        finally
        {
            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = dimension / 3;
        setMeasuredDimension(dimension, dimension);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN)
        {
            int row = (int) Math.ceil(y / cellSize);
            int col = (int) Math.ceil(x / cellSize);

            if (!winningLine)
            {
                if (ticTacToeCode.updateGameBoard(row, col))
                {
                    invalidate();
                    if (ticTacToeCode.checkWinner())
                    {
                        winningLine = true;
                        invalidate();
                    }
                    if (ticTacToeCode.getPlayer() == 1)
                        ticTacToeCode.setPlayer(2);
                    else
                        ticTacToeCode.setPlayer(1);
                }
            }

            invalidate();
            return true;
        }

        return false;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(false);

        drawGameBoard(canvas);
        drawMarkers(canvas);
        if (winningLine)
        {
            paint.setColor(winningLineColor);
            drawWinningLine(canvas);
        }
    }

    private void drawGameBoard(Canvas canvas)
    {
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);

        for (int i = 1; i < 3; i++)
        {
            canvas.drawLine(cellSize * i, 0, cellSize * i, canvas.getWidth(), paint);
        }
        for (int i = 1; i < 3; i++)
        {
            canvas.drawLine(0, cellSize * i, canvas.getWidth(), cellSize * i, paint);
        }
    }

    private void drawMarkers(Canvas canvas)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (ticTacToeCode.getGameBoard()[i][j] > 0)
                {
                    if (ticTacToeCode.getGameBoard()[i][j] == 1)
                        drawX(canvas, i, j);
                    else
                        drawO(canvas, i, j);
                }
            }
        }
    }

    private void drawX(Canvas canvas, int row, int col)
    {
        paint.setColor(XColor);
        canvas.drawLine((float) ((col + 1) * cellSize - cellSize * 0.2),
                        (float) (row * cellSize + cellSize * 0.2),
                        (float) (col * cellSize + cellSize * 0.2),
                        (float) ((row + 1) * cellSize - cellSize * 0.2),
                        paint
        );

        canvas.drawLine((float) (col * cellSize + cellSize * 0.2),
                        (float) (row * cellSize + cellSize * 0.2),
                        (float) ((col + 1) * cellSize - cellSize * 0.2),
                        (float) ((row + 1) * cellSize - cellSize * 0.2),
                        paint
        );
    }

    private void drawO(Canvas canvas, int row, int col)
    {
        paint.setColor(OColor);
        canvas.drawOval((float) (col * cellSize + cellSize * 0.2),
                        (float) (row * cellSize + cellSize * 0.2),
                        (float) (col * cellSize + cellSize - cellSize * 0.2),
                        (float) (row * cellSize + cellSize - cellSize * 0.2),
                        paint
        );
    }

    private void drawHorizontalLine(Canvas canvas, int row, int col)
    {
        canvas.drawLine(col, row * cellSize + cellSize / 2, cellSize * 3, row * cellSize + cellSize / 2, paint);
    }

    private void drawVeriticalLine(Canvas canvas, int row, int col)
    {
        canvas.drawLine(col * cellSize + cellSize / 2, row, col * cellSize + cellSize / 2, cellSize * 3, paint);
    }

    private void drawXLine(Canvas canvas)
    {
        canvas.drawLine(0, cellSize * 3, cellSize * 3, 0, paint);
    }

    private void drawXLineDown(Canvas canvas)
    {
        canvas.drawLine(0, 0, cellSize * 3, cellSize * 3, paint);
    }

    private void drawWinningLine(Canvas canvas)
    {
        switch (ticTacToeCode.getWinType()[2])
        {
            case 1:
                drawHorizontalLine(canvas, ticTacToeCode.getWinType()[0], ticTacToeCode.getWinType()[1]);
                break;
            case 2:
                drawVeriticalLine(canvas, ticTacToeCode.getWinType()[0], ticTacToeCode.getWinType()[1]);
                break;
            case 3:
                drawXLineDown(canvas);
                break;
            case 4:
                drawXLine(canvas);
                break;

        }
    }

    public void newGame()
    {
        ticTacToeCode.newGame();
        winningLine = false;
        invalidate();
    }

    public void setUpGame(Button playAgain, Button home, TextView textViewDisplay, String[] names, ImageView imageViewCurrentPlayer, Bitmap bitmapPlayer1, Bitmap bitmapPlayer2)
    {
        ticTacToeCode.setPlayAgain(playAgain);
        ticTacToeCode.setHome(home);
        ticTacToeCode.setTextViewDisplay(textViewDisplay);
        ticTacToeCode.setNames(names);
        ticTacToeCode.setPlayerImage(imageViewCurrentPlayer);
        ticTacToeCode.setBitmapPlaer1(bitmapPlayer1);
        ticTacToeCode.setBitmapPlaer2(bitmapPlayer2);
    }
}
