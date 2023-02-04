package com.app.pokeapi.ui.activity.activityMainFlow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.pokeapi.databinding.ActivityMainBinding


class MainFlowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }

}