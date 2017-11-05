package joslabs.newsapp.all_news;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import joslabs.newsapp.R;

import static android.R.id.list;

/**
 * Created by OSCAR on 4/24/2017.
 */

public class all_NewsAdapter extends RecyclerView.Adapter<all_NewsViewHolder>{
List<all_Newsget>newsList;
    Context context;
    CoordinatorLayout coordinatorLayout;
    ImageView newsImage;

    public all_NewsAdapter(List<all_Newsget> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;
    }

    @Override
    public all_NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_activity, parent, false);
        all_NewsViewHolder holder = new all_NewsViewHolder(view);
        newsImage= (ImageView) view.findViewById(R.id.imgNewsImage);
        this.coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayoutnewsfragment);
        return holder;
    }

    @Override
    public void onBindViewHolder(all_NewsViewHolder holder, int position) {
        final all_Newsget currentNews = newsList.get(position);


        //use the provided viewHolder on the oncreateviewholder method to populate the current row
        final all_NewsViewHolder localHolder = holder;



        localHolder.newsDate.setText(newsList.get(position).getNewstime());
        localHolder.newsDetails.setText(newsList.get(position).getNewsdetails());
        localHolder.newsHeadline.setText(currentNews.getNewsheadline());
        localHolder.full_article.setText(currentNews.getNewsfullarticle());
        if (!currentNews.getNewsurl().equals(""))
        {
           // localHolder.newsImage.setImageResource(R.drawable.give);
            Picasso
                    .with(context)
                    .load(currentNews.getNewsurl())

                    .fit()
                    .centerCrop()
                    .into(localHolder.newsImage);

        }
        else
            newsImage.setVisibility(View.GONE);




    }

    @Override
    public int getItemCount() {
        return (newsList != null) ? newsList.size() : 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    //Insert a new item to the recyclerview on a predefined position
    public void insert (int position,all_Newsget all_newsget)
    {
        newsList.add(position, all_newsget);
        notifyItemInserted(position);
    }
    public void remove (all_Newsget all_newsget)
    {
        int position = newsList.indexOf (all_newsget);
        newsList.remove(position);
        notifyItemRemoved(position);
    }




}
