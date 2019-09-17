package com.webaddicted.kotlinproject.global.common

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.android.boxlty.view.interfaces.AlertDialogListener
import com.android.boxlty.view.interfaces.AlertRetryDialogListener
import com.webaddicted.kotlinproject.R


class DialogUtil {
    companion object {
        private val TAG = DialogUtil::class.java.simpleName

        /**
         * createMessageAlert dialog
         *
         * @param title               return dialog title string value
         * @param messgae             return dialog messge
         * @param btnOk               return button name event
         * @param btnCancel           return button name event
         * @param btnRetry            return button click event
         * @param alertDialogListener click event listener
         */
        fun alertFunction(
            context: Context?,
            title: String,
            messgae: String?,
            btnOk: String,
            btnCancel: String,
            btnRetry: String,
            alertDialogListener: AlertRetryDialogListener
        ): AlertDialog {
            var dialogAnimation = R.style.DialogSlideUpAnimation
            val builder = AlertDialog.Builder(context, R.style.AlertDialogStyle)
            builder.setCancelable(false)
            builder.setTitle(title)
            builder.setMessage(messgae)
            builder.setPositiveButton(btnOk) { dialog, which -> alertDialogListener.okClick() }
            builder.setNegativeButton(btnCancel) { dialog, which -> alertDialogListener.cancelClick() }
            builder.setNeutralButton(btnRetry) { dialogInterface, i -> alertDialogListener.okRetry() }
            val dialogs = builder.create()
            dialogs.getWindow()!!.getAttributes().windowAnimations = dialogAnimation
            dialogs.show()
            return dialogs
        }

        fun alertFunction(context: Context?, title: String, messgae: String, btnOk: String, btnCancel: String, alertDialogListener: AlertDialogListener):AlertDialog {
            var dialogAnimation = R.style.DialogSlideUpAnimation
            val builder = AlertDialog.Builder(context, R.style.AlertDialogStyle)
            builder.setCancelable(false)
            builder.setTitle(title)
            builder.setMessage(messgae)
            builder.setPositiveButton(btnOk) { dialog, which -> alertDialogListener.okClick() }
            builder.setNegativeButton(btnCancel) { dialog, which -> alertDialogListener.cancelClick() }
            val dialogs = builder.create()
            dialogs.getWindow()!!.getAttributes().windowAnimations = dialogAnimation
            dialogs.show()
            return dialogs
        }
        fun createMessageAlert(context: Context?, title: String, messgae: String, btnOk: String,btnListener: DialogInterface.OnClickListener):AlertDialog {
            var dialogAnimation = R.style.DialogSlideUpAnimation
            val builder = AlertDialog.Builder(context, R.style.AlertDialogStyle)
            builder.setCancelable(false)
            builder.setTitle(title)
            builder.setMessage(messgae)
            builder.setPositiveButton(btnOk, btnListener)
            val dialogs = builder.create()
            dialogs.getWindow()!!.getAttributes().windowAnimations = dialogAnimation
            dialogs.show()
            return dialogs
        }
    }
}
