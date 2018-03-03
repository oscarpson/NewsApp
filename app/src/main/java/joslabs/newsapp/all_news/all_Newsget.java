package joslabs.newsapp.all_news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import joslabs.newsapp.R;

/**
 * Created by OSCAR on 4/24/2017.
 */

public class all_Newsget {
   String newsurl,newsheadline,newstime,newsdetails,newsfullarticle;

    public all_Newsget() {
    }

    public all_Newsget(String newsurl, String newsheadline, String newstime, String newsdetails, String newsfullarticle) {
        this.newsurl = newsurl;
        this.newsheadline = newsheadline;
        this.newstime = newstime;
        this.newsdetails = newsdetails;
        this.newsfullarticle = newsfullarticle;
    }

    public String getNewsurl() {
        return newsurl;
    }

    public void setNewsurl(String newsurl) {
        this.newsurl = newsurl;
    }

    public String getNewsheadline() {
        return newsheadline;
    }

    public void setNewsheadline(String newsheadline) {
        this.newsheadline = newsheadline;
    }

    public String getNewstime() {
        return newstime;
    }

    public void setNewstime(String newstime) {
        this.newstime = newstime;
    }

    public String getNewsdetails() {
        return newsdetails;
    }

    public void setNewsdetails(String newsdetails) {
        this.newsdetails = newsdetails;
    }

    public String getNewsfullarticle() {
        return newsfullarticle;
    }

    public void setNewsfullarticle(String newsfullarticle) {
        this.newsfullarticle = newsfullarticle;
    }
}
