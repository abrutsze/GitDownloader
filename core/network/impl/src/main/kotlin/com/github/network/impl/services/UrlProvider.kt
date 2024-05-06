package com.github.network.impl.services

interface UrlProvider {
    fun getProtocol(): String
    fun getUrl(): String
    fun getToken(): String
}