package com.blake.kids.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blake.kids.BirdDetailedInformation;
import com.blake.kids.R;
import com.blake.kids.model.BirdInfo;
import com.blake.kids.util.GameHelper;

import java.io.IOException;
import java.util.ArrayList;

public class BirdsRecViewAdapter extends RecyclerView.Adapter<BirdsRecViewAdapter.ViewHolder>
{

    private static final String TAG = "BirdsRecViewAdapter";

    private ArrayList<BirdInfo> birdsInfo = new ArrayList<>();

    private Context context;

    public BirdsRecViewAdapter(Context context)
    {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bird_info_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        Log.d(TAG, "onBindViewHolder Called");
        holder.txtName.setText(birdsInfo.get(position).getName());
        holder.txtInfo.setText((birdsInfo.get(position).getInfo()));
        holder.txtFood.setText((birdsInfo.get(position).getFood()));
        String imagePathInAsset = "birds_picture/" + birdsInfo.get(position).getNameOfFilesInEnglish() + ".jpg";
        final String soundNamePathInAsset = "birds_sounds_name/" + birdsInfo.get(position).getNameOfFilesInEnglish() + ".mp3";
        final String soundVoicePathInAsset = "birds_sounds_voice/" + birdsInfo.get(position).getNameOfFilesInEnglish() + ".mp3";
        try
        {
            holder.image.setImageDrawable(Drawable.createFromStream(context.getAssets().open(imagePathInAsset), imagePathInAsset));
        }
        catch (IOException e)
        {
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        holder.imgPlaySound.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                GameHelper.getInstance().playAssetSound(context, soundVoicePathInAsset);
            }
        });
        holder.imgPlayName.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                GameHelper.getInstance().playAssetSound(context, soundNamePathInAsset);
            }
        });

        holder.parent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, BirdDetailedInformation.class);
                intent.putExtra(BirdDetailedInformation.BIRD_NAME_KEY, birdsInfo.get(position).getNameOfFilesInEnglish());
                context.startActivity(intent);
            }
        });

        if (birdsInfo.get(position).isExpanded())
        {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.imgDownArrow.setVisibility(View.GONE);
        }
        else
        {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.imgDownArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount()
    {
        return birdsInfo.size();
    }

    public void setBirdsInfo(ArrayList<BirdInfo> birdsInfo)
    {
        this.birdsInfo = birdsInfo;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txtName, txtInfo, txtFood;
        private ImageView image, imgPlaySound, imgPlayName, imgDownArrow;
        ImageView imgUpArrow;
        private CardView parent;
        private RelativeLayout expandedRelLayout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtBirdInfoName);
            txtInfo = itemView.findViewById(R.id.txtBirdInfoInfo);
            txtFood = itemView.findViewById(R.id.txtBirdInfoFood);
            image = itemView.findViewById(R.id.imgBirdInfoImage);
            imgPlaySound = itemView.findViewById(R.id.imgBirdInfoPlaySound);
            imgPlayName = itemView.findViewById(R.id.imgBirdInfoPlayName);
            imgUpArrow = itemView.findViewById(R.id.btnBirdInfoUpArrow);
            imgDownArrow = itemView.findViewById(R.id.btnBirdInfoDownArrow);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            parent = itemView.findViewById(R.id.birdParent);

            imgDownArrow.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    BirdInfo birdInfo = birdsInfo.get(getAdapterPosition());
                    birdInfo.setExpanded(!birdInfo.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
            imgUpArrow.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    BirdInfo birdInfo = birdsInfo.get(getAdapterPosition());
                    birdInfo.setExpanded(!birdInfo.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
