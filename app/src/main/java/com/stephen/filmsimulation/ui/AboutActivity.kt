package com.stephen.filmsimulation.ui

import android.os.Bundle
import com.stephen.commonhelper.base.BaseActivity
import com.stephen.commonhelper.utils.infoLog
import com.stephen.filmsimulation.databinding.ActivityAboutBinding

class AboutActivity : BaseActivity<ActivityAboutBinding>() {

    companion object {
        const val TAG = "AboutActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        infoLog("=========>onCreate<========", TAG)
    }

    override fun setBinding() = ActivityAboutBinding.inflate(layoutInflater)
}