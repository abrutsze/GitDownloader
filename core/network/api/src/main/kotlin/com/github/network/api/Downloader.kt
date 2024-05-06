package com.github.network.api

interface Downloader {
    fun downloadFile(branchName:String, fullName: String): Long
}