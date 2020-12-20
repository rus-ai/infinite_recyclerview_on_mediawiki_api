package ru.vladikadiroff.mediawikiprojects.utils.abstracts

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class AsyncAdapter<T, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<T>
) : RecyclerView.Adapter<VH>() {

    private val differ = AsyncListDiffer(this, diffCallback)

    private var isActiveOnLoadMoreListener: Boolean = true
    private var onLoadMoreListener: (() -> Unit)? = null

    fun submitData(list: List<T>, isActiveLoadMoreListener: Boolean = true) {
        setActiveOnLoadMoreListener(isActiveLoadMoreListener)
        differ.submitList(list)
    }

    fun setOnLoadMoreListener(listener: () -> Unit) {
        onLoadMoreListener = listener
    }

    fun setActiveOnLoadMoreListener(status: Boolean = true) {
        isActiveOnLoadMoreListener = status
    }

    protected fun getItem(position: Int) = differ.currentList[position]

    override fun getItemCount() = differ.currentList.count()

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        val layoutPosition = holder.layoutPosition
        if (layoutPosition == itemCount - 1 && isActiveOnLoadMoreListener)
            onLoadMoreListener?.invoke()
    }

}