package com.ihebchiha.hiltapp.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.ihebchiha.hiltapp.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        attachKeyboardListeners()
    }

    private val keyboardLayoutListener = OnGlobalLayoutListener {
        val heightDiff = rootLayout!!.rootView.height - rootLayout!!.height
        val contentViewTop = window.findViewById<View>(Window.ID_ANDROID_CONTENT).top
        val broadcastManager = LocalBroadcastManager.getInstance(this@BaseActivity)
        if (heightDiff <= contentViewTop) {
            onHideKeyboard()
            val intent = Intent("KeyboardWillHide")
            broadcastManager.sendBroadcast(intent)
        } else {
            val keyboardHeight = heightDiff - contentViewTop
            onShowKeyboard(keyboardHeight)
            val intent = Intent("KeyboardWillShow")
            intent.putExtra("KeyboardHeight", keyboardHeight)
            broadcastManager.sendBroadcast(intent)
        }
    }

    private var keyboardListenersAttached = false
    private var rootLayout: ViewGroup? = null

    protected open fun onShowKeyboard(keyboardHeight: Int) {}
    protected open fun onHideKeyboard() {}

    protected open fun attachKeyboardListeners() {
        if (keyboardListenersAttached) {
            return
        }
        rootLayout = findViewById<View>(R.id.content) as ViewGroup
        rootLayout!!.viewTreeObserver.addOnGlobalLayoutListener(keyboardLayoutListener)
        keyboardListenersAttached = true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (keyboardListenersAttached) {
            rootLayout!!.viewTreeObserver.removeGlobalOnLayoutListener(keyboardLayoutListener)
        }
    }

    /*private fun viewModels(clazz: KClass<ViewModel>, factoryProducer: (() -> ViewModelProvider.Factory)? = null): Lazy<ViewModel> {
        return ViewModelLazy(clazz, { viewModelStore }, factoryProducer ?: { defaultViewModelProviderFactory })
    }

    private fun initViewModel(): ViewModel {
        val v: ViewModel by viewModels(getViewModelClass(), getViewModelFactory())
        return v
    }

    protected val viewModel: ViewModel by lazy { initViewModel() }
    protected val binding: ViewBinding by lazy { DataBindingUtil.setContentView<ViewBinding>(this, getLayoutResId()) }

    protected fun getViewModelFactory(): (() -> ViewModelProvider.Factory)? = null
    protected abstract fun getViewModelClass(): KClass<ViewModel>
    protected abstract fun getLayoutResId(): Int*/

}
