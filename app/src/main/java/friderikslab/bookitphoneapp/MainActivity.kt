package friderikslab.bookitphoneapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.DatePicker
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import friderikslab.bookitphoneapp.helpers.BetweenSpacingItemDecoration
import friderikslab.bookitphoneapp.helpers.Constants
import friderikslab.bookitphoneapp.helpers.TopSpacingItemDecoration
import friderikslab.bookitphoneapp.recycleradapters.DealsRecyclerAdapter
import friderikslab.bookitphoneapp.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, DealsRecyclerAdapter.OnItemClickListener {
    private lateinit var mMainActivityViewModel: MainActivityViewModel
    private lateinit var dealsAdapter: DealsRecyclerAdapter

    var day = 0
    var month = 0
    var year = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cal = Calendar.getInstance()
        val constraintBuilder = CalendarConstraints.Builder()
        constraintBuilder.setValidator(DateValidatorPointForward.now())
        constraintBuilder.setStart(cal.timeInMillis)

        val builder = MaterialDatePicker.Builder.dateRangePicker().setCalendarConstraints(constraintBuilder.build())
        val picker = builder.build()


        picker.addOnPositiveButtonClickListener {
            val S = picker.selection
            Log.d("method fired", "addOnPositiveButtonClickListener")
            if (S == null) {
                Log.d("Picker error", "S is null")
            } else if (S.first != null && S.second != null) {
                var format = SimpleDateFormat("E d MMM")

                var df = format.format(Date(S.first!!))
                var dt = format.format(Date(S.second!!))

                txt_dateFrom.setText("$df - $dt")
            }
        }

        txt_dateFrom.setOnClickListener() {
            picker.show(supportFragmentManager, picker.toString())
        }

        btn_search.setOnClickListener() {
            val message = txt_dateFrom.text.toString()
            val intent = Intent(this, BuildingsActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }

            startActivity(intent)
        }

        mMainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        mMainActivityViewModel.init()

        initRecyclerView()

        mMainActivityViewModel.getBuildings().observe(this, Observer {
            dealsAdapter.submitList(it)
        })
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month + 1 // cause it is in range [0-11] to be compatible with calendar class
        savedYear = year

        txt_dateFrom?.setText("$savedDay. $savedMonth. $savedYear")
    }

    override fun onItemClick(position: Int) {
        var item =  mMainActivityViewModel.getBuildingById(position)
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
                DealsRecyclerAdapter(
                        mMainActivityViewModel.getInitBuildingDeals(),
                        this
                )

        deals_recyclerview.apply {
            val betweenSpacingItemDecoration =
                    BetweenSpacingItemDecoration(15)
            addItemDecoration(betweenSpacingItemDecoration)
            val llm = LinearLayoutManager(this@MainActivity)
            llm.orientation = LinearLayoutManager.HORIZONTAL
            layoutManager = llm
            dealsAdapter = initAdapter
            adapter = dealsAdapter
        }
    }
}