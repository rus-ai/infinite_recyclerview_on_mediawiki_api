package ru.vladikadiroff.mediawikiprojects.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.vladikadiroff.mediawikiprojects.databinding.ItemFooterExceptionBinding

class FooterExceptionViewHolder(private val viewBinding: ItemFooterExceptionBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    companion object {
        fun create(parent: ViewGroup): FooterExceptionViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val viewBinding = ItemFooterExceptionBinding.inflate(layoutInflater, parent, false)
            return FooterExceptionViewHolder(viewBinding)
        }
    }

    fun bind(onRetryLoadListener: (() -> Unit)?) {
        viewBinding.container.setOnClickListener { onRetryLoadListener?.invoke() }
    }

}