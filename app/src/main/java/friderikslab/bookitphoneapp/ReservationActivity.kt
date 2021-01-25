package friderikslab.bookitphoneapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import friderikslab.bookitphoneapp.helpers.Constants
import kotlinx.android.synthetic.main.activity_reservation.*

class ReservationActivity : AppCompatActivity() {
    private lateinit var imageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        val actionBar = supportActionBar
        actionBar!!.title = "Reserve a room"

        val extras = intent.extras
        val hotelName = extras?.getString(Constants.HOTEL_NAME)
        val hotelAddress = extras?.getString(Constants.HOTEL_ADDRESS)
        val userAddress = extras?.getString(Constants.USER_ADDRESS)
        val userFullname = extras?.getString(Constants.USER_FULLNAME)
        val _imageUrl = extras?.getString(Constants.IMAGE_URL)
        if (_imageUrl.isNullOrEmpty()) {
            imageUrl = ""
        } else {
            imageUrl = _imageUrl
        }
        val reservationDate = extras?.getString(Constants.RESERVATION_DATE)
        val roomType = extras?.getString(Constants.ROOM_TYPE)

        lbl_hotel_name.text = hotelName
        lbl_hotel_address.text = hotelAddress
        lbl_hotel_room_type.text = roomType
        lbl_user_name.text = userFullname
        lbl_user_address.text = userAddress
        lbl_date.text = reservationDate
        setBuildingImage()
    }

    private fun setBuildingImage() {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(this)
            .applyDefaultRequestOptions(requestOptions)
            .load(imageUrl)
            .into(img_hotel)
    }
}