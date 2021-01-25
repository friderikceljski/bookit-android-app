package friderikslab.bookitphoneapp.repositories

import androidx.lifecycle.MutableLiveData
import friderikslab.bookitphoneapp.models.Building
import friderikslab.bookitphoneapp.models.Room

class RoomRepository {
    var dataSet: ArrayList<Room> = ArrayList()

    companion object {
        private lateinit var instance: RoomRepository
        fun getInstance(): RoomRepository {
            //if (instance == null) {
            instance = RoomRepository()
            //}

            return instance
        }
    }

    fun getRooms(): MutableLiveData<List<Room>> {
        // pretend to get data from WS
        setRooms()
        val data = MutableLiveData<List<Room>>()
        data.value = dataSet
        return data
    }

    fun getInitRooms(): List<Room> {
        setRooms()
        return dataSet
    }

    fun setRooms() {
        if (dataSet.count() == 0) {
            dataSet.add(Room(1, 101, 2, true, 40.31, "Regular bedroom for two", 1))
            dataSet.add(Room(2, 102, 3, false, 24.60, "Regular bedroom with extra bed", 1))
            dataSet.add(Room(3, 103, 5, false, 51.0, "President suite", 1))
            dataSet.add(Room(4, 104, 1, true, 34.2, "Room for one person", 1))
        }


        // network call

    }
}