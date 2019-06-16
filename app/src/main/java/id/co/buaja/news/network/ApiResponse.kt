package id.co.buaja.news.network

import com.google.gson.annotations.SerializedName
import id.co.buaja.news.network.model.ArticlesItem

data class ApiResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
)