package com.a21vianet.quincysx.corelibrarysample.main;

import com.a21vianet.quincysx.corelibrary.mvp.BaseModel;
import com.a21vianet.quincysx.corelibrary.mvp.BasePresenter;
import com.a21vianet.quincysx.corelibrary.mvp.BaseView;
import com.a21vianet.quincysx.corelibrarysample.bean.response.ConentInfoResponse;
import com.a21vianet.quincysx.corelibrarysample.bean.response.sss;
import com.a21vianet.quincysx.library.net.subscriber.HttpSubscriber;

/**
 * Created by wamg.rongqiang on 2017/1/10.
 */

public interface MainContract {
    interface View extends BaseView {
        void setData(MainViewModel mainViewModel);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void onClick(android.view.View view);

        public abstract void getLoad();
    }

    interface Model extends BaseModel {
        void getLoadss(HttpSubscriber<sss> subscriber);

        void getLoadStr(HttpSubscriber<String> subscriber);

        void getLoad(HttpSubscriber<ConentInfoResponse> subscriber);
    }
}
