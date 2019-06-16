package id.co.buaja.news.views.news

import id.co.buaja.news.network.base.BaseConfig
import id.co.buaja.news.views.base.Presenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsPresenter : Presenter<NewsView>, BaseConfig() {
    private var newsView: NewsView? = null

    override fun onAttach(view: NewsView) {
        newsView = view
    }

    override fun onDetach() {
        newsView = null
    }

    fun getCategory(category: String?) {
        newsView?.onShowLoading()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val call = config().getNews(category)
                val response = call.await()

                when(response.code()) {
                    200 -> {
                        newsView?.onShowNews(response.body()?.articles)
                    }

                    else -> {
                        newsView?.onMessage(response.message())
                    }
                }
                newsView?.onHideLoading()
            } catch (e: Exception) {
                newsView?.onError(e.message)
                newsView?.onHideLoading()
            }
        }
    }
}