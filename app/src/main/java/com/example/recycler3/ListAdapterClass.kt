package com.example.recycler3

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler3.databinding.Item2ForRecycler1Binding
import com.example.recycler3.databinding.ItemForRecycler1Binding

class ListAdapterClass(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val Item_Type_1 = 1
        const val Item_Type_2 = 2
    }

    inner class MushroomViewHolder(private val binding: ItemForRecycler1Binding):RecyclerView.ViewHolder(binding.root){
        @RequiresApi(Build.VERSION_CODES.Q)
        fun bind(mushroom: Mushroom){
            binding.tvMushroomName.text = mushroom.name
        }
    }

    inner class TextViewHolder(private val binding: Item2ForRecycler1Binding):RecyclerView.ViewHolder(binding.root){
        fun bind(mushroom: Mushroom){

        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Mushroom>() {

        override fun areItemsTheSame(oldItem: Mushroom, newItem: Mushroom): Boolean {
            Log.d("tag123","item id was checked ${oldItem.id == newItem.id}")
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Mushroom, newItem: Mushroom): Boolean {
            Log.d("tag123","item content was checked ${oldItem == newItem}")
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(differ.currentList[position].type == Item_Type_1){
            return Item_Type_1
        }else{
            return Item_Type_2
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    fun submitList(list: MutableList<Mushroom>) {
        differ.submitList(list)
    }

    fun add(mushroom: Mushroom, position: Int) {
        val currentList = differ.currentList.toMutableList()
        if (position < currentList.size) {
            currentList.add(position, mushroom)
        } else {
            currentList.add(mushroom) // Add at the end if position is greater than the list size
        }
        submitList(currentList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder{
        Log.d("tag123","onCreate")
        if(viewType == Item_Type_1){
            return MushroomViewHolder(
                ItemForRecycler1Binding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
            )
        }else{
            return TextViewHolder(Item2ForRecycler1Binding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("tag123","onBind")
        if(holder is MushroomViewHolder)holder.bind(differ.currentList[position])
        else if(holder is TextViewHolder)holder.bind(differ.currentList[position])
    }

}