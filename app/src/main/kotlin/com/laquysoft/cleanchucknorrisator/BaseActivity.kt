package com.laquysoft.cleanchucknorrisator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.laquysoft.cleanchucknorrisator.framework.extension.inTransaction
import kotlinx.android.synthetic.main.toolbar.*

abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
    }

    fun enableHomeUpButton() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })
    }
    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
            savedInstanceState ?: supportFragmentManager.inTransaction { add(R.id.fragmentContainer, fragment()) }

    abstract fun fragment(): BaseFragment
}
