package com.example.vuongnotekotlin

import android.app.Application
import com.example.vuongnotekotlin.adddeletenote.MainModel

class App: Application() {
    private lateinit var mainModel: MainModel
    override fun onCreate() {
        super.onCreate()
        MainModel.init(this)
    }
}