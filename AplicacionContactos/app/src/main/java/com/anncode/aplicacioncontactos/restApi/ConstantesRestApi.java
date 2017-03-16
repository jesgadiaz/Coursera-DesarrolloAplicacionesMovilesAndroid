package com.anncode.aplicacioncontactos.restApi;

/**
 * Created by rodomualdo on 15/03/2017.
 */

public class ConstantesRestApi {

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "3463889310.b9104ff.e1344beb6c374749ac5fd3c32d98becc";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    // Other users
    public static final String KEY_USERS = "users/";
    public static final String KEY_MEDIA_RECENT = "media/recent/";
    public static final String KEY_GET_USER_ID = "users/search/";
    public static final String KEY_USER_NAME = "?q=";
    public static final String KEY_ACCESS_TOKEN_SECOND_POSITION = "&access_token=";
    public static final String URL_GET_USER_ID = KEY_GET_USER_ID + KEY_USER_NAME + "lobo_mlk7" + KEY_ACCESS_TOKEN_SECOND_POSITION + ACCESS_TOKEN;
}