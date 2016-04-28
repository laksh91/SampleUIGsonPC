package com.example.lakshmikanta.sampleui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.example.lakshmikanta.sampleui.model.*;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        try {
            new JsonParser(this).execute();
        } catch (Exception e) {
        }
    }

    class JsonParser extends AsyncTask<ArrayList<LabvaluesModel>, ArrayList<LabvaluesModel>, ArrayList<LabvaluesModel>> {
        private Context mContext;
        public ArrayList<LabvaluesModel> labvaluelist = new ArrayList<>();

        public JsonParser(MainActivity mainActivity) {
            this.mContext = mainActivity;
        }

        @Override
        protected ArrayList<LabvaluesModel> doInBackground(ArrayList<LabvaluesModel>... params) {
            parseData(mContext);
            return labvaluelist;
        }

        protected void onPostExecute(ArrayList<LabvaluesModel> listvalues) {
            super.onPostExecute(listvalues);
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), labvaluelist);
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
        }

        private void parseData(Context c) {
            String JSON = loadJSONFromAsset(c);
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<LabvaluesModel>>() {}.getType();
            labvaluelist = (ArrayList<LabvaluesModel>) gson.fromJson(JSON, listType);
        }

        private String loadJSONFromAsset(Context c) {
            String json = null;
            try {
                InputStream is = c.getAssets().open("LabValues.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            return json;
        }
    }
}


