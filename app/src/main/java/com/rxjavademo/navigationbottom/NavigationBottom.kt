package com.rxjavademo.navigationbottom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rxjavademo.R
import kotlinx.android.synthetic.main.activity_navigation_bottom.*

class NavigationBottom : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_bottom)

        button.setOnClickListener { bottomNavigationView.transform(fab) }
    }
}
