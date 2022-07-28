package com.example.nestedrecyclerview.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private lateinit var _binding: VB
    abstract fun bindingInflater(): VB
    protected val binding get() = _binding

    abstract fun setUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
        _binding = bindingInflater()
        setContentView(_binding.root)
    }
}