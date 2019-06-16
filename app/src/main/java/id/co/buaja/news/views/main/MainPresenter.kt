package id.co.buaja.news.views.main

import id.co.buaja.news.views.base.Presenter

class MainPresenter : Presenter<MainView> {
    private var mainView: MainView? = null

    override fun onAttach(view: MainView) {
        mainView = view
    }

    override fun onDetach() {
        mainView = null
    }

    fun showFragment() {
        mainView?.onShowFragment()
    }
}