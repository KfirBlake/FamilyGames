package com.blake.kids.util;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blake.kids.R;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameHelper
{
    private static GameHelper instance = null;

    Animation leftAnim;

    public static GameHelper getInstance()
    {
        if (instance == null)
            instance = new GameHelper();

        return instance;
    }

    private GameHelper()
    {
    }

    private int numberOfErrorToReduce = 2;

    public int getNumberOfErrorToReduce()
    {
        return numberOfErrorToReduce;
    }

    public void setNumberOfErrorToReduce(int numberOfErrorToReduce)
    {
        this.numberOfErrorToReduce = numberOfErrorToReduce;
    }

    private int numberOfSecondForTimeout = 20;

    public int getNumberOfSecondForTimeout()
    {
        return numberOfSecondForTimeout;
    }

    public void setNumberOfSecondForTimeout(int numberOfSecondForTimeout)
    {
        this.numberOfSecondForTimeout = numberOfSecondForTimeout;
    }

    boolean timeOutFlag = false;

    public void setTimeOutFlag(boolean timeOutFlag)
    {
        this.timeOutFlag = timeOutFlag;
    }

    public boolean getTimeOutFlag()
    {
        return timeOutFlag;
    }


    private MediaPlayer mediaPlayer;

    boolean playSoundFlag = true;

    public void setPlaySoundFlag(boolean playSoundFlag)
    {
        this.playSoundFlag = playSoundFlag;
    }


    // ********** Sound   **********
    //Sound
    public void playSound(Context context, int resource)
    {
        if (!playSoundFlag)
            return;

        if (mediaPlayer != null && mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(context, resource);
        mediaPlayer.start();
    }

    public void playAssetSound(Context context, String soundFileName)
    {
        if (!playSoundFlag)
            return;
        try
        {
            if (mediaPlayer != null)
            {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
            mediaPlayer = new MediaPlayer();

            AssetFileDescriptor descriptor = context.getAssets().openFd(soundFileName);
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            mediaPlayer.prepare();
            //mediaPlayer.setVolume(1f, 1f);
            mediaPlayer.setLooping(false);
            mediaPlayer.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void releaseMediaPlayer()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public int getSuccessSound()
    {
        Random r = new Random();
        int i = r.nextInt(3);
        switch (i)
        {
            case 0:
                return R.raw.all_right;
            case 1:
                return R.raw.excellent;
            case 2:
                return R.raw.good;
        }
        return R.raw.all_right;
    }

    public int getFailedSound()
    {
        return R.raw.try_again;
    }


    // ********** Images / Files   **********
    // Images / Files
    public List<String> loadFromAssetsNameList(Context context, String path)
    {
        final AssetManager assets = context.getAssets();
        if (assets != null)
        {
            try
            {
                return new LinkedList<>(Arrays.asList(assets.list(path)));
            }
            catch (Exception e)
            {
                return new LinkedList<>();
            }
        }
        else
            return new LinkedList<>();
    }


    // ********** Answer and Score   **********
    //Answer and Score

    public Integer correctAnswer(Context context, TextView scoreTextView, ImageView[] scoreArrayImageList, Integer score)
    {
        playSound(context, getSuccessSound());
        score++;
        setScore(context, scoreTextView, scoreArrayImageList, score);
        return score;
    }

    public Integer wrongAnswer(Context context, TextView scoreTextView, ImageView[] scoreArrayImageList, Integer score)
    {
        playSound(context, getFailedSound());
        score = score - numberOfErrorToReduce;
        if (score < 0)
            score = 0;
        setScore(context, scoreTextView, scoreArrayImageList, score);
        return score;
    }

    public void setScore(Context context, TextView scoreTextView, ImageView[] scoreArrayImageList, Integer score)
    {
        try
        {
            if (score > 0 && (score % 5) == 0)
            {
                playSound(context, R.raw.success_music);
            }

            scoreTextView.setText(String.valueOf(score));

            int i;
            for (i = 0; i < 5; i++)
            {
                scoreArrayImageList[i].setImageResource(0);
            }

            int cup = score / 30;
            if (cup > 0)
            {
                leftAnim = AnimationUtils.loadAnimation(context, R.anim.left_animation);
            }
            for (i = 0; i < cup && i < 5; i++)
            {
                scoreArrayImageList[i].setImageResource(R.drawable.winner_cup);
                scoreArrayImageList[i].setAnimation(leftAnim);
            }

            int stars = (score - (30 * cup)) / 5;
            for (i = cup; i < stars && i < 5; i++)
            {
                //TODO: check if to add Star with Animation
                scoreArrayImageList[i].setImageResource(R.drawable.star);
            }
        }
        catch (Exception e)
        {
            //makeText(LetterGame.this, "Problem happen: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void timeOut(Context context, String message, Integer imageResource)
    {
        playSound(context, R.raw.explosion);
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setTitle("הזמן נגמר");
        TextView dialogMessageTextView = dialog.findViewById(R.id.dialog_custom_message);
        dialogMessageTextView.setText(message);
        ImageView dialogImageView = dialog.findViewById(R.id.dialog_custom_image);
        dialogImageView.setImageResource(imageResource);
        Button dialogButton = dialog.findViewById(R.id.dialog_custom_ButtonOK);
        dialogButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
