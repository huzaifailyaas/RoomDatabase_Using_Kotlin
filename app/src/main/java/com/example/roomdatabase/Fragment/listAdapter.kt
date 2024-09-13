package com.example.roomdatabase

import android.annotation.SuppressLint
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.Fragment.ListFragmentDirections
import com.example.roomdatabase.model.StudentTable

class listAdapter : RecyclerView.Adapter<listAdapter.ViewHolder>() {
    private var UserList = emptyList<StudentTable>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id_txt: TextView = itemView.findViewById(R.id.id_txt)
        val First_txt: TextView = itemView.findViewById(R.id.firstname_txt)
        val Last_txt: TextView = itemView.findViewById(R.id.lastname_txt)
        val Age_txt: TextView = itemView.findViewById(R.id.age_txt)
        val row_layout: ConstraintLayout = itemView.findViewById(R.id.row_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom, parent, false))
    }

    override fun getItemCount(): Int {
        return UserList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentView = UserList[position]
        holder.id_txt.text = currentView.id.toString()
        holder.First_txt.text = currentView.firstname
        holder.Last_txt.text = currentView.lastname
        holder.Age_txt.text = currentView.age.toString()


        holder.row_layout.setOnClickListener {
            val action= ListFragmentDirections.actionListFragmentToUpdateFragment(currentView)
            holder.itemView.findNavController().navigate(action)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(userList: List<StudentTable>) {
        this.UserList = userList
        notifyDataSetChanged()
    }
}
