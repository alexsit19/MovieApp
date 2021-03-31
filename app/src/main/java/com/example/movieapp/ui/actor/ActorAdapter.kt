package com.example.movieapp.ui.actor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.Actor

class ActorAdapter: RecyclerView.Adapter<ViewHolderActors>() {



    private var actorList = listOf<Actor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderActors {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val actorView = inflater.inflate(R.layout.view_holder_actor, parent, false)
        return ViewHolderActors(actorView)
    }

    override fun onBindViewHolder(holder: ViewHolderActors, position: Int) {
        holder.bind(actorList[position])
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

    fun updateActors(actors: List<Actor>?) {
        if (actors != null) {
            actorList = actors
        }
        notifyDataSetChanged()
    }

}

class ViewHolderActors(view: View) : RecyclerView.ViewHolder(view) {
    private val imageActor = itemView.findViewById<ImageView>(R.id.iv_actor)
    private val nameActor = itemView.findViewById<TextView>(R.id.tv_actor)

    fun bind(actor: Actor) {
        nameActor.text = actor.name

        Glide.with(itemView.context)
                .load(actor.picture)
                .placeholder(R.drawable.person_base_line)
                .centerCrop()
                .into(imageActor)

    }

}