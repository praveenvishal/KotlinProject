package com.example.kotlinproject.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.RowChannelListBinding
import com.example.kotlinproject.global.common.AppApplication.Companion.context
import com.example.kotlinproject.model.respo.newsChannel.NewsChanelRespo


class NewsAdapter(private val newsList: ArrayList<NewsChanelRespo.Source>) :
    RecyclerView.Adapter<NewsAdapter.ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = DataBindingUtil.inflate<RowChannelListBinding>(
            LayoutInflater.from(parent.context), R.layout.row_channel_list,
            parent, false
        )
        return ProjectViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList!!.size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    class ProjectViewHolder(val binding: RowChannelListBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
        }

        fun bind(source: NewsChanelRespo.Source) {
            binding.run {
                txtChannelName.text = source.name
                txtChannelDesc.text = source.description
                val stringBuilder = StringBuilder("https://besticon-demo.herokuapp.com/icon?url=")
                stringBuilder.append(source.url)
                stringBuilder.append("&size=64..64..120")
                Log.d("TAG", stringBuilder.toString())
                Glide.with(context).load(stringBuilder.toString()).placeholder(R.drawable.logo)
                    .into(imgChannelImg)
            }
        }
    }

}