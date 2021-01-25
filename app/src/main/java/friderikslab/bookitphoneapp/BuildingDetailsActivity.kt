package friderikslab.bookitphoneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import friderikslab.bookitphoneapp.helpers.Constants
import friderikslab.bookitphoneapp.helpers.TopSpacingItemDecoration
import friderikslab.bookitphoneapp.recycleradapters.DetailsRecyclerAdapter
import friderikslab.bookitphoneapp.viewmodels.BuildingDetailsActivityViewModel
import kotlinx.android.synthetic.main.activity_building_details.*

class BuildingDetailsActivity : AppCompatActivity(), DetailsRecyclerAdapter.OnItemClickListener {

    private lateinit var mBuildingDetailsActivityViewModel: BuildingDetailsActivityViewModel
    private lateinit var roomsRecyclerAdapter: DetailsRecyclerAdapter
    private lateinit var imageUrl: String
    private lateinit var hotelAddress: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_building_details)

        val extras = intent.extras
        val _imageUrl = extras?.getString(AlarmClock.EXTRA_MESSAGE)
        val hotelName = extras?.getString(Constants.HOTEL_NAME)
        val _hotelAddress = extras?.getString(Constants.HOTEL_ADDRESS)

        if (_hotelAddress.isNullOrEmpty()) {
            hotelAddress = ""
        } else {
            hotelAddress = _hotelAddress
        }

        val actionBar = supportActionBar
        actionBar!!.title = hotelName
        lbl_name.setText(hotelName)

        if (_imageUrl.isNullOrEmpty()) {
            imageUrl = ""
            Log.d("imageUrl1: ", imageUrl)
        } else {
            imageUrl = _imageUrl
            Log.d("imageUrl2: ", imageUrl)

        }

        mBuildingDetailsActivityViewModel = ViewModelProvider(this)[BuildingDetailsActivityViewModel::class.java]
        mBuildingDetailsActivityViewModel.init()

        initRecyclerView()
        setBuildingImage()

        mBuildingDetailsActivityViewModel.getRooms().observe(this, Observer {
            roomsRecyclerAdapter.submitList(it)
        })
    }



    private fun initRecyclerView() {
        val initAdapter = DetailsRecyclerAdapter(
            mBuildingDetailsActivityViewModel.getInitRooms(),
            this
        )

        recycler_rooms.apply {
            val topSpacingDecoration =
                TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecoration)
            layoutManager = LinearLayoutManager(this@BuildingDetailsActivity)
            roomsRecyclerAdapter = initAdapter
            adapter = roomsRecyclerAdapter
        }
    }

    private fun setBuildingImage() {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Log.d("imageUrl: ", imageUrl)

        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(imageUrl)
            .into(img_hotel)
    }

    override fun onItemClick(position: Int) {
        var room = mBuildingDetailsActivityViewModel.getRoomById(position)

        val intent = Intent(this, ReservationActivity::class.java).apply {
            putExtra(Constants.HOTEL_NAME, lbl_name.text)
            putExtra(Constants.IMAGE_URL, imageUrl)
            putExtra(Constants.HOTEL_ADDRESS, hotelAddress)
            putExtra(Constants.ROOM_TYPE, room?.type)
            putExtra(Constants.RESERVATION_DATE, lbl_name.text)
            putExtra(Constants.USER_FULLNAME, "Jošt Soteški")
            putExtra(Constants.USER_ADDRESS, "Celjski grad 1, 3000 Celje")
        }

        startActivity(intent)
    }
}