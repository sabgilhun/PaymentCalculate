package com.example.socarpaymentcalculate.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<B : ViewDataBinding> constructor(
    private val layoutId: Int
) : AppCompatActivity() {

    protected lateinit var binding: B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
    }

    protected fun <VM : ViewModel> getViewModel(vmClass: Class<VM>): VM {
        return ViewModelProviders.of(this).get(vmClass)
    }

}