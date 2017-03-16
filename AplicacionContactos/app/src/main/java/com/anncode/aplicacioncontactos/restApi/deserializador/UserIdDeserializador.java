package com.anncode.aplicacioncontactos.restApi.deserializador;

import com.anncode.aplicacioncontactos.model.Contacto;
import com.anncode.aplicacioncontactos.model.UserId;
import com.anncode.aplicacioncontactos.restApi.JsonKeys;
import com.anncode.aplicacioncontactos.restApi.model.ContactoResponse;
import com.anncode.aplicacioncontactos.restApi.model.UserIdResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by rodomualdo on 15/03/2017.
 */

public class UserIdDeserializador implements JsonDeserializer<UserIdResponse> {
    @Override
    public UserIdResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UserIdResponse userIdResponse = gson.fromJson(json, UserIdResponse.class);
        JsonArray userIdResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        userIdResponse.setUserId(deserializarUserIdDeJson(userIdResponseData));
        return userIdResponse;
    }

    private UserId deserializarUserIdDeJson(JsonArray userIdResponseData){
        String userId;
        JsonObject userIdResponseDataObject = userIdResponseData.get(0).getAsJsonObject();
        //JsonObject userJson = userIdResponseDataObject.getAsJsonObject(JsonKeys.USER);
        String id = userIdResponseDataObject.get(JsonKeys.USER_ID).getAsString();
        UserId userIdObject = new UserId();
        userId = id;
        userIdObject.setId(userId);
        return userIdObject;
    }
}
