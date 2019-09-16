package com.webaddicted.kotlinproject.view.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.ActivityWebviewBinding
import com.webaddicted.kotlinproject.view.base.BaseActivity

class WebViewActivity : BaseActivity() {

    private var url: String = "http://10.11.6.18:8080"
    private lateinit var mBinding: ActivityWebviewBinding

    companion object {
        val TAG = WebViewActivity::class.java.simpleName
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, WebViewActivity::class.java))
        }
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityWebviewBinding
        init()
        clickListener();
//        url = "https://www.journaldev.com"
    }

    override fun getLayout(): Int {
        return R.layout.activity_webview
    }

    private fun init() {
        mBinding.toolbar.imgBack.visibility = View.VISIBLE
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.webview_title)
        mBinding.webview.settings.setLoadsImagesAutomatically(true);
        mBinding.webview.settings.setUserAgentString("boxltyandroid");

        mBinding.webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mBinding.webview.settings.setJavaScriptEnabled(true)
        mBinding.webview.settings.setLoadWithOverviewMode(true)
        mBinding.webview.settings.setUseWideViewPort(true)
        mBinding.webview.setScrollbarFadingEnabled(true)
        mBinding.webview.setVerticalScrollBarEnabled(false)

        mBinding.webview.addJavascriptInterface(
            WebAppInterface(
                this
            ), "Android"
        )
        mBinding.webview.setWebViewClient(MyBrowser())
        val str =
            "<html>\n" +
                    "<head>\n" +
                    " <title>Express HTML</title>\n" +
                    " <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>\n" +
                    " <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css\">\n" +
                    " <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css\">\n" +
                    " <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js\"></script>\n" +
                    " <script>\n" +
                    " \n" +
                    " \n" +
                    " //alert(txt);\n" +
                    " </script>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    " <div style=\"margin:100px;\">\n" +
                    " <nav class=\"navbar navbar-inverse navbar-static-top\">\n" +
                    " <div class=\"container\">\n" +
                    " <a class=\"navbar-brand\" href=\"/\">Express HTML</a>\n" +
                    " <ul class=\"nav navbar-nav\">\n" +
                    " <li class=\"active\">\n" +
                    " <a href=\"/\">Home</a>\n" +
                    " </li>\n" +
                    " <li>\n" +
                    " <a href=\"/about\">About</a>\n" +
                    " </li>\n" +
                    " <li>\n" +
                    " <a href=\"/sitemap\">Sitemap</a>\n" +
                    " </li>\n" +
                    " </ul>\n" +
                    " </div>\n" +
                    "</nav>\n" +
                    " <div class=\"jumbotron\" style=\"padding:40px;\">\n" +
                    " <h1>Hello, world!</h1>\n" +
                    " <p>This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>\n" +
                    " <p>---------------------------------------------------------<br></p>\n" +
                    " <p id=\"agentId\"></p>\n" +
                    " <p>---------------------------------------------------------<br></p>\n" +
                    " <p id=\"responseP\"></p>\n" +
                    " <p><button id=\"btnClick\">Android click</button></p>\n" +
                    " </div>\n" +
                    " </div>\n" +
                    " <script>\n" +
                    " \$(document).ready(function(){\n" +
                    " var txt = \"\";\n" +
                    " txt += \"<p>Browser CodeName: \" + navigator.appCodeName + \"</p>\";\n" +
                    " txt += \"<p>Browser Name: \" + navigator.appName + \"</p>\";\n" +
                    " txt += \"<p>Browser Version: \" + navigator.appVersion + \"</p>\";\n" +
                    " txt += \"<p>Cookies Enabled: \" + navigator.cookieEnabled + \"</p>\";\n" +
                    " txt += \"<p>Browser Language: \" + navigator.language + \"</p>\";\n" +
                    " txt += \"<p>Browser Online: \" + navigator.onLine + \"</p>\";\n" +
                    " txt += \"<p>Platform: \" + navigator.platform + \"</p>\";\n" +
                    " txt += \"<p>User-agent header: \" + navigator.userAgent + \"</p>\";\n" +
                    " document.getElementById(\"agentId\").innerHTML=txt;\n" +
                    " \n" +
                    " \n" +
                    " \$(\"#btnClick\").bind(\"click\", function(){\n" +
                    " if(navigator.userAgent==\"boxltyios\"){\n" +
                    " document.getElementById(\"responseP\").innerHTML=\"IOS FOUND\";\n" +
                    " window.webkit.messageHandlers.boxlty.postMessage({param1:\"sourabh\"})\n" +
                    " }else if(navigator.userAgent==\"boxltyandroid\"){\n" +
                    " document.getElementById(\"responseP\").innerHTML=\"ANDROID FOUND\";\n" +
                    " window.Android.showToast(\"Hello\");\n" +
                    " \n" +
                    " } else{\n" +
                    " document.getElementById(\"responseP\").innerHTML=\"NORMAL BROWSER\";\n" +
                    " }\n" +
                    " }); \n" +
                    " \n" +
                    " })\n" +
                    " </script>\n" +
                    "</body>\n" +
                    "</html>"
        mBinding.webview.loadData(str, "text/html", "utf-8")
//        mBinding.webview.loadUrl(url)
    }

    private fun clickListener() {
        mBinding.toolbar.imgBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when (v?.id) {
            R.id.img_back -> onBackPressed()
        }
    }

    private inner class MyBrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            Log.d(TAG, url)
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            mBinding.progressBar.setVisibility(View.GONE)
            super.onPageFinished(view, url)
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
            mBinding.progressBar.setVisibility(View.VISIBLE)
            super.onPageStarted(view, url, favicon)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && this.mBinding.webview.canGoBack()) {
            this.mBinding.webview.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    /** Instantiate the interface and set the context  */
    class WebAppInterface(private val mContext: Context) {
        /** Show a toast from the web page  */
        @JavascriptInterface
        fun showToast(toast: String) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }
    }
}

