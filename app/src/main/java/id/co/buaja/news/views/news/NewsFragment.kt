package id.co.buaja.news.views.news

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import id.co.buaja.news.R
import id.co.buaja.news.adapter.NewsAdapter
import id.co.buaja.news.network.model.ArticlesItem
import id.co.buaja.news.views.base.BaseFragment

class NewsFragment : BaseFragment(), NewsView {
    private lateinit var newsPresenter: NewsPresenter
    private val ARG_PARAM1 = "param1"
    private var category: String? = null
    private var listNews: MutableList<ArticlesItem> = mutableListOf()
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView

    override fun contentView(): Int {
        return R.layout.fragment_news
    }

    override fun onCreated(view: View) {
        arguments?.let {
            category = it.getString(ARG_PARAM1)
        }

        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh)
        recyclerView = view.findViewById(R.id.recyclerView)

        newsPresenter = NewsPresenter()
        onAttachView()
    }

    override fun onShowLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun onHideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onShowNews(articles: List<ArticlesItem>?) {
        listNews.clear()
        articles?.let {
            listNews.addAll(it)
        }
        newsAdapter.notifyDataSetChanged()
    }

    override fun onMessage(message: String?) {
        Log.d("Message", message)
    }

    override fun onError(error: String?) {
        Log.d("Error", error)
    }

    override fun onAttachView() {
        newsPresenter.onAttach(this)

        swipeRefreshLayout.post {
            loadData()
        }

        swipeRefreshLayout.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        newsPresenter.getCategory(category)

        newsAdapter = NewsAdapter(requireContext(), listNews) {

        }
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = newsAdapter
    }

    override fun onDetachView() {

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
