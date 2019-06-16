package id.co.buaja.news.network.base

import id.co.buaja.news.network.ApiConfig
import id.co.buaja.news.network.ApiInterface

open class BaseConfig {
    fun config(): ApiInterface {
        return ApiConfig.config()
    }
}