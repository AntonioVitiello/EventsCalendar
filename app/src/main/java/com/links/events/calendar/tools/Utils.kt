package com.links.events.calendar.tools

import android.annotation.SuppressLint
import androidx.fragment.app.FragmentActivity
import com.links.events.calendar.view.CustomProgressDialog
import java.lang.ref.WeakReference

/**
 * Created by Antonio Vitiello
 */
@SuppressLint("ConstantLocale")
class Utils {

    companion object {
        fun showLoading(weakActivity: WeakReference<FragmentActivity>) {
            weakActivity.get()?.let { activity ->
                activity.closeKeyboard()
                CustomProgressDialog.show(activity)
            }
        }

        fun hideLoading(weakActivity: WeakReference<FragmentActivity>) {
            weakActivity.get()?.let { activity ->
                CustomProgressDialog.dismiss(activity)
            }
        }
    }

}