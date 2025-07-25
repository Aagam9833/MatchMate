package com.aagamshah.matchmate.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB : ViewBinding>(
    private val items: MutableList<T>,
    private val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB
) : RecyclerView.Adapter<BaseAdapter<T, VB>.BaseViewHolder>() {

    inner class BaseViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = bindingInflater(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bind(holder.binding, items[position], position)
    }

    override fun getItemCount(): Int = items.size

    protected abstract fun bind(binding: VB, item: T, position: Int)

    fun getItem(position: Int): T = items[position]
}
