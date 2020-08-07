package com.example.kotlinapp
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapp.model.UserRepos
import com.example.kotlinapp.utils.CustomSnackBar


class UserAdapter (
    dataList: List<UserRepos>,
    context: Context
) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private val dataList: List<UserRepos> = dataList
    private val con: Context = context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int) {
        val currentRow: UserRepos = dataList[position]
        holder.itemView.setOnClickListener{
            CustomSnackBar
                .make(it,currentRow.updated_at, currentRow.forks,currentRow.stargazers_count)
                .show()
        }
        holder.bindData(currentRow)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var title: TextView = itemView.findViewById(R.id.title)
        private var desc: TextView = itemView.findViewById(R.id.desc)

        fun bindData(current: UserRepos) {
            title.text = current.name
            desc.text = current.description

        }
    }
}