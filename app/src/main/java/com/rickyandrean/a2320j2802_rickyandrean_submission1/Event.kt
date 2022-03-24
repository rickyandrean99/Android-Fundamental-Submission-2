package com.rickyandrean.a2320j2802_rickyandrean_submission1

open class Event<out T>(private val content: T) {
    @Suppress("MemberVisibilityCanBePrivate")
    var handled = false
        private set

    fun handler(): T? {
        return if (handled) {
            null
        } else {
            handled = true
            content
        }
    }
}