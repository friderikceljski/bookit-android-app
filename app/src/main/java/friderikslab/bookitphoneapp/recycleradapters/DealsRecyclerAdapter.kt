package friderikslab.bookitphoneapp.recycleradapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import friderikslab.bookitphoneapp.R
import friderikslab.bookitphoneapp.models.Building
import kotlinx.android.synthetic.main.layout_hottest_card.view.*

class DealsRecyclerAdapter(
        private var items: List<Building>,
        private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private var items: List<Building> = buildingList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // vsak element recyclerja je viewholder
        return BuildingViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_hottest_card, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is BuildingViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    fun submitList(buildingList: List<Building>) {
        items = buildingList
    }

    inner class BuildingViewHolder constructor(
            itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        // pointerji na elemente v layoutu
        val buildingName = itemView.building_name
        val buildingPrice = itemView.building_price
        val buildingImage = itemView.building_image

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        fun bind(building: Building) {
            // bindamo podatke na pointerje
            buildingName.setText(building.name)
            buildingPrice.setText("${building.price.toString()} EUR on average")

            val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(building.imageUrl)
                    .into(buildingImage)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}