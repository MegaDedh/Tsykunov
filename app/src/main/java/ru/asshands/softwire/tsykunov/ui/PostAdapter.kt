package ru.asshands.softwire.tsykunov.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import ru.asshands.softwire.tsykunov.R
import ru.asshands.softwire.tsykunov.databinding.PostItemBinding
import ru.asshands.softwire.tsykunov.models.Post
import ru.asshands.softwire.tsykunov.utils.loadImage
import ru.asshands.softwire.tsykunov.utils.loadImageWithPb
import kotlin.coroutines.coroutineContext

class PostAdapter : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private var _bind: PostItemBinding? = null
    private val bind get() = _bind!!
    private var list: List<Post> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _bind = PostItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
            holder.bindView(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun bindData(newList: List<Post>) {
        list = newList
        notifyDataSetChanged()
    }

    class ViewHolder(private val bind: PostItemBinding) :
        RecyclerView.ViewHolder(bind.root) {

        fun bindView(item: Post) {
            bind.postItemDesc.text = item.description

            if (item.gifURL == null) {
                bind.postItemImageContainer.loadImage("")
                Snackbar.make(
                    bind.postItemImageContainer, R.string.missing_gif_url,
                    Snackbar.LENGTH_LONG
                ).show()
            } else {

                bind.postItemProgressBar.visibility = View.VISIBLE
                bind.postItemImageContainer.loadImageWithPb(
                    item.gifURL,
                    bind.postItemProgressBar
                )
            }
        }
    }
}