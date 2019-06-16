package id.co.buaja.news.views.detail

import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Picasso
import id.co.buaja.news.R
import id.co.buaja.news.network.model.ArticlesItem
import id.co.buaja.news.views.base.BaseActivity
import id.co.buaja.news.views.main.MainActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.startActivity

class DetailActivity : BaseActivity(), View.OnClickListener {
    private lateinit var news: ArticlesItem

    override fun contentView(): Int {
        return R.layout.activity_detail
    }

    override fun onCreated() {
        news = intent.getParcelableExtra("news")

        title = news.title

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        tvTitle.text = news.title
        tvSourceName.text = news.source?.name
        Picasso.get()
            .load(news.urlToImage)
            .placeholder(R.drawable.noimage)
            .error(R.drawable.noimage)
            .into(ivImage)
        tvDescription.text = news.description

        tvLihatSelengkap.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tvLihatSelengkap -> {
                startActivity<WebDetailActivity>("name" to news.source?.name,
                    "weblink" to news.url)
            }
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
        startActivity<MainActivity>()
    }
}
