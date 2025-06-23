package com.unlam.marvelkmm2

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform