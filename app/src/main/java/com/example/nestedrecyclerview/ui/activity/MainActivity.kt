package com.example.nestedrecyclerview.ui.activity

import com.example.nestedrecyclerview.R
import com.example.nestedrecyclerview.databinding.ActivityMainBinding
import com.example.nestedrecyclerview.ui.base.BaseActivity
import com.example.nestedrecyclerview.ui.fragment.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val homeFragment = HomeFragment()

    override fun setUp() {
        setDefaultMainFragment()
    }

    private fun setDefaultMainFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.root_fragment, homeFragment)
        transaction.commit()
    }

    override fun bindingInflater() = ActivityMainBinding.inflate(layoutInflater)
}