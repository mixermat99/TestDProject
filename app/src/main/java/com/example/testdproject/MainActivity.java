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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String response;
    final private String url = "https://randomuser.me/api/?results=10";
    private RecyclerView recyclerView;
    private RogerAdapter rogerAdapter;
    private static ProgressDialog mProgressDialog;
    OkHttpClient client = new OkHttpClient();

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

            }

            protected Data doInBackground(String... strins) {
                Gson gson = new Gson();
                final Realm realm = Realm.getDefaultInstance();
                Data result = new Data();
                try {
                        Request request = new Request.Builder().url(url).build();

                        try {
                            Response response = client.newCall(request).execute();
                            response.body().toString();
                        }
                        catch(IOException e){
                            e.printStackTrace();
                    }
                        Type resultType = new TypeToken<Data>(){}.getType();
                        result = gson.fromJson(response, resultType);

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
                return finalData;
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
