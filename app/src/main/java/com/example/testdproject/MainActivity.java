package com.example.testdproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.testdproject.model.Data;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String response;
    private String jsonURL = "https://randomuser.me/api/?results=5";
    private RecyclerView recyclerView;
    private RogerAdapter rogerAdapter;
    private static ProgressDialog mProgressDialog;
    private Data users;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new Data();

        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.hasFixedSize();

        fetchJSON();

    }
    @SuppressLint("StaticFieldLeak")
    private void fetchJSON(){

        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        new AsyncTask<Void, Void, Data>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                gson = new Gson();
            }

            protected Data doInBackground(Void[] params) {
                Data result = new Data();
                //HashMap<String, String> map=new HashMap<>();
                try {
                    URL url = new URL(jsonURL);
                    HttpURLConnection req = (HttpURLConnection) url.openConnection();
                    try {
                        InputStream in = new BufferedInputStream(req.getInputStream());
                        byte[] buffer = new byte[1024];
                        int byteLetti = 0;
                        response = "";
                        while((byteLetti = in.read(buffer)) != -1) {
                            response += new String(buffer, 0, byteLetti);
                        }
                        Type resultType = new TypeToken<Data>(){}.getType();
                        result = gson.fromJson(response, resultType);
                    }
                    finally {
                        req.disconnect();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            }
            protected void onPostExecute(Data result) {
                //do something with response
                //Log.d("newwwss",result);
                //onTaskCompleted(result,jsoncode);
                users = result;
                rogerAdapter = new RogerAdapter(getBaseContext(), R.layout.rv_item, users.getResults());
                recyclerView.setAdapter(rogerAdapter);
                rogerAdapter.notifyDataSetChanged();
                removeSimpleProgressDialog();
            }
        }.execute();
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
