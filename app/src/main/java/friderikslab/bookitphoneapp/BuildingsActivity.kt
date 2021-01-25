package friderikslab.bookitphoneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import friderikslab.bookitphoneapp.helpers.Constants
import friderikslab.bookitphoneapp.helpers.TopSpacingItemDecoration
import friderikslab.bookitphoneapp.recycleradapters.BuildingRecyclerAdapter
import friderikslab.bookitphoneapp.viewmodels.BuildingsActivityViewModel
import kotlinx.android.synthetic.main.activity_buildings.*

class BuildingsActivity : AppCompatActivity(), BuildingRecyclerAdapter.OnItemClickListener {

    private lateinit var mBuildingsActivityViewModel: BuildingsActivityViewModel
    private lateinit var buildingAdapter: BuildingRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buildings)

        val extras = intent.extras
        val message = extras?.getString(AlarmClock.EXTRA_MESSAGE)
        val actionBar = supportActionBar
        actionBar!!.title = message

        mBuildingsActivityViewModel = ViewModelProvider(this)[BuildingsActivityViewModel::class.java]
        mBuildingsActivityViewModel.init()

        initRecyclerView()

        mBuildingsActivityViewModel.getBuildings().observe(this, Observer {
            buildingAdapter.submitList(it)
        })
    }

    override fun onItemClick(position: Int) {
        var item =  mBuildingsActivityViewModel.getBuildingById(position)
        val imgUrl = item?.imageUrl
        val hotelTitle = item?.name

        val intent = Intent(this, BuildingDetailsActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, imgUrl)
            putExtra(Constants.HOTEL_NAME, hotelTitle)
            putExtra(Constants.HOTEL_ADDRESS, item!!.address)
        }

        startActivity(intent)
    }

    private fun initRecyclerView() {
        val initAdapter =
            BuildingRecyclerAdapter(
                mBuildingsActivityViewModel.getInitBuildings(),
                this
            )
        recycler_view.apply {
            val topSpacingDecoration =
                TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecoration)
            layoutManager = LinearLayoutManager(this@BuildingsActivity)
            buildingAdapter = initAdapter
            adapter = buildingAdapter
        }
    }
}