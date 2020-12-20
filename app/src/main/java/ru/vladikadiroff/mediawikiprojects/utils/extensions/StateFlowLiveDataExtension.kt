package ru.vladikadiroff.mediawikiprojects.utils.extensions

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow

fun <T: Any?> MutableLiveData<T>.default(initialValue: T) = apply { value = initialValue }
fun <T: Any?> MutableStateFlow<T>.default(initialValue: T) = apply { value = initialValue }

fun <T> MutableLiveData<T>.set(newValue: T) = apply { value = newValue }
fun <T> MutableStateFlow<T>.set(newValue: T) = apply { value = newValue }