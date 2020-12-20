package ru.vladikadiroff.mediawikiprojects.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vladikadiroff.mediawikiprojects.databinding.ItemFooterLoadingBinding

class FooterLoadingViewHolder(private val viewBinding: ItemFooterLoadingBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    companion object {
        fun create(parent: ViewGroup): FooterLoadingViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val viewBinding = ItemFooterLoadingBinding.inflate(layoutInflater, parent, false)
            return FooterLoadingViewHolder(viewBinding)
        }
    }

    fun bind() {
        viewBinding.footerProgressBar.isIndeterminate = true
    }

}