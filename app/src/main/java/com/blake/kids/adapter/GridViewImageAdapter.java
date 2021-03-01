package com.blake.kids.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.blake.kids.CountGame;

import java.util.List;

/**
 * Created by Kfir Blake on 28/02/2021.
 */
public class GridViewImageAdapter extends BaseAdapter
{
    private Context context;
    private CountGame countGame;
    private List<String> images;

    public GridViewImageAdapter(Context context, CountGame countGame, List<String> images)
    {
        this.context = context;
        this.countGame = countGame;
        this.images = images;
    }

    @Override
    public int getCount()
    {
        return images.size();
    }

    @Override
    public Object getItem(int position)
    {
        return images.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        if (convertView == null)
        {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setPadding(8, 8, 8, 8);
            try
            {
                imageView.setImageDrawable(Drawable.createFromStream(context.getAssets().open(countGame.imageFullPath), countGame.imageFullPath));
                //imageView.setImageResource(R.drawable.sf49ers);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        return imageView;
    }
}
