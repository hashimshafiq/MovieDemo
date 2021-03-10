package com.hashimshafiq.moviedemo.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.hashimshafiq.moviedemo.MovieApplication
import com.hashimshafiq.moviedemo.di.components.ActivityComponent
import com.hashimshafiq.moviedemo.di.components.DaggerActivityComponent
import com.hashimshafiq.moviedemo.di.modules.ActivityModule
import com.hashimshafiq.moviedemo.utils.display.Toaster
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel : VM


    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()
    }

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, {
            it.data?.run { showMessage(this) }
        })
    }

    open fun showMessage(message: String) = Toaster.show(applicationContext, message)

    open fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    open fun goBack() = onBackPressed()

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        }
        else super.onBackPressed()
    }

    private fun buildActivityComponent() =
            DaggerActivityComponent
                    .builder()
                    .applicationComponent((application as MovieApplication).applicationComponent)
                    .activityModule(ActivityModule(this))
                    .build()




    protected abstract fun provideLayoutId(): View

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setupView(savedInstanceState: Bundle?)

}