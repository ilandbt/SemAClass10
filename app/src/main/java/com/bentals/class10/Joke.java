package com.bentals.class10;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ilandbt on 28/12/2015.
 */
public class Joke {

    private String text;

    public Joke(JSONObject json){
        try {
            text = json.getString("joke");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getText(){
        return text;
    }
}
