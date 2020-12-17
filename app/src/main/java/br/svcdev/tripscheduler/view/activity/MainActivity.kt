package br.svcdev.tripscheduler.view.activity

import android.os.Bundle
import br.svcdev.tripscheduler.App
import br.svcdev.tripscheduler.R
import br.svcdev.tripscheduler.common.interfaces.IBackPressedListener
import br.svcdev.tripscheduler.presenter.MainPresenter
import br.svcdev.tripscheduler.view.interfaces.IMainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), IMainView {

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = SupportAppNavigator(this, supportFragmentManager, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onStop() {
        super.onStop()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment is IBackPressedListener && fragment.onBackPressed()) {
                return
            }
        }
        presenter.onBackPressed()
    }

}