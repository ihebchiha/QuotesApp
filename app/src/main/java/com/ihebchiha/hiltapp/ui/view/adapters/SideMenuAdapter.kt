package com.ihebchiha.hiltapp.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ihebchiha.hiltapp.utils.sidemenu.MenuItem
import com.ihebchiha.hiltapp.R
import kotlinx.android.synthetic.main.item_side_menu.view.*

class SideMenuAdapter(
    private var menuItems: MutableList<MenuItem>
) : RecyclerView.Adapter<SideMenuAdapter.ViewHolder>() {

    lateinit var menuItemClicked: (Int) -> Unit

    override fun getItemCount(): Int {
        return menuItems.size
    }

    fun setList(menuItems: MutableList<MenuItem>) {
        this.menuItems = menuItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_side_menu,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(position)
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val itemImage = view.menuItemIcon
        private val itemName = view.profileName


        fun bindViewHolder(position: Int) {
            itemName.text = view.context.resources.getText(menuItems[position].title)
            itemImage.setImageResource(menuItems[position].icon)

            view.setOnClickListener {
                menuItemClicked?.invoke(position)
            }

        }
    }
}