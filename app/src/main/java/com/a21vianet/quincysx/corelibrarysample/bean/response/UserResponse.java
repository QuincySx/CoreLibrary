package com.a21vianet.quincysx.corelibrarysample.bean.response;

import com.a21vianet.quincysx.corelibrarysample.bean.User;

/**
 * Created by wang.rongqiang on 2017/5/5.
 */

public class UserResponse extends Response<UserResponse.Data> {
    public class Data {
        public User user;

        @Override
        public String toString() {
            return "Data{" +
                    "user=" + user +
                    '}';
        }
    }
}
