package id.co.buaja.news.untils

import java.text.SimpleDateFormat
import java.util.*

fun String.formatTanggal(): String? {
    val locale = Locale("id")
    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", locale)
    val output = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm", locale)
    val date = input.parse(this)
    return output.format(date)
}