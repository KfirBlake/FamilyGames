package com.blake.kids.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImageAdapter extends PagerAdapter
{

    private Context m_Context;
    //private int [] m_ImageIds = new int[]{R.drawable.number2, R.drawable.number3,R.drawable.number4,R.drawable.number5};
    private Drawable[] m_ImageDrawable;

    public ImageAdapter(Context m_Context, Drawable[] drawables)
    {
        this.m_Context = m_Context;
        this.m_ImageDrawable = drawables;
    }

    @Override
    public int getCount()
    {
        return m_ImageDrawable.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position)
    {
        ImageView imageView = new ImageView(m_Context);
        //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //imageView.setImageResource(m_ImageIds[position]);
        imageView.setImageDrawable(m_ImageDrawable[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object)
    {
        container.removeView((ImageView) object);
    }
}
