package com.ihebchiha.hiltapp.base

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.reflect.KClass

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

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
