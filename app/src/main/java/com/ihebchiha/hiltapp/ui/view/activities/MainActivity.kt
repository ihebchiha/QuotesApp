package com.ihebchiha.hiltapp.ui.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.ihebchiha.hiltapp.R
import com.ihebchiha.hiltapp.base.BaseActivity
import com.ihebchiha.hiltapp.databinding.ActivityMainBinding
import com.ihebchiha.hiltapp.ui.view.activities.login.ui.login.LoginActivity
import com.ihebchiha.hiltapp.ui.view.adapters.SideMenuAdapter
import com.ihebchiha.hiltapp.utils.sidemenu.MenuManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    lateinit var fragment: Fragment

    private val QUOTE_OF_THE_DAY = 0
    private val ALL_QUOTES = 1
    private val FAVORITES = 2
    private val PROFILE = 3
    private val LOGOUT = 4

    private lateinit var sideMenuAdapter: SideMenuAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController: NavController = navHostFragment.navController


//        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(navController.graph)
//        binding.activityMainToolBar.setupWithNavController(navController, appBarConfiguration)

        //setupActionBar()
        //Setup BottomNavigation listener
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        updateDrawer()
        initRecyclerMenu()
    }


    private fun initRecyclerMenu() {
        sideMenuAdapter = SideMenuAdapter(MenuManager.getSideMenuItems())
        sideMenuAdapter.menuItemClicked = { position -> handleNavigationInMenu(position) }
        menuRecyler.adapter = sideMenuAdapter
    }

    private fun setupActionBar() {
        // setSupportActionBar(activity_main_toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


    private fun updateDrawer() {
        Timber.d("updateDrawer()")
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            null,
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragmentId: Int  = 0
        when (item.itemId) {
            R.id.item_qotd -> {
                fragmentId = R.id.qotdFragment
            }
            R.id.item_all_quote -> {
                fragmentId = R.id.quotesFragment
            }
            R.id.item_fav -> {
                fragmentId = R.id.favoritesFragment
            }
            R.id.item_profile -> {
                fragmentId = R.id.profileFragment
            }
        }
        findNavController(R.id.nav_host_fragment).navigate(fragmentId)
        return true
    }

    private fun handleNavigationInMenu(position: Int) {
        when (position) {
            QUOTE_OF_THE_DAY -> {
            }
            ALL_QUOTES -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.quotesFragment)
            }
            FAVORITES -> {
            }
            PROFILE -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.profileFragment)
            }
            LOGOUT -> {
                firebaseAuth.currentUser.apply {
                    firebaseAuth.signOut()
                    startActivity(Intent(applicationContext, LoginActivity::class.java))
                }
            }
        }

    }

}
