package com.example.newarchstudy

import android.app.Application
import com.example.newarchstudy.utils.Factory

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()


        Factory.setDataSource(this)

    }
}