package joslabs.newsapp.all_news;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import joslabs.newsapp.R;

public class full_article extends AppCompatActivity {
WebView mWebView;
String NewsUrl;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
Bundle extras=getIntent().getExtras();
       NewsUrl= extras.getString("fullUrl");
       // Toast.makeText(this, kk, Toast.LENGTH_SHORT).show();
        mWebView = (WebView) findViewById(R.id.wvfullarticle);



        mWebView.loadUrl(NewsUrl);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebViewClient(new WebViewClient()

                                  {
                                      @Override
                                      public void onPageFinished(WebView view, String url) {
                                          super.onPageFinished(view, url);
               /* if(progressDialog.isShowing())
                progressDialog.dismiss();*/
                                      }
                                      public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                          return true;
                                      }
                                  }
        );

    }

}
