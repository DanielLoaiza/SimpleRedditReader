package com.dafelo.co.redditreader.deserializer;

import android.util.Log;

import com.dafelo.co.redditreader.main.domain.RedditListing;
import com.dafelo.co.redditreader.subreddits.domain.SubReddit;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by root on 7/01/17.
 */

public class RedditObjectJsonDeserializer implements JsonDeserializer {
    private static String TAG = "RedditDeserializer";

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || !json.isJsonObject())
        {
            return null;
        }

        try
        {
            JsonObject jsonObject = json.getAsJsonObject();
            String kind = jsonObject.get("kind").getAsString();

            return context.deserialize(jsonObject.get("data"), getClassForKind(kind));
        }
        catch (JsonParseException e)
        {
            Log.e(TAG, String.format("Couldn't deserialize Reddit element: %s", json.toString()));
            return null;
        }
    }

    private Class getClassForKind(String kind)
    {
        switch (kind)
        {
            case "Listing":
                return RedditListing.class;
            case "t5":
                return SubReddit.class;
            default:
                Log.e(TAG, String.format("Unsupported Reddit kind: %s", kind));
                return null;
        }
    }
}
