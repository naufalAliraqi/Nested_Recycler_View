package com.example.nestedrecyclerview.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nestedrecyclerview.R
import com.example.nestedrecyclerview.databinding.ItemAddStoryBinding
import com.example.nestedrecyclerview.databinding.ItemStoryBinding
import com.example.nutritionapp.data.model.Story
import java.lang.Exception

class StoriesAdapter(private val stories: List<Story>) :
    RecyclerView.Adapter<StoriesAdapter.BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
            VIEW_ADD_STORY -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_add_story, parent, false)
                AddStoryViewHolder(view)
            }
            VIEW_STORY -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
                StoryViewHolder(view)
            }
            else -> throw Exception("type_is_wrong")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> VIEW_ADD_STORY
            else -> VIEW_STORY
        }
    }

    private fun bindStory(holder: StoryViewHolder, position: Int){
        val currentStory = stories[position - 1]
        holder.binding.apply {
            if (currentStory.shown){
                imageStoryNotShown.visibility = View.INVISIBLE
            }
            else{
                imageStoryNotShown.visibility = View.VISIBLE
            }
            Glide.with(this.root.context).load(currentStory.storyImageURL).into(imageUser)
        }
    }

    override fun getItemCount(): Int = stories.size + 1


    abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)

    class StoryViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemStoryBinding.bind(viewItem)
    }

    class AddStoryViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemAddStoryBinding.bind(viewItem)
    }

    companion object{
        private const val VIEW_ADD_STORY = 101
        private const val VIEW_STORY = 102
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder){
            is AddStoryViewHolder -> {holder.binding.apply {  }}
            is StoryViewHolder -> { bindStory(holder, position) }
        }
    }
}