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

        inline fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
            return if (p1 != null && p2 != null) block(p1, p2) else null
        }
    }

}