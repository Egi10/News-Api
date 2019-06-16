package id.co.buaja.news.views.news

import id.co.buaja.news.network.model.ArticlesItem
import id.co.buaja.news.views.base.View

interface NewsView : View {
    fun onShowLoading()
    fun onHideLoading()
    fun onShowNews(articles: List<ArticlesItem>?)
    fun onMessage(message: String?)
    fun onError(error: String?)
}