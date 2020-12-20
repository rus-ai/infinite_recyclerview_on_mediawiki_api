package ru.vladikadiroff.mediawikiprojects.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vladikadiroff.mediawikiprojects.databinding.ItemWikiProjectBinding
import ru.vladikadiroff.mediawikiprojects.presentation.models.ProjectModel

class WikiProjectViewHolder(private val viewBinding: ItemWikiProjectBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    companion object {
        fun create(parent: ViewGroup): WikiProjectViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemWikiProjectBinding.inflate(layoutInflater, parent, false)
            return WikiProjectViewHolder(binding)
        }
    }

    fun bind(model: ProjectModel, onItemClickListener: ((ProjectModel) -> Unit)?) {
        with(viewBinding) {
            itemTextTitle.text = model.id
            itemTextSummary.text = model.name
            itemContainer.setOnClickListener { onItemClickListener?.invoke(model) }
        }
    }

}