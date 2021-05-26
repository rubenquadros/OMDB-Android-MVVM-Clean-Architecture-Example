package com.ruben.movieapplication.base

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ruben.quadros on 26/05/21.
 **/
abstract class BaseAdapter<I: Any, L: Any, VH: RecyclerView.ViewHolder>: RecyclerView.Adapter<VH>() {

    abstract fun setItems(items: I)
    abstract fun setListener(listener: L)
}