package com.ihebchiha.hiltapp.ui.view.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.ihebchiha.hiltapp.utils.sidemenu.MenuManager
import com.ihebchiha.hiltapp.R
import com.ihebchiha.hiltapp.base.BaseActivity
import com.ihebchiha.hiltapp.databinding.ActivityMainBinding
import com.ihebchiha.hiltapp.ui.view.adapters.SideMenuAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var sideMenuAdapter:SideMenuAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController


        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(navController.graph)
        binding.activityMainToolBar.setupWithNavController(navController, appBarConfiguration)

        setupActionBar()
        updateDrawer()
        initRecyclerMenu()
    }

    private fun initRecyclerMenu() {
        sideMenuAdapter = SideMenuAdapter(MenuManager.getSideMenuItems())
        sideMenuAdapter.menuItemClicked = {}
        menuRecyler.adapter = sideMenuAdapter
    }

    private fun setupActionBar() {
        setSupportActionBar(activity_main_toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


    private fun updateDrawer() {
        Timber.d("updateDrawer()")
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            activity_main_toolBar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.syncState()

        val drawerListener = object : DrawerLayout.SimpleDrawerListener() {

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)

                val drawerWidth = layout_main_drawercontainer.width
                nav_view.translationX = drawerWidth * (1 - slideOffset)
                layout_main_content.translationX = drawerWidth * slideOffset
            }
        }

        drawer_layout.apply {
            setScrimColor(ContextCompat.getColor(this@MainActivity, R.color.black40000000))
            addDrawerListener(drawerListener)
            addDrawerListener(toggle)
        }
    }
}
