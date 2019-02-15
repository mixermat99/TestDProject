package com.example.testdproject;

import android.app.AlertDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.testdproject.model.Data;
import com.example.testdproject.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    String response;
    private String jsonURL = "https://randomuser.me/api/?results=10";
    private RecyclerView recyclerView;
    private RogerAdapter rogerAdapter;
    private static ProgressDialog mProgressDialog;
    Gson gson;
    private Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.hasFixedSize();
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();
        RealmResults startupResults = realm.where(User.class).findAll();
        RogerRealmCustomAdapter rogerRealmCustomAdapter = new RogerRealmCustomAdapter(startupResults, true);
        recyclerView.setAdapter(rogerRealmCustomAdapter);

        //Refresh and Fetch
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchJSON();
                pullToRefresh.setRefreshing(false);
            }
        });

        removeButton = findViewById(R.id.RemoveButton);
        removeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {

                    @Override
                    public void execute(Realm realm) {
                        realm.deleteAll();
                    }
                });
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public void fetchJSON() {
        //showSimpleProgressDialog(this, "Loading...","I Tuoi dati saranno disponibili tra poco...",false);

        new AsyncTask<String, Void, Data>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                gson = new Gson();
            }

            protected Data doInBackground(String... strins) {
                final Realm realm = Realm.getDefaultInstance();
                Data result = new Data();
                try {
                    URL url = new URL(jsonURL);
                    HttpURLConnection req = (HttpURLConnection) url.openConnection();
                    try {
                        InputStream in = new BufferedInputStream(req.getInputStream());
                        byte[] buffer = new byte[1024];
                        int byteLetti = 0;
                        response = "";
                        while ((byteLetti = in.read(buffer)) != -1) {
                            response += new String(buffer, 0, byteLetti);
                        }
                        Type resultType = new TypeToken<Data>(){}.getType();
                        result = gson.fromJson(response, resultType);
                    } finally {
                        req.disconnect();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    final Data finalData = result;
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.insert(finalData.getResults());
                        }
                    });
                return result;
            }

            @Override
            protected void onPostExecute(Data result) {
                Realm realm = Realm.getDefaultInstance();
                RealmResults<User> realmResults = realm
                        .where(User.class)
                        .findAll();
                RogerRealmCustomAdapter rogerRealmCustomAdapter = new RogerRealmCustomAdapter(realmResults, true);
                recyclerView.setAdapter(rogerRealmCustomAdapter);
            }
        }.execute();
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog == null) {
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
                mProgressDialog.show(context, title, msg);
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
