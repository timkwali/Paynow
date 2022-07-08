package com.timkwali.paynow.common.util

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.timkwali.paynow.R

object Utils {

    fun showDialog(
        activity: Activity,
        title: String? = activity.getString(R.string.app_name),
        message: String,
        callback: () -> Unit = {}
    ) {
        try {
            var builder: androidx.appcompat.app.AlertDialog.Builder? = null
            builder =
                if (activity.parent != null) androidx.appcompat.app.AlertDialog.Builder(activity.parent) else androidx.appcompat.app.AlertDialog.Builder(
                    activity
                )
            val inflater = activity.layoutInflater
            val view: View = inflater.inflate(R.layout.custom_dialog, null)
            builder.setView(view)
            val titleTv = view.findViewById<TextView>(R.id.yesNoDialog_title_tv)
            val messageTv = view.findViewById<TextView>(R.id.yesNoDialog_message_tv)
            val yesTv = view.findViewById<TextView>(R.id.yesNoDialog_yes_tv)
            titleTv.text = title
            messageTv.text = message

            val dialog = builder.create()
            dialog.setCancelable(false)
            dialog.show()

            yesTv.setOnClickListener {
                dialog.dismiss()
                callback()
            }
        } catch (ex: Exception) {
            Log.e(TAG, ex.toString())
        }
    }
}