package com.example.socarpaymentcalculate.view.base

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.socarpaymentcalculate.common.RxLifecycleHandler
import com.example.socarpaymentcalculate.viewmodel.ViewModelFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity<B : ViewDataBinding> constructor(
    private val layoutId: Int
) : AppCompatActivity() {

    protected lateinit var binding: B
        private set

    protected var progressBar: View? = null

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            statusBarColor = Color.WHITE
        }
    }

    protected fun <VM : ViewModel> getViewModel(vmClass: Class<VM>): VM {
        return ViewModelProviders.of(this, ViewModelFactory).get(vmClass)
    }

    protected fun Disposable.track() {
        compositeDisposable.add(this)
    }

    protected fun <T> Observable<T>.observe(o: (T) -> Unit) {
        RxLifecycleHandler(this@BaseActivity, this, o)
    }

    protected fun showToastMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    protected fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }

    protected inline fun bind(block: B.() -> Unit) {
        binding.block()
    }

}