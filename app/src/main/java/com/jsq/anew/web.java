package com.jsq.anew;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.nio.channels.NetworkChannel;

/**
 * Created by 尚文 on 2017/2/3.
 */

public class web extends Activity {
    private WebView webView;
    private ProgressDialog progressDialog;
    private IntentFilter intentFilter;
    private NetworkChangeReciver networkChangeReciver;

    class NetworkChangeReciver extends BroadcastReceiver {
        @Override
        /*动态注册接收器——因为注册逻辑写在onCreate中*/
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(web.this, R.string.available, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(web.this, R.string.unavailable, Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReciver = new NetworkChangeReciver();
        /*进行注册，两个实例都要传*/
        registerReceiver(networkChangeReciver, intentFilter);
        init();
    }

    @Override
    /*
    动态注册的广播接收器一定要取消注册才行
    还要声明查询状态权限
    * */
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReciver);
    }

    private void init() {
        webView = (WebView) findViewById(R.id.webview1);
        /*启用支持JavaScript*/
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        /*WebView加载页面优先使用缓存加载,其次再从网络加载*/
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        /*网页打开进程*/
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //newProgress 1-100之间的整数。
                if (newProgress == 100) {
                    // /网页加载完成，关闭对话框。
                    closeDialog();
                } else {
                    //网页正在加载，打开progressDialog。
                    openDialog(newProgress);
                }
            }

            private void closeDialog() {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }

            }

            private void openDialog(int newProgress) {
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(web.this);
                    progressDialog.setTitle(getString(R.string.jiszhai));
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setProgress(newProgress);
                    progressDialog.show();
                } else {
                    progressDialog.setProgress(newProgress);
                }

            }
        });
        //Webview加载本地资源
        //webView.loadUrl("file:///android_asset/example.html");
        //Webview加载外部资源
        webView.loadUrl("http://www.baidu.com");
        //覆盖Webview默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在Webview中打开
        //覆盖后会自动产生历史访问记录。可以通过goBack()或goForward()来向前或向后访问。
        //WebViewClient帮助webview处理一些页控制和请求通知。


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true时在Webview中打开，是false时在系统浏览器或第三方浏览器中打开；
                //return super.shouldOverrideUrlLoading(view,url);
                view.loadUrl(url);
                return true;
            }
/*          页面开始、页面关闭、获取HTTP
            onPageFinished/onPageStarted/onReceivedHttpAuthRequest
*/
        });

    }

    //改写物理按键——返回逻辑。
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(this, webView.getUrl(), Toast.LENGTH_SHORT).show();
            if (webView.canGoBack()) {
                webView.goBack();//返回上一页。
                return true;
            } else {
                System.exit(0);//退出程序。}
            }
        }
        return super.onKeyDown(keyCode, event);

    }
}
