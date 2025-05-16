package com.stephen.filmsimulation.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import com.stephen.filmsimulation.R
import com.stephen.filmsimulation.data.FilmItemBean

class FilmItemAdapter(filmList: List<FilmItemBean>) :
    BaseQuickAdapter<FilmItemBean, QuickViewHolder>() {

    override var items: List<FilmItemBean> = filmList

    private var choosedPosition = -1

    @SuppressLint("NotifyDataSetChanged")
    fun setChoosedPosition(position: Int) {
        choosedPosition = position
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        holder: QuickViewHolder,
        position: Int,
        item: FilmItemBean?,
    ) {
        holder.getView<View>(R.id.v_background_film_roll).visibility =
            if (choosedPosition == position) View.VISIBLE
            else View.INVISIBLE
        holder.getView<ImageView>(R.id.iv_icon_film_roll).setImageResource(item?.brandIconResId!!)
    }

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int,
    ) = QuickViewHolder(R.layout.item_film_roll, parent)
}