package com.anncode.aplicacioncontactos.restApi.model;

import com.anncode.aplicacioncontactos.model.UserId;

/**
 * Created by rodomualdo on 15/03/2017.
 */

public class UserIdResponse {
    UserId userId;
    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }
}
