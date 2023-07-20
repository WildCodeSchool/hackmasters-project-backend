package com.templateproject.api.utils;

import com.nimbusds.jose.shaded.gson.Gson;
import com.nimbusds.jose.shaded.gson.GsonBuilder;

import java.time.Instant;

public class GsonFactory {
    public static Gson createGsonWithInstantAdapter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Instant.class, new InstantTypeAdapter());
        return gsonBuilder.create();
    }

}
