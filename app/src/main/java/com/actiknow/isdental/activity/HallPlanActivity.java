package com.actiknow.isdental.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.actiknow.isdental.R;
import com.actiknow.isdental.utils.TouchImageView;
import com.actiknow.isdental.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class HallPlanActivity extends AppCompatActivity {
    ImageView ivBack;
    TouchImageView ivHallPlan;
    //    WebView webView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_hall_plan);
        initView ();
        initData ();

        //   CopyReadAssets();
        initListener ();
    }

    private void initView () {
        ivBack = (ImageView) findViewById (R.id.ivBack);
        ivHallPlan = (TouchImageView) findViewById (R.id.ivHallPlan);
//        webView = (WebView) findViewById (webView);
        Utils.setTypefaceToAllViews (this, ivBack);
    }

    private void initData () {

/*
        progressDialog = new ProgressDialog (this);
        Utils.showProgressDialog (progressDialog, "Loading...", false);

        final Handler handler = new Handler ();
        handler.postDelayed (new Runnable () {
            @Override
            public void run () {
                progressDialog.dismiss ();
            }
        }, 5000);


        // ivHallPlan.setZoom (1);
        webView.setScrollbarFadingEnabled (true);
        webView.getSettings ().setJavaScriptEnabled (true);
        webView.getSettings ().setPluginState (WebSettings.PluginState.ON);
        webView.getSettings ().setDisplayZoomControls (false);
        webView.getSettings ().setLoadWithOverviewMode (true);
        webView.getSettings ().setSupportZoom (false);
        webView.getSettings ().setNeedInitialFocus (true);
        webView.getSettings ().setDefaultZoom (WebSettings.ZoomDensity.FAR);
        webView.getSettings ().setBuiltInZoomControls (true);
        webView.getSettings ().setDisplayZoomControls (false);
        webView.setInitialScale (250);
        webView.setWebViewClient (new Callback ());

        String pdfURL = "http://35.165.111.86/api/extras/hallplan.pdf";
        webView.loadUrl (
                "http://docs.google.com/gview?embedded=true&url=" + pdfURL);

        */
    }

    private void initListener () {
        ivBack.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                finish ();
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    private void CopyReadAssets () {
        AssetManager assetManager = getAssets ();

        InputStream in = null;
        OutputStream out = null;
        File file = new File (getFilesDir (), "hall_plan.pdf");
        try {
            in = assetManager.open ("hall_plan.pdf");
            out = openFileOutput (file.getName (), Context.MODE_WORLD_READABLE);

            copyFile (in, out);
            in.close ();
            in = null;
            out.flush ();
            out.close ();
            out = null;
        } catch (Exception e) {
            Log.e ("tag", e.getMessage ());
        }

        Intent intent = new Intent (Intent.ACTION_VIEW);
        intent.setDataAndType (
                Uri.parse ("file://" + getFilesDir () + "/hall_plan.pdf"),
                "application/pdf");

        startActivity (intent);
    }

    private void copyFile (InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read (buffer)) != - 1) {
            out.write (buffer, 0, read);
        }
    }


    @Override
    public void onBackPressed () {
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading (
                WebView view, String url) {
            return (false);
        }
    }
}
