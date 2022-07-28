package com.example.nestedrecyclerview.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nestedrecyclerview.R
import com.example.nestedrecyclerview.data.HomeItem
import com.example.nestedrecyclerview.data.model.Post
import com.example.nestedrecyclerview.databinding.*
import com.example.nestedrecyclerview.extention.convertNumberToMillionAndKiloString
import com.example.nestedrecyclerview.data.model.Story
import java.lang.Exception

class HomeAdapter(private val itemsList: List<HomeItem<Any>>) :
    RecyclerView.Adapter<HomeAdapter.BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
            VIEW_STORIES -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stories_recycler, parent, false)
                StoriesViewHolder(view)
            }
            VIEW_NEW_POST -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_new_post, parent, false)
                NewPostViewHolder(view)
            }
            VIEW_POST -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
                PostViewHolder(view)
            }
            else -> throw Exception("type_is_wrong")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder){
            is StoriesViewHolder -> { bindStories(holder, position) }
            is NewPostViewHolder -> { bindNewPost(holder, position) }
            is PostViewHolder -> { bindPost(holder, position) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 -> VIEW_STORIES
            1 -> VIEW_NEW_POST
            else -> VIEW_POST
        }
    }

    private fun bindStories(holder: StoriesViewHolder, position: Int){
        val stories = itemsList[position].item as List<Story>
        val adapter = StoriesAdapter(stories)
        holder.binding.apply { recyclerViewStories.adapter = adapter }
    }

    private fun bindNewPost(holder: NewPostViewHolder, position: Int){
        val newPostText = itemsList[position].item as String
        holder.binding.apply {
            editTextNewPost.hint = newPostText
        }
    }

    private fun bindPost(holder: PostViewHolder, position: Int){
        val post = itemsList[position].item as Post
        holder.binding.apply {
            textUserName.text = post.userName
            textPostDate.text = post.postDate
            textPostTitle.text = post.postTitle
            textLikeCount.text = post.convertNumberToMillionAndKiloString(post.postLikesCount)
            textCommentCount.text = post.convertNumberToMillionAndKiloString(post.postCommentCount)
            textShareCount.text = post.convertNumberToMillionAndKiloString(post.postShareCount)
            if (post.postIsLiked){
                imageLike.setImageDrawable(AppCompatResources.getDrawable(this.root.context, R.drawable.ic_full_hart))
            }
            else{
                imageLike.setImageDrawable(AppCompatResources.getDrawable(this.root.context, R.drawable.ic_empty_hart))
            }
            Glide.with(this.root.context).load(post.userImage).into(imageUser)
            Glide.with(this.root.context).load(post.postImage).into(imagePost)
        }
    }

    override fun getItemCount(): Int = itemsList.size


    abstract class BaseViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem)

    class StoriesViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemStoriesRecyclerBinding.bind(viewItem)
    }

    class NewPostViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemNewPostBinding.bind(viewItem)
    }

    class PostViewHolder(viewItem: View) : BaseViewHolder(viewItem) {
        val binding = ItemPostBinding.bind(viewItem)
    }

    companion object{
        private const val VIEW_STORIES = 101
        private const val VIEW_NEW_POST = 102
        private const val VIEW_POST = 103
    }
}