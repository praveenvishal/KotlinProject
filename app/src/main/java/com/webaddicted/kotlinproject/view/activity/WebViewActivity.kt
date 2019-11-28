package com.webaddicted.kotlinproject.view.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.webaddicted.kotlinproject.R
import com.webaddicted.kotlinproject.databinding.ActivityWebviewBinding
import com.webaddicted.kotlinproject.global.common.Lg
import com.webaddicted.kotlinproject.global.common.MediaPickerHelper
import com.webaddicted.kotlinproject.global.common.showToast
import com.webaddicted.kotlinproject.global.common.visible
import com.webaddicted.kotlinproject.global.misc.WebChromeClientTest
import com.webaddicted.kotlinproject.view.base.BaseActivity


class WebViewActivity : BaseActivity() {
    private var url: String = "http://www.google.com"
    private lateinit var mBinding: ActivityWebviewBinding
    val INPUT_FILE_REQUEST_CODE = 1
    var mUploadMessage: ValueCallback<Array<Uri>>? = null
    var mCameraPhotoPath: String = ""

    companion object {
        val TAG = WebViewActivity::class.java.simpleName
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, WebViewActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_webview
    }

    override fun isNetworkConnected(isInternetConnected: Boolean) {
        showInternetSnackbar(isInternetConnected, mBinding.txtNoInternet)
    }

    override fun initUI(binding: ViewDataBinding) {
        mBinding = binding as ActivityWebviewBinding
        init()
        clickListener();
        checkStoragePermission()
        normalWebView(mBinding.webview)
    }
    private fun init() {
        mBinding.toolbar.imgBack.visible()
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.webview_title)
    }
    private fun clickListener() {
        mBinding.toolbar.imgBack.setOnClickListener(this)
        mBinding.btnNormalWebview.setOnClickListener(this)
        mBinding.btnWebClick.setOnClickListener(this)
        mBinding.btnWebFileChoose.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        super.onClick(v)
        when (v.id) {
            R.id.img_back -> onBackPressed()
            R.id.btn_normal_webview -> normalWebView(mBinding.webview)
            R.id.btn_web_click -> webInterface(mBinding.webview)
            R.id.btn_web_file_choose -> chooseImageWebViewUrl(
                this,
                mBinding.webview,
                "https://en.imgbb.com/"
            )
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && this.mBinding.webview.canGoBack()) {
            this.mBinding.webview.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun normalWebView(webView: WebView) {
        webView.settings.setJavaScriptEnabled(true)
        webView.settings.setLoadWithOverviewMode(true)
        webView.settings.setUseWideViewPort(true)
        webView.setScrollbarFadingEnabled(true)
        webView.setVerticalScrollBarEnabled(false)
        webView.setWebViewClient(myTestBrowser())
        try {
            webView.loadUrl(url)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun webInterface(webView: WebView) {
        mBinding.toolbar.imgBack.visibility = View.VISIBLE
        mBinding.toolbar.txtToolbarTitle.text = resources.getString(R.string.webview_title)
        webView.settings.setLoadsImagesAutomatically(true);
        webView.settings.setUserAgentString("android");
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.settings.setJavaScriptEnabled(true)
        webView.settings.setLoadWithOverviewMode(true)
        webView.settings.setUseWideViewPort(true)
        webView.setScrollbarFadingEnabled(true)
        webView.setVerticalScrollBarEnabled(false)
        webView.addJavascriptInterface(
            WebAppInterface(
                this
            ), "Android"
        )
        webView.setWebViewClient(myTestBrowser())
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
                    " if(navigator.userAgent==\"testios\"){\n" +
                    " document.getElementById(\"responseP\").innerHTML=\"IOS FOUND\";\n" +
                    " window.webkit.messageHandlers.android.postMessage({param1:\"sourabh\"})\n" +
                    " }else if(navigator.userAgent==\"android\"){\n" +
                    " document.getElementById(\"responseP\").innerHTML=\"ANDROID FOUND\";\n" +
                    " window.Android.profileVerification(\"Hello\");\n" +
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
        webView.loadData(str, "text/html", "utf-8")
    }

    private inner class myTestBrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            mBinding.progressBar.setVisibility(View.VISIBLE)
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            mBinding.progressBar.setVisibility(View.GONE)
            super.onPageFinished(view, url)
        }
    }

    /** Instantiate the interface and set the context  */
    class WebAppInterface(private val mContext: Context) {
        /** Show a toast from the web page  */
        @JavascriptInterface
        fun profileVerification(toast: String) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * show response url on webview
     *
     * @param webView    view
     * @param contentUrl webview url
     */
    fun chooseImageWebViewUrl(activity: Activity?, webView: WebView, contentUrl: String?) {
        try {
            val cookieManager = CookieManager.getInstance()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                cookieManager.removeAllCookies(ValueCallback<Boolean> { aBoolean ->
                    // a callback which is executed when the cookies have been removed
//                    Log.d(FragmentActivity.TAG, "Cookie removed: " + aBoolean!!)
                })
            } else
                cookieManager.removeAllCookie()
            webView.settings.javaScriptEnabled = true
            webView.settings.loadWithOverviewMode = true
            webView.settings.useWideViewPort = true
            webView.isScrollbarFadingEnabled = true
            webView.isVerticalScrollBarEnabled = false
            webView.settings.userAgentString = "android"
            webView.getSettings().setAppCacheEnabled(false)
            webView.getSettings().domStorageEnabled = true
            webView.clearCache(true)
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.getSettings().setSupportZoom(true);
            webView.getSettings().allowFileAccess = (true);
            webView.getSettings().allowContentAccess = (true);
            if (Build.VERSION.SDK_INT >= 19) {
                webView.setLayerType(View.LAYER_TYPE_HARDWARE, null)
            } else if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT < 19) {
                webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            }
//            webView.getSettings().Access = (true);
            webView.webChromeClient = WebChromeClientTest(activity)
            webView.webViewClient = myTestBrowser()
            webView.loadUrl(contentUrl)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != INPUT_FILE_REQUEST_CODE || mUploadMessage == null) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
        try {
            if (resultCode == 0) {
                Lg.d(TAG, "when user cancel then reset web chrome client")
                mBinding.webview?.webChromeClient = WebChromeClientTest(this)
                mUploadMessage?.onReceiveValue(arrayOf(Uri.parse("")))
                return
            }
            if (data?.data != null || data?.clipData != null) {
                val files = MediaPickerHelper().getData(this, data)
                val arrayLists = Array(files.size, { i -> Uri.fromFile(files[i]) })
                showToast("selected image size - " + files.size)
                mUploadMessage?.onReceiveValue(arrayLists)
            } else {
                mUploadMessage?.onReceiveValue(arrayOf(Uri.parse(mCameraPhotoPath)))
                showToast("captured image - " + mCameraPhotoPath)
            }
        } catch (e: Exception) {
            Log.e("Error!", "Error while opening image file" + e.localizedMessage)
            mUploadMessage?.onReceiveValue(arrayOf(Uri.parse("")))
            mUploadMessage = null
        }
        mUploadMessage = null
    }
}

