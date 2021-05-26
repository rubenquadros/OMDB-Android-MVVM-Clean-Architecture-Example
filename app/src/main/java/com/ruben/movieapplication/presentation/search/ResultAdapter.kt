package com.ruben.movieapplication.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ruben.domain.model.search.SearchResultRecord
import com.ruben.movieapplication.base.BaseAdapter
import com.ruben.movieapplication.base.ItemClickListener
import com.ruben.movieapplication.databinding.SearchRowBinding

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class ResultAdapter: BaseAdapter<SearchResultRecord, ItemClickListener, ResultAdapter.ViewHolder>() {

    private var items: SearchResultRecord? = null
    private  var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return items?.searchResults?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTv.text = items?.searchResults?.get(position)?.title ?: ""
        holder.nameTv.setOnClickListener {
            //get id of element
            listener?.onItemClick(items?.searchResults?.get(position)?.id ?: "")
        }
    }

    override fun setItems(items: SearchResultRecord) {
        this.items = items
    }

    override fun setListener(listener: ItemClickListener) {
        this.listener = listener
    }

    class ViewHolder(itemView: SearchRowBinding): RecyclerView.ViewHolder(itemView.root) {
        val nameTv: AppCompatTextView = itemView.nameTv
    }

}