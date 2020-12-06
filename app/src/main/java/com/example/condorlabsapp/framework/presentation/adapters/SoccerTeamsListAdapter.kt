package com.example.condorlabsapp.framework.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.condorlabsapp.R
import com.example.condorlabsapp.business.domain.model.SoccerTeam
import kotlinx.android.synthetic.main.layout_soccer_team_item.view.*

class SoccerTeamsListAdapter(
    private val requestManager: RequestManager,
    private val selectTeam: (SoccerTeam) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SoccerTeam>() {

        override fun areItemsTheSame(oldItem: SoccerTeam, newItem: SoccerTeam): Boolean {
            return oldItem.teamId == newItem.teamId
        }

        override fun areContentsTheSame(oldItem: SoccerTeam, newItem: SoccerTeam): Boolean {
            return oldItem == newItem
        }

    }
    private val differ =
        AsyncListDiffer(
            BlogRecyclerChangeCallback(this),
            AsyncDifferConfig.Builder(DIFF_CALLBACK).build()
        )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return BlogViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_soccer_team_item,
                parent,
                false
            ),
            requestManager = requestManager,
            selectTeam
        )

    }

    internal inner class BlogRecyclerChangeCallback(
        private val adapter: SoccerTeamsListAdapter
    ) : ListUpdateCallback {

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            adapter.notifyItemRangeChanged(position, count, payload)
        }

        override fun onInserted(position: Int, count: Int) {
            adapter.notifyItemRangeChanged(position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            adapter.notifyDataSetChanged()
        }

        override fun onRemoved(position: Int, count: Int) {
            adapter.notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BlogViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // Prepare the images that will be displayed in the RecyclerView.
    // This also ensures if the network connection is lost, they will be in the cache
    fun preloadGlideImages(
        requestManager: RequestManager,
        list: List<SoccerTeam>
    ) {
        for (team in list) {
            requestManager
                .load(team.temBadge)
                .preload()
        }
    }

    fun submitList(
        blogList: List<SoccerTeam>?,
    ) {
        differ.submitList(blogList)
    }

    class BlogViewHolder
    constructor(
        itemView: View,
        val requestManager: RequestManager,
        private val selectTeam: (SoccerTeam) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: SoccerTeam) = with(itemView) {
            itemView.setOnClickListener {
                selectTeam.invoke(item)
            }

            requestManager
                .load(item.temBadge)
                .transition(withCrossFade())
                .into(itemView.team_badge)
            itemView.team_name.text = item.teamName
            itemView.team_formed_year.text = item.temFoundationYear.toString()
            itemView.team_description.text = item.teamDescription
        }
    }
}
