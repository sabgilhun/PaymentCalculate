package com.example.socarpaymentcalculate.view.base

import android.os.Bundle
import android.view.View
import android.view.WindowManager
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

    protected val binding: B by lazy {
        DataBindingUtil.setContentView<B>(this, layoutId)
    }

    protected var progressBar: View? = null

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

    }

    protected fun <VM : ViewModel> getViewModel(vmClass: Class<VM>): VM {
        return ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(vmClass)
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

    protected fun toggleProgressBar(isVisible: Boolean) {
        bind {
            if (isVisible) {
                root.alpha = 0.6f
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
                progressBar?.visibility = View.VISIBLE
            } else {
                root.alpha = 1.0f
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                progressBar?.visibility = View.GONE
            }
        }
    }

    protected inline fun bind(block: B.() -> Unit) {
        binding.block()
    }

}