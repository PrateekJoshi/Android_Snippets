package com.example.prateek.androidapp;

import android.app.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends Activity {

    final String TAG = "My Errors";
    ListView list;
    ActorsAdapter adapter;
    ArrayList<Actors> actors_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=(ListView) findViewById(R.id.list);
        actors_list=new ArrayList<Actors>();

        new ActorsAsynTask().execute("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors");


    }

    public class ActorsAsynTask extends AsyncTask<String,Void,Boolean>{
        @Override
        protected Boolean doInBackground(String... params) {
            try {
                HttpClient client=new DefaultHttpClient();
                HttpPost post=new HttpPost(params[0]);
                HttpResponse response=client.execute(post);

               int status=response.getStatusLine().getStatusCode();


                if(status==200){
                    try {
                        HttpEntity entity=response.getEntity();
                        String data= EntityUtils.toString(entity);

                        JSONObject json_object=new JSONObject(data);
                        JSONArray json_array=json_object.getJSONArray("actors");

                        for(int i=0;i<json_array.length();i++){
                            Actors actor=new Actors();

                            JSONObject current_object=json_array.getJSONObject(i);

                            actor.setName(current_object.getString("name"));
                            actor.setChildren(current_object.getString("children"));
                            actor.setCountry(current_object.getString("country"));
                            actor.setDescription(current_object.getString("description"));
                            actor.setDob(current_object.getString("dob"));
                            actor.setHeight(current_object.getString("height"));
                            actor.setSpouse(current_object.getString("spouse"));
                            actor.setImage(current_object.getString("image"));

                            actors_list.add(actor);

                        }
                        return true;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            if(result==false)
                Toast.makeText(getApplicationContext(),"Could not parse json",Toast.LENGTH_SHORT);
            else {
                Toast.makeText(getApplicationContext(), "json successfully parsed", Toast.LENGTH_SHORT);
                ActorsAdapter adapter=new ActorsAdapter(getApplicationContext(),R.layout.row,actors_list);
                list.setAdapter(adapter);

            }
        }
    }









}
