package com.links.events.calendar.view

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.links.events.calendar.R
import kotlinx.android.synthetic.main.dialog_custom_progress.*

/**
 * Created by Antonio Vitiello
 */
class CustomProgressDialog : DialogFragment() {

    companion object {
        const val TAG = "ProgressDialog"
        var showingLoading = false

        fun newInstance() = CustomProgressDialog()

        @JvmStatic
        fun show(activity: FragmentActivity) = synchronized(activity) {
            retrieveDialogFragment(activity)?.dismissAllowingStateLoss()
            newInstance().show(activity.supportFragmentManager, TAG)
            showingLoading = true
        }

        @JvmStatic
        fun dismiss(activity: FragmentActivity) = synchronized(activity) {
            showingLoading = false
            retrieveDialogFragment(activity)?.dismissAllowingStateLoss()
                ?: run { // wait to allow the fragment to be attached
                    Handler(Looper.getMainLooper()).postDelayed(
                        { if (!showingLoading) retrieveDialogFragment(activity)?.dismissAllowingStateLoss() },
                        300
                    )
                }
        }

        private fun retrieveDialogFragment(activity: FragmentActivity): DialogFragment? {
            return activity.supportFragmentManager.findFragmentByTag(TAG)
                ?.takeIf { it is DialogFragment }
                ?.let { it as DialogFragment }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.ProgressDialogTheme)
        isCancelable = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_custom_progress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.isClickable = true
        view.isFocusable = true
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        sxcProgress?.clearAnimation()
    }

}