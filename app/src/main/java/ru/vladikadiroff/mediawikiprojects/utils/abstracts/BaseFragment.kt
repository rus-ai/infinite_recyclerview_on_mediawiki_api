package ru.vladikadiroff.mediawikiprojects.utils.abstracts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: VM
    protected abstract val viewModelClass: Class<VM>

    private var _viewBinding: ViewBinding? = null
    protected abstract val viewBindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val viewBinding: VB
        get() = _viewBinding as VB

    open protected var hasOptionMenu = false
    protected open var transitionPostponedAnimation = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(hasOptionMenu)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = viewBindingInflater(inflater, container, false)
        return requireNotNull(_viewBinding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (transitionPostponedAnimation) onPostponedEnterTransition()
        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass)
        initFragment()
    }

    private fun onPostponedEnterTransition() {
        postponeEnterTransition()
        view?.doOnPreDraw { startPostponedEnterTransition() }
    }

    protected open fun initFragment() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

}