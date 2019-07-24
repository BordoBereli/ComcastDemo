package com.kutluoglu.comcastdemo.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.kutluoglu.comcastdemo.BuildConfig
import com.kutluoglu.comcastdemo.R
import com.kutluoglu.comcastdemo.base.BaseActivity
import com.kutluoglu.comcastdemo.main.features.content.Contents
import com.kutluoglu.comcastdemo.utils.extensions.addFragment
import com.kutluoglu.comcastdemo.utils.extensions.setAppTitle
import com.kutluoglu.comcastdemo.utils.extensions.setupActionbar
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var navController: NavController
    private lateinit var appBarConfig: AppBarConfiguration
    private var isTablet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)
        isTablet = resources.getBoolean(R.bool.isTablet)

        setupNavigationEnvironment()

        if (isTablet) {
            val contentFragment = Contents()
            addFragment(contentFragment, R.id.content_holder)
        }

    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onNavigateUp() = navController.navigateUp()

    override fun onBackPressed() {
        super.onBackPressed()
        toolbar.setAppTitle(BuildConfig.AppName)
    }

    private fun setupNavigationEnvironment() {
        setupActionbar(toolbar)
        setupNavigation()
    }

    private fun setupNavigation() {
        if (!::navController.isInitialized) {
            navController = if (isTablet) {
                findNavController(R.id.tablet_nav_host_fragment)
            } else {
                findNavController(R.id.nav_host_fragment)
            }
        }

        // Tie actionbar/toolbar items to navController
        appBarConfig = AppBarConfiguration(navController.graph)

        // Top Level Destinations
        if (isTablet) {
            appBarConfig.topLevelDestinations.add(R.id.contentDetail)
        } else {
            appBarConfig.topLevelDestinations.add(R.id.contents)
        }

        // Tie all together
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfig)
    }
}
