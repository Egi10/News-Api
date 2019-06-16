package id.co.buaja.news.views.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.KeyEvent
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import id.co.buaja.news.R
import id.co.buaja.news.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_web_detail.*

class WebDetailActivity : BaseActivity() {
    override fun contentView(): Int {
        return R.layout.activity_web_detail
    }

    override fun onCreated() {
        val intent = intent
        val name = intent.getStringExtra("name")
        val link = intent.getStringExtra("weblink")
        title = name

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        swipeRefresh.post {
            loadData(link)
        }

        swipeRefresh.setOnRefreshListener {
            loadData(link)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun loadData(link: String) {
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = MyBrowser()
        webview.loadUrl(link)
    }

    private inner class MyBrowser : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            swipeRefresh.isRefreshing = true
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            swipeRefresh.isRefreshing = false
        }
    }

    // Back
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBack()
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                onBack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onBack() {
        onBackPressed()
    }
}
