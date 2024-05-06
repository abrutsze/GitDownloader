package com.github.user

import com.github.network.impl.services.UrlProvider
import org.koin.core.annotation.Single

@Single
class UrlProviderImpl(): UrlProvider {
    override fun getProtocol(): String  = BuildConfig.PROTOCOL
    override fun getUrl(): String = BuildConfig.BASE_URL
    override fun getToken(): String = BuildConfig.TOKEN
}
