package id.co.buaja.news.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import id.co.buaja.news.R
import id.co.buaja.news.network.model.ArticlesItem
import kotlinx.android.synthetic.main.layout_list_news.view.*

class NewsAdapter(private val context: Context, private val item: List<ArticlesItem>, private val listener: (ArticlesItem) -> Unit)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_news, parent, false))

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(item[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("CheckResult")
        fun bindItem(articlesItem: ArticlesItem, listener: (ArticlesItem) -> Unit) {
            itemView.tvJudul.text = articlesItem.title
            itemView.tvTanggal.text = articlesItem.publishedAt
            Picasso.get()
                .load(articlesItem.urlToImage)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.noimage)
                .into(itemView.image)


            itemView.setOnClickListener {
                listener(articlesItem)
            }
        }
    }
}