package com.aagamshah.matchmate.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun Context.hasInternetConnection(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = cm.activeNetwork ?: return false
    val nc = cm.getNetworkCapabilities(network) ?: return false
    return nc.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            nc.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
}