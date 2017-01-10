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
 * Created by dafelo on 7/01/17.
 * @author  Daniel Loaiza
 * Class that handles the deserialization of the RedditObject
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
            // get the kind from the jsonObject , the kind tells the type of the reddit data
            JsonObject jsonObject = json.getAsJsonObject();
            String kind = jsonObject.get("kind").getAsString();

            // sends the kind and the data to the correct class
            return context.deserialize(jsonObject.get("data"), getClassForKind(kind));
        }
        catch (JsonParseException e)
        {
            Log.e(TAG, String.format("Couldn't deserialize Reddit element: %s", json.toString()));
            return null;
        }
    }

    /**
     *
     * @param kind , the kind of reddit returned by the service
     * @return the right class to deserializate
     */
    private Class getClassForKind(String kind)
    {
        // reddit can return multiple type of kinds, now we just want to deserialize
        // listing that is the root and t5 that corresponds to subreddits
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
