package com.example.newsaggregator.model

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val connectivityModule = module {
    single { provideConnectivityModule(androidApplication()) }
}

fun provideConnectivityModule(context: Context) = ConnectivityModule(context)

class ConnectivityModule(context: Context) {
    private val connectivityManager by lazy { context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }

    public val isOnline: Boolean
        get() = isWifiAvailable || isCellularAvailable

    private var isWifiAvailable = false

    private var isCellularAvailable = false

    private val wifiCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            isWifiAvailable = true
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            isWifiAvailable = false
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            super.onLosing(network, maxMsToLive)
            isWifiAvailable = false
        }

        override fun onUnavailable() {
            super.onUnavailable()
            isWifiAvailable = false

        }
    }

    private val cellularCallback = object : ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            isCellularAvailable = true
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            isCellularAvailable = false
        }

        override fun onLosing(network: Network, maxMsToLive: Int) {
            super.onLosing(network, maxMsToLive)
            isCellularAvailable = false
        }

        override fun onUnavailable() {
            super.onUnavailable()
            isCellularAvailable = false
        }
    }

    init {
        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build(),
            wifiCallback
        )

        connectivityManager.registerNetworkCallback(
            NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build(),
            cellularCallback
        )
    }
}