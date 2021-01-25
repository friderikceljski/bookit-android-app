package friderikslab.bookitphoneapp.recycleradapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import friderikslab.bookitphoneapp.R
import friderikslab.bookitphoneapp.models.Room
import kotlinx.android.synthetic.main.activity_reservation.view.*
import kotlinx.android.synthetic.main.layout_rooms.view.*

class DetailsRecyclerAdapter(
    private var items: List<Room>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private var items: List<Room> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // vsak element recyclerja je viewholder
        return RoomViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_rooms,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is RoomViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    fun submitList(roomList: List<Room>) {
        items = roomList
    }

    inner class RoomViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        // pointerji na elemente v layoutu
        val type = itemView.room_type
        val price = itemView.room_price
        val persons = itemView.room_persons
        val view = itemView.room_view

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

        fun bind(room: Room) {
            // bindamo podatke na pointerje
            type.setText(room.type)
            price.setText(room.pricePerNight.toString() + " EUR per night")
            persons.setText("for " + room.persons + "person(s)")

            val v = room.view
            if (v) {
                view.setText("This room has a magnificent view of the sights.")
            } else {
                view.setText("")
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}