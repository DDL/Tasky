package com.ddoczi.tasky.core.util

val <T : Enum<T>> List<Enum<T>>.names: List<String>
    get() = map { it.name.lowercase().replaceFirstChar { it.titlecase() } }