package com.ruben.movieapplication.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ruben.domain.model.SearchResultRecord
import com.ruben.movieapplication.databinding.SearchRowBinding

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class ResultAdapter(private val searchResults: SearchResultRecord): RecyclerView.Adapter<ResultAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return searchResults.searchResults.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTv.text = searchResults.searchResults[position].title
        holder.nameTv.setOnClickListener {
            //get id of element
        }
    }

    class ViewHolder(itemView: SearchRowBinding): RecyclerView.ViewHolder(itemView.root) {
        val nameTv: AppCompatTextView = itemView.nameTv
    }

}