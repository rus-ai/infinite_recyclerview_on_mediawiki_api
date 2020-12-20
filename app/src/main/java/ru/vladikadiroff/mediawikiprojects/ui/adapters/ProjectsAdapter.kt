package ru.vladikadiroff.mediawikiprojects.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.vladikadiroff.mediawikiprojects.presentation.models.ProjectModel
import ru.vladikadiroff.mediawikiprojects.ui.adapters.viewholders.FooterExceptionViewHolder
import ru.vladikadiroff.mediawikiprojects.ui.adapters.viewholders.FooterLoadingViewHolder
import ru.vladikadiroff.mediawikiprojects.ui.adapters.viewholders.WikiProjectViewHolder
import ru.vladikadiroff.mediawikiprojects.utils.abstracts.AsyncAdapter
import ru.vladikadiroff.mediawikiprojects.utils.interfaces.ProjectModels

class ProjectsAdapter :
    AsyncAdapter<ProjectModels, RecyclerView.ViewHolder>(diffUtilCallback) {

    private var onRetryLoadListener: (() -> Unit)? = null
    private var onItemClickListener: ((ProjectModel) -> Unit)? = null

    fun setOnRetryLoadListener(listener: () -> Unit) {
        onRetryLoadListener = listener
    }

    fun setOnItemClickListener(listener: (ProjectModel) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).VIEW_TYPE.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ProjectModels.ViewTypes.MODEL.type -> WikiProjectViewHolder.create(parent)
            ProjectModels.ViewTypes.FOOTER_LOADING.type -> FooterLoadingViewHolder.create(parent)
            ProjectModels.ViewTypes.FOOTER_EXCEPTION.type -> FooterExceptionViewHolder.create(parent)
            else -> throw IllegalArgumentException("ViewHolder with type $viewType not found")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WikiProjectViewHolder -> holder.bind(
                getItem(position) as ProjectModel,
                onItemClickListener
            )
            is FooterLoadingViewHolder -> holder.bind()
            is FooterExceptionViewHolder -> holder.bind(onRetryLoadListener)
        }
    }

    companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<ProjectModels>() {
            override fun areItemsTheSame(oldItem: ProjectModels, newItem: ProjectModels): Boolean {
                if (oldItem is ProjectModel && newItem is ProjectModel)
                    return oldItem.id == newItem.id
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ProjectModels,
                newItem: ProjectModels
            ): Boolean {
                if (oldItem is ProjectModel && newItem is ProjectModel)
                    return oldItem.name == newItem.name
                return true
            }
        }
    }

}