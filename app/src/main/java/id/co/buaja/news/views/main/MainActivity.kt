package id.co.buaja.news.views.main

import com.alfianyusufabdullah.init
import id.co.buaja.news.R
import id.co.buaja.news.views.base.BaseActivity
import id.co.buaja.news.views.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {
    private lateinit var mainPresenter: MainPresenter

    override fun contentView(): Int {
        return R.layout.activity_main
    }

    override fun onCreated() {
        setSupportActionBar(toolBar)

        mainPresenter = MainPresenter()

        onAttachView()
    }

    override fun onShowFragment() {
        mainPage.init(mainTabLayout) {
            addPages("Business" , NewsFragment.newInstance("business"))
            addPages("Entertainment" , NewsFragment.newInstance("entertainment"))
            addPages("General" , NewsFragment.newInstance("general"))
            addPages("Health" , NewsFragment.newInstance("health"))
            addPages("Science" , NewsFragment.newInstance("science"))
            addPages("Sports" , NewsFragment.newInstance("sports"))
            addPages("Technology" , NewsFragment.newInstance("technology"))
        }
    }

    override fun onAttachView() {
        mainPresenter.onAttach(this)
        mainPresenter.showFragment()
    }

    override fun onDetachView() {
        mainPresenter.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        onDetachView()
    }
}
