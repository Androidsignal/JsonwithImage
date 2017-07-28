package com.example.tops.jsonimgdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tops on 4/17/2017.
 */

public class AsynckLoader extends AsyncTask<Void, Void, String> {

    Context context;
    String myUrl;
    ProgressDialog progressDialog;
    onAsynckLoader onAsynckLoader;

    public AsynckLoader(Context context, String myUrl, onAsynckLoader onAsynckLoader){
        this.context = context;
        this.myUrl = myUrl;
        this.onAsynckLoader = onAsynckLoader;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Wait");
        progressDialog.setMessage("Data Is Loading......");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = "";

        try {
            URL url = new URL(myUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            if (httpURLConnection.getResponseCode() == 200){

                InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line;
                while ((line = bufferedReader.readLine()) !=null){
                        result += line;
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onAsynckLoader.onResult(s);
        progressDialog.dismiss();
    }
}
