package com.burakkodaloglu.my_e_commerce_app.util.common

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ManagerDialog(private val context: Context) {

    private var alertDialog: AlertDialog? = null
    
    fun showAlertDialog(
        title: String? = null,
        message: String? = null,
        positiveButtonTitle: String? = null,
        positiveButtonAction: () -> Unit,
        negativeButtonTitle: String? = null,
        negativeButtonAction: (() -> Unit)? = null,
        customView: View? = null,
        isCancelable: Boolean = true
    ) {
        val alertDialogBuilder =
            MaterialAlertDialogBuilder(context, android.R.style.ThemeOverlay_Material_Dialog_Alert)
                .setTitle(title)
                .setMessage(message)
                .setView(customView)
                .setCancelable(isCancelable)


        alertDialogBuilder.setPositiveButton(positiveButtonTitle) { _, _ ->
            positiveButtonAction.invoke()
            alertDialog?.dismiss()
        }

        if (negativeButtonTitle != null && negativeButtonAction != null) {
            alertDialogBuilder.setNegativeButton(negativeButtonTitle) { _, _ ->
                negativeButtonAction.invoke()
                alertDialog?.dismiss()
            }
        }

        alertDialog = alertDialogBuilder.create()
        alertDialog?.show()
    }

    fun dismissAlertDialog() {
        alertDialog?.dismiss()
    }
}