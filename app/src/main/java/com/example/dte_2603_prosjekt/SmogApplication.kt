package com.example.dte_2603_prosjekt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SmogApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Fra: https://medium.com/androiddevnotes/customize-your-android-timber-logger-setup-to-add-a-global-tag-and-a-method-name-to-the-logs-for-e7f23acd844f
        Timber.plant(object : Timber.DebugTree() {

            // Denne sørger for at aktivitetsnavnet skrives etter WFA_LOG
            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                super.log(priority, "SMOG_LOG_$tag", message, t)
            }

            // Denne sørger for at metodenavnet også skrives ut:
            override fun createStackElementTag(element: StackTraceElement): String {
                return String.format(
                    "%s:%s",
                    element.methodName,
                    super.createStackElementTag(element)
                )
            }
        })
    }

}