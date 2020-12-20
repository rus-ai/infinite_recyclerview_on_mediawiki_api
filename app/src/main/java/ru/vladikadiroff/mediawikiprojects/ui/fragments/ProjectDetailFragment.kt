package ru.vladikadiroff.mediawikiprojects.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialSharedAxis
import ru.vladikadiroff.mediawikiprojects.R
import ru.vladikadiroff.mediawikiprojects.databinding.FragmentWikiProjectDetailBinding
import ru.vladikadiroff.mediawikiprojects.presentation.viewmodels.ProjectDetailViewModel
import ru.vladikadiroff.mediawikiprojects.utils.abstracts.BaseFragment

class ProjectDetailFragment :
    BaseFragment<FragmentWikiProjectDetailBinding, ProjectDetailViewModel>() {

    private val args: ProjectDetailFragmentArgs by navArgs()

    override val viewModelClass = ProjectDetailViewModel::class.java
    override val viewBindingInflater: (LayoutInflater, ViewGroup?, Boolean) ->
    FragmentWikiProjectDetailBinding = FragmentWikiProjectDetailBinding::inflate

    override fun initFragment() {
        initTransitionAnimation()
        viewBinding.infoId.text = getString(R.string.info_id, args.id)
        viewBinding.infoName.text = getString(R.string.info_name, args.name)
    }

    private fun initTransitionAnimation() {
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_fast).toLong()
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_fast).toLong()
        }
    }

}