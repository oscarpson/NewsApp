package joslabs.newsapp.all_news;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import joslabs.newsapp.R;

/**
 * Created by OSCAR on 4/24/2017.
 */

public class all_NewsViewHolder extends RecyclerView.ViewHolder
{
    RelativeLayout newsLayer;
    TextView newsHeadline,newsDate,newsShowMore,newsDetails,full_article;
    ImageView newsImage;
    public all_NewsViewHolder(View itemView) {
        super(itemView);
        newsDate= (TextView) itemView.findViewById(R.id.txtNewsTime);
        newsHeadline= (TextView) itemView.findViewById(R.id.txtNewsHeadline);
        newsShowMore= (TextView) itemView.findViewById(R.id.txtNewsShowMore);
        newsDetails= (TextView) itemView.findViewById(R.id.txtNewsDetails);
        newsImage= (ImageView) itemView.findViewById(R.id.imgNewsImage);
        newsLayer= (RelativeLayout) itemView.findViewById(R.id.newsLayer);
        full_article= (TextView) itemView.findViewById(R.id.txtfull_article);
        newsLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(v.getContext(), joslabs.newsapp.all_news.full_article.class);

               intent.putExtra("fullUrl",full_article.getText().toString());
                v.getContext().startActivity(intent);
            }
        });
    }
}
