package me.ray.justtest

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

/**
 * Description: 主页
 * Author : ray
 */
class WebActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webView = findViewById(R.id.webView)
        setSettings()

        var url = intent.getStringExtra("URL")

        if(!url.contains("https://")&&!url.contains("http://")){
            url = "https://${url}"
        }

        webView.loadUrl(url)

    }

    private fun setSettings() {
        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
        settings.setSupportZoom(true)
        settings.setAppCacheEnabled(true)
        settings.setGeolocationEnabled(true)
        settings.setAppCacheMaxSize(Long.MAX_VALUE)
        settings.pluginState = WebSettings.PluginState.ON_DEMAND
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        settings.setSupportMultipleWindows(false)
        settings.defaultTextEncodingName = "UTF-8" //设置默认为utf-8
        //        settings.setUserAgent(AbHttpAO.getUserAgent());
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.databaseEnabled = true
        settings.allowContentAccess = true
        settings.loadsImagesAutomatically = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.allowFileAccess = true
        settings.allowFileAccessFromFileURLs = true
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true) // 允许接受 Cookie
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.getInstance().sync()
        } else {
            cookieManager.setAcceptThirdPartyCookies(webView, true)
            cookieManager.flush()
        }

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url != null) {
                    view.loadUrl(url)
                }
                return true
            }

            override fun onReceivedError(webView: WebView, i: Int, s: String, s1: String) {
                super.onReceivedError(webView, i, s, s1)
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
            }
        }


        //设置响应js 的Alert()函数


        //设置响应js 的Alert()函数
        webView.setWebChromeClient(object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView,
                url: String,
                message: String,
                result: JsResult
            ): Boolean {
                val b = AlertDialog.Builder(this@WebActivity)
                b.setTitle("提示")
                b.setMessage(message)
                b.setPositiveButton(
                    android.R.string.ok
                ) { dialog, which -> result.confirm() }
                b.setCancelable(false)
                b.create().show()
                return true
            }

            //设置响应js 的Confirm()函数
            override fun onJsConfirm(
                view: WebView,
                url: String,
                message: String,
                result: JsResult
            ): Boolean {
                val b = AlertDialog.Builder(this@WebActivity)
                b.setTitle("Confirm")
                b.setMessage(message)
                b.setPositiveButton(
                    android.R.string.ok
                ) { dialog, which -> result.confirm() }
                b.setNegativeButton(
                    android.R.string.cancel
                ) { dialog, which -> result.cancel() }
                b.create().show()
                return true
            }

            override fun onShowFileChooser(
                webView: WebView,
                filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                return true
            }


        })
    }

    companion object {

        @JvmStatic
        fun start(context: Context, url: String) {
            context.startActivity(Intent(context, WebActivity::class.java).apply {
                this.putExtra("URL", url)
            })
        }
    }


}