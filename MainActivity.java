package com.karabukuniveristesi.beste_mert.e_nakliye;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView wView;
    private CustomWebViewClient client;
    private String Url= "http://www.alisari.com.tr/blabla/";
    ProgressDialog mPD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mPD = new ProgressDialog(this);
        mPD.setMessage("Yükleniyor...");

        client = new CustomWebViewClient();

        wView   = (WebView) findViewById(R.id.webView1);
            wView.getSettings().setBuiltInZoomControls(true);
            wView.getSettings().setSupportZoom(true);
            wView.getSettings().setDisplayZoomControls(false);
            wView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            wView.getSettings().setAllowFileAccess(true);
            wView.getSettings().setDomStorageEnabled(true);
            wView.getSettings().setJavaScriptEnabled(true);
            wView.getSettings().setLoadsImagesAutomatically(true);
            wView.setWebViewClient(client);
        if(isOnline()){
            wView.loadUrl(Url);
        }else{
            Toast.makeText(getApplicationContext(),"İnternet bağlantısı bulunamadı",Toast.LENGTH_LONG).show();

        }

    }

    private class CustomWebViewClient extends WebViewClient{

        @Override
        public void onPageFinished(WebView  view, String url){
            super.onPageFinished(view,url);

            if(mPD.isShowing()){
                mPD.dismiss();
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view,int errorCode, String description, String failingUrl){

        }
    }
        public void onBackPressed(){
            if(wView.canGoBack()){
                wView.goBack();
            }else{
                super.onBackPressed();
            }
        }

        public boolean isOnline(){
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()){
                return true;
            }
            return false;
        }
}
