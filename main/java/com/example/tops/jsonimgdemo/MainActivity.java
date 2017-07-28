package com.example.tops.jsonimgdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<HashMap<String, String>> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.myList);
        arrayList = new ArrayList<>();

        AsynckLoader asynckLoader = new AsynckLoader(MainActivity.this, "http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors", new onAsynckLoader() {
            @Override
            public void onResult(String result) {

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("actors");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("name", "Name    : "+ obj.getString("name"));
                        hashMap.put("description", obj.getString("description"));
                        hashMap.put("dob", obj.getString("dob"));
                        hashMap.put("country", obj.getString("country"));
                        hashMap.put("height", obj.getString("height"));
                        hashMap.put("spouse", "Spouse  : "+obj.getString("spouse"));
                        hashMap.put("children", obj.getString("children"));
                        hashMap.put("image", obj.getString("image"));
                        arrayList.add(hashMap);
                    }

                    String [] keys = {"name" , "spouse"};
                    int [] id = {R.id.txtname, R.id.txtspouse};
                    SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this, arrayList, R.layout.my_layout, keys, id);
                    listView.setAdapter(simpleAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        asynckLoader.execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("name", arrayList.get(position).get("name"));
                intent.putExtra("description", arrayList.get(position).get("description"));
                intent.putExtra("dob", arrayList.get(position).get("dob"));
                intent.putExtra("country", arrayList.get(position).get("country"));
                intent.putExtra("height", arrayList.get(position).get("height"));
                intent.putExtra("spouse", arrayList.get(position).get("spouse"));
                intent.putExtra("children", arrayList.get(position).get("children"));
                intent.putExtra("image", arrayList.get(position).get("image"));

                startActivity(intent);
            }
        });
    }
}
