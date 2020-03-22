package com.gdc.mysqlcrudandroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdc.mysqlcrudandroid.R
import com.gdc.mysqlcrudandroid.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(private val context: Context): RecyclerView.Adapter<UserAdapter.UserHolder>() {

    private var userList: List<User> = ArrayList()
    private lateinit var clickListener: ClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun setUserList(userList: List<User>) {
        this.userList = userList
        notifyDataSetChanged()
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    inner class UserHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private val name: TextView = view.tv_name
        private val age: TextView = view.tv_age
        private val address: TextView = view.tv_address
        private val btnMore: ImageButton = view.btn_more

        init {
            btnMore.setOnClickListener(this)
        }

        fun bind(user: User) {
            name.text = user.nama
            age.text = user.usia
            address.text = user.domisili
        }

        override fun onClick(p0: View?) {
            clickListener.onMoreClick(adapterPosition, btnMore)
        }
    }

    interface ClickListener {
        fun onMoreClick(position: Int, button: ImageButton)
    }
}