package ru.vladikadiroff.mediawikiprojects.utils.abstracts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import ru.vladikadiroff.mediawikiprojects.utils.extensions.default
import ru.vladikadiroff.mediawikiprojects.utils.interfaces.ViewState

abstract class BaseViewModel<VS : ViewState>(defaultViewState: VS) : ViewModel() {

    private var viewModelJob = Job()
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + Job())

    protected val viewState = MutableLiveData<VS>().default(defaultViewState)
    val obtainViewState: LiveData<VS> get() = viewState

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}