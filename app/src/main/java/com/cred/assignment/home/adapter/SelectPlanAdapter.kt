package com.cred.assignment.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cred.assignment.callback.ItemClickListener
import com.cred.assignment.databinding.CardPlanItemBinding
import com.cred.assignment.home.model.Plan

class SelectPlanAdapter(private val callback: ItemClickListener<Plan>) :
    ListAdapter<Plan, SelectPlanAdapter.ViewHolder>(DifferenceCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            CardPlanItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val results = getItem(position)
        holder.apply {
            bind(results)
            itemView.tag = results
        }
    }

    inner class ViewHolder(
        private val binding: CardPlanItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Plan) {
            binding.apply {
                plan = item

                itemView.setOnClickListener {
                    callback.onRecyclerItemClicked(adapterPosition, itemView, item)

                }
                executePendingBindings()
            }
        }
    }


    private class DifferenceCallback : DiffUtil.ItemCallback<Plan>() {

        override fun areItemsTheSame(oldItem: Plan, newItem: Plan): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Plan, newItem: Plan): Boolean {
            return oldItem == newItem
        }
    }

}