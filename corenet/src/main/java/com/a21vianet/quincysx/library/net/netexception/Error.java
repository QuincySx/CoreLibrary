package com.a21vianet.quincysx.library.net.netexception;

/**
 * Created by wang.rongqiang on 2017/5/26.
 */

public class Error {
    private String err_no;
    private String err_msg;

    public Error() {
    }

    public Error(String err_no, String err_msg) {
        this.err_no = err_no;
        this.err_msg = err_msg;
    }

    public void setErrNo(String errno) {
        this.err_no = err_no;
    }

    public void setErrMsg(String errmsg) {
        this.err_msg = err_msg;
    }

    public String getErrNo() {
        return err_no;
    }

    public String getErrMsg() {
        return err_msg;
    }

    @Override
    public String toString() {
        return "Data{" +
                "err_no='" + err_no + '\'' +
                ", err_msg='" + err_msg + '\'' +
                '}';
    }
}
