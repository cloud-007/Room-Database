package com.example.roomdatabse.fragment.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabse.model.User
import com.example.roomdatabse.R
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.tvCrId.text = currentItem.id.toString()
        holder.itemView.tvCrFirstName.text = currentItem.firstName
        holder.itemView.tvCrLastName.text = currentItem.lastName
        holder.itemView.tvCrAge.text = currentItem.age.toString()
        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(data: List<User>){
        this.userList = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}