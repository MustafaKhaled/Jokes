package com.khaled.jokes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khaled.jokes.R
import com.khaled.jokes.data.model.JokeItem
import kotlinx.android.synthetic.main.joke_list_item.view.*

class JokesAdapter: RecyclerView.Adapter<JokesAdapter.JokeViewHolder>() {
    private val jokesList = ArrayList<JokeItem>()
    var onItemClick: ((JokeItem) -> Unit)? = null
    inner class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener{
                onItemClick?.invoke(jokesList[adapterPosition])
            }
        }
        fun bind(joke: JokeItem) = with(itemView) {
            categoryName.text = joke.category
            setup.text = joke.setup
        }
    }


    fun addAll(newJokesList: ArrayList<JokeItem>){
        jokesList.clear()
        jokesList.addAll(newJokesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.joke_list_item,parent,false)
        return JokeViewHolder(view)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val joke = jokesList[position]
        holder.bind(joke)
    }

    override fun getItemCount(): Int {
        return jokesList.size
    }
}