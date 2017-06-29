package com.a21vianet.quincysx.corelibrarysample;

import android.util.Log;

import com.a21vianet.quincysx.library.net.netexception.ApiException;
import com.a21vianet.quincysx.library.net.netexception.Error;
import com.a21vianet.quincysx.library.net.converter.jsonparse.GsonParse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * Created by wang.rongqiang on 2017/5/26.
 * 通用的处理错的示例
 * 这个地方自己继承相应的Json解析器
 */

public class ExtGsonParse<T> extends GsonParse<T> {
    @Override
    public T responseConvert(ResponseBody value, Type type) throws IOException {
        //自行用自己的方法 来接收服务器的错误 Json
        //此处value.string() 方法关闭了 value 所以最后 responseConvert(String,Type) 应该掉这个方法
        String jsonStr = value.string();
        try {
            JSONObject jsonObject = new JSONObject();
            boolean error = jsonObject.getBoolean("error");
            if (error) {
                Log.e("====", "失败");
                //理论上这就是这就是服务器报告一个错误
                Error error1 = new Error("services_error", "不知道");
                throw new ApiException(error1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return super.responseConvert(jsonStr, type);
    }
}


