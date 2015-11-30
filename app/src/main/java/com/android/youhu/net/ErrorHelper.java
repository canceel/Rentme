package com.android.youhu.net;

import com.android.youhu.R;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;


/**
 * Created by Administrator on 2015/7/8.
 */
public class ErrorHelper {

    public static int getMessage(VolleyError error) {
        if (error instanceof TimeoutError) {
            return R.string.network_error_timeout;
        } else if (isNetworkProblem(error)) {
            return R.string.network_error_timeout;
        } else if (isServerProblem(error)) {
            return R.string.network_error_generic;
        } else if (error instanceof ParseError) {
            return R.string.network_error_parse;
        } else {
            return R.string.network_error_generic;
        }
    }

    private static boolean isNetworkProblem(VolleyError error) {
        return (error instanceof NetworkError || error instanceof NoConnectionError);
    }

    private static boolean isServerProblem(VolleyError error) {
        return (error instanceof AuthFailureError || error instanceof ServerError);
    }
}
