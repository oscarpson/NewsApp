package joslabs.newsapp.all_news;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import joslabs.newsapp.R;
import joslabs.newsapp.check_network.NointernetActivity;


public class all_News extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private List<all_Newsget> currentNews;
    private SwipeRefreshLayout swipeRefreshLayout;
    final static String MAIN_URL=" https://newsapi.org/v1/articles?source=techcrunch&apiKey=7cb5932571df414b8d861a36d750bd08";

    private CoordinatorLayout coordinatorLayout;

    public all_News() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all__news, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.news_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(null);

        this.coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinatorlayoutnewsfragment);
        this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutUpdatesFragment);

        this.swipeRefreshLayout.setOnRefreshListener(this);

        this.currentNews = new ArrayList<>();
        currentNews.clear();

        fetchNews();


        return view;
    }

    private void fetchNews() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, MAIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("responjson",response);

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String kk=jsonObject.getString("status");
                    Log.d("statusweb",jsonObject.getString("status")+"\n"+jsonObject.getString("source"));
                    // String kkk=jsonObject.getString("sources");
                    Toast.makeText(getContext(),"Success1"+kk+"\n",Toast.LENGTH_LONG).show();

                    JSONArray jsonArray = jsonObject.getJSONArray("articles");

                    Log.d("datajson",jsonArray.toString());



                    for (int i=0;i<jsonArray.length();i++) {

                        JSONObject jor=jsonArray.getJSONObject(i);

                       currentNews.add(new all_Newsget(jor.getString("urlToImage"),jor.getString("title"),jor.getString("publishedAt"),jor.getString("description"),jor.getString("url")));
                       // System.out.print("Image Url: "+jor.getString("photo_url"));

                        Log.d("newsData",jor.getString("urlToImage")+"\n"+jor.getString("title")+"\n"+jor.getString("publishedAt")+"\n"+jor.getString("description")+"\n"+jor.getString("url"));
                        final all_NewsAdapter newsAdapter = new all_NewsAdapter (currentNews, getActivity());

                        recyclerView.setAdapter(newsAdapter);
                    }








                } catch (JSONException e) {
                    Log.e("exception ","Exception encoutered ");
                    //  Toast.makeText(getContext(),"Exception encoutered ",Toast.LENGTH_LONG);
                    e.printStackTrace();

                }
                //JsonArrayRequest jsonArrayRequest=
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof NoConnectionError) {
                    Toast.makeText(getContext(),"No Internet",Toast.LENGTH_LONG).show();
                   new SnackClass(coordinatorLayout,"internet error");

                }




            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                // params.put(USERNAME,vname1);
                //  params.put(PASSWORD,pass1);
                System.out.print("was here");
                Log.e("paramx","params sent" );
                return params;
            }
        };

        int x = 2;// retry count
        stringRequest.setRetryPolicy(new
                DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*48,
                x, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getContext()).

                add(stringRequest);


    }

    public void onRefresh() {
        //presetNews();

        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN);

        swipeRefreshLayout.setRefreshing(true);
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                currentNews.clear();
                fetchNews();

            }
        }, 2000);


    }
}