package friderikslab.bookitphoneapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import friderikslab.bookitphoneapp.models.Building
import friderikslab.bookitphoneapp.models.Room
import friderikslab.bookitphoneapp.repositories.RoomRepository

class BuildingDetailsActivityViewModel : ViewModel() {
    private lateinit var mRooms: MutableLiveData<List<Room>>
    private lateinit var mRepo: RoomRepository

    fun init() {
        //if (mBuildings != null) {
        //    return
        //}

        mRepo = RoomRepository.getInstance()
        mRooms = mRepo.getRooms()
    }

    fun getInitRooms(): List<Room> {
        return mRepo.getInitRooms()
    }

    fun getRooms(): LiveData<List<Room>> {
        return mRooms
    }

    fun getRoomById(position: Int): Room? {
        val room = mRooms.value?.get(position)
        if (room != null) {
            return room
        }

        return null
    }
}