package com.bentals.class10;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView jokeTV;
    private Button button;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        jokeTV = (TextView) findViewById(R.id.jokeText);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showProgressDialog(v.getContext(), "Loading...");
                getJoke();
            }
        });

    }

    //http://www.icndb.com/api/
    private void getJoke(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://api.icndb.com/jokes/random/1";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jokes = response.getJSONArray("value");
                    if (jokes.length() > 0){
                        Joke joke = new Joke(jokes.getJSONObject(0));
                        jokeTV.setText(joke.getText());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Utils.cancelProgressDialog();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.cancelProgressDialog();
                Toast.makeText(context, "Failed to get joke", Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(request);


    }
}
