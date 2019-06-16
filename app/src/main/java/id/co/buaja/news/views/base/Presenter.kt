package id.co.buaja.news.views.base

interface Presenter<T : View> {
    fun onAttach(view: T)
    fun onDetach()
}