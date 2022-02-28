package com.ciandt.breweryees.utils

import android.text.TextUtils
import android.util.Patterns

class Email {
    companion object {
        fun validEmail(email: String): Boolean {
            return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}