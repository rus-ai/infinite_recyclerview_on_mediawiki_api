package ru.vladikadiroff.mediawikiprojects.ui.fragments

import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.vladikadiroff.mediawikiprojects.R
import ru.vladikadiroff.mediawikiprojects.databinding.FragmentWikiProjectsBinding
import ru.vladikadiroff.mediawikiprojects.presentation.keys.ProjectsViewState
import ru.vladikadiroff.mediawikiprojects.presentation.viewmodels.ProjectsViewModel
import ru.vladikadiroff.mediawikiprojects.ui.activities.WikiProjectsActivity
import ru.vladikadiroff.mediawikiprojects.ui.adapters.ProjectsAdapter
import ru.vladikadiroff.mediawikiprojects.utils.abstracts.BaseFragment

@ExperimentalCoroutinesApi
class ProjectsFragment : BaseFragment<FragmentWikiProjectsBinding, ProjectsViewModel>() {

    private val adapter = ProjectsAdapter()

    override var hasOptionMenu = true
    override var transitionPostponedAnimation = true
    override val viewModelClass = ProjectsViewModel::class.java
    override val viewBindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentWikiProjectsBinding = FragmentWikiProjectsBinding::inflate

    override fun initFragment() {
        initView()
        initViewState()
        initTransitionAnimation()
    }

    private fun initView() {
        adapter.setOnItemClickListener { model ->
            val action = ProjectsFragmentDirections.actionToDetailFragment(model.id, model.name)
            findNavController().navigate(action)
        }
        adapter.setOnLoadMoreListener { viewModel.loadNewItems() }
        adapter.setOnRetryLoadListener { viewModel.loadNewItems() }
        viewBinding.listView.adapter = adapter
        viewBinding.swipeRefresh.setOnRefreshListener { viewModel.refresh() }
    }

    private fun initViewState() {
        viewModel.obtainViewState.observe(this, Observer { state ->
            when (state) {
                is ProjectsViewState.Default -> showSplash()
                is ProjectsViewState.ShowList -> showData(state)
                is ProjectsViewState.Exception -> showException()
            }
        })
    }

    private fun initTransitionAnimation(){
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z,true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_fast).toLong()
        }
    }

    private fun showData(state: ProjectsViewState.ShowList) {
        adapter.submitData(state.list, state.isActiveOnLoadMoreListener)
        showSplash(false)
        hideException()
    }

    private fun showException() {
        showSplash(false)
        hideException(true)
    }

    private fun showSplash(show: Boolean = true) {
        setTitle()
        viewBinding.swipeRefresh.isRefreshing = false
        if (show) {
            viewBinding.splashContainer.visibility = View.VISIBLE
        } else {
            viewBinding.splashContainer.visibility = View.GONE
        }
    }

    private fun hideException(hide: Boolean = false) {
        if (!hide) {
            viewBinding.exceptionContainer.visibility = View.GONE
            viewBinding.listView.visibility = View.VISIBLE
        } else {
            viewBinding.exceptionContainer.visibility = View.VISIBLE
            viewBinding.listView.visibility = View.GONE
        }
    }

    private fun setTitle() {
        val toolbar = (activity as WikiProjectsActivity).supportActionBar
        if (viewModel.getSearchQuery().isNullOrEmpty())
            toolbar?.title = getString(R.string.wiki_projects_title)
        else toolbar?.title = viewModel.getSearchQuery()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_toolbar_menu, menu)
        val searchView = SearchView(
            (context as WikiProjectsActivity).supportActionBar?.themedContext ?: requireContext()
        )
        menu.findItem(R.id.search).actionView = searchView
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchNewItems(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) showSplash()
                else if (viewModel.getPreloadStatus()) viewModel.searchNewItems(newText)
                return false
            }
        })
    }

}