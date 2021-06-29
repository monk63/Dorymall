package com.dorymall.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    private LinearLayout noInternetLayout;

ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();// This line hides the action bar.
        setContentView(R.layout.activity_main);

        noInternetLayout=findViewById(R.id.noInternetLayout);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());

        progressBar=findViewById(R.id.pbar);
        progressBar.setMax(100);
        webView.setWebViewClient(new WebViewClient());



       // webView.loadUrl("https://dorynmall.com/");

        checkInternet();

        //Displaying Toast with loading message
       Toast.makeText(getApplicationContext(),"Chargement...",Toast.LENGTH_LONG).show();

        progressBar.setProgress(0);

        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress == 100)
                    progressBar.setVisibility(View.INVISIBLE);

                else
                    progressBar.setVisibility(View.VISIBLE);
                super.onProgressChanged(view, newProgress);
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


    }

    private void checkInternet(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi.isConnected()){
            webView.loadUrl("https://dorynmall.com/");
            webView.setVisibility(View.VISIBLE);
            noInternetLayout.setVisibility(View.INVISIBLE);
        }
        else if(mobile.isConnected()){
            webView.loadUrl("https://dorynmall.com/");
            webView.setVisibility(View.VISIBLE);
            noInternetLayout.setVisibility(View.INVISIBLE);
        } else {
            webView.setVisibility(View.INVISIBLE);
            noInternetLayout.setVisibility(View.VISIBLE);

        }

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }




}
