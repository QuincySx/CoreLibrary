package com.a21vianet.quincysx.corelibrarysample.main;

import android.view.View;
import android.widget.Toast;

import com.a21vianet.quincysx.corelibrarysample.R;
import com.a21vianet.quincysx.corelibrarysample.bean.ConentInfo;
import com.a21vianet.quincysx.corelibrarysample.bean.User;
import com.a21vianet.quincysx.corelibrarysample.bean.response.ConentInfoResponse;
import com.a21vianet.quincysx.corelibrarysample.bean.response.sss;
import com.a21vianet.quincysx.library.commoncore.utility.SPUtils;
import com.a21vianet.quincysx.library.net.subscriber.HttpSubscriber;

import java.util.List;


/**
 * Created by wamg.rongqiang on 2017/1/10.
 */

public class MainPresenter extends MainContract.Presenter {
    private MainModel mMainModel = new MainModel();
    MainViewModel mMainViewModel = new MainViewModel();
    SPUtils spUtils = new SPUtils();

    @Override
    public void onStart() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_getlist:
                getLoad();
                break;
            case R.id.button_save:
                User user = new User();
                user.setName(250);
                spUtils.putObject("user", user);
                break;
            case R.id.button_get:
                User user1 = spUtils.getObject("user", User.class);
                if (user1 != null) {
                    Toast.makeText(getView().getViewContext(), user1.toString(), Toast
                            .LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void getLoad() {
        mMainModel.getLoad(new HttpSubscriber<ConentInfoResponse>() {
            @Override
            protected void onSuccess(ConentInfoResponse response) {
                List<ConentInfo> results = response.results;
//                ToastUtils.showShortToast(results.toString());
                mMainViewModel.mConentInfo = results.get(0);
                getView().setData(mMainViewModel);
            }
        });

        mMainModel.getLoadss(new HttpSubscriber<sss>() {
            @Override
            protected void onSuccess(sss response) {
                Toast.makeText(getView().getViewContext(), response.toString(), Toast
                        .LENGTH_SHORT).show();
            }
        });

        mMainModel.getLoadStr(new HttpSubscriber<String>() {
            @Override
            protected void onSuccess(String s) {
                Toast.makeText(getView().getViewContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

        mMainModel.getLoadStr(new HttpSubscriber<String>() {
            @Override
            protected void onSuccess(String s) {
                Toast.makeText(getView().getViewContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
