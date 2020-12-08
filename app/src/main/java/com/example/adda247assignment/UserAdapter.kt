package com.example.adda247assignment

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.adda247assignment.model.Data
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter : PagedListAdapter<Data, UserAdapter.UserViewHolder>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it) }
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val userName = view.name
        private val userEmail = view.email
        private val gender = view.gender
        private val status = view.status

        fun bind(user: Data) {
            userName.text = user.name
            userEmail.text = user.email
            gender.text = user.gender
            status.text = user.status
            
            if(user.status == "Active") {
                status.setTextColor(Color.GREEN)
            } else 
                status.setTextColor(Color.RED)
        }

    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean =
                newItem == oldItem
        }
    }
}