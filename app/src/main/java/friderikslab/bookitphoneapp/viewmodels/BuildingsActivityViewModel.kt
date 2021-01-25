package friderikslab.bookitphoneapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import friderikslab.bookitphoneapp.models.Building
import friderikslab.bookitphoneapp.repositories.BuildingRepository

class BuildingsActivityViewModel : ViewModel() {
    private lateinit var mBuildings: MutableLiveData<List<Building>>
    private lateinit var mRepo: BuildingRepository

    fun init() {
        //if (mBuildings != null) {
        //    return
        //}

        mRepo = BuildingRepository.getInstance()
        mBuildings = mRepo.getBuildings()
    }

    fun getInitBuildings(): List<Building> {
        return mRepo.getInitBuildings()
    }

    fun getBuildingById(position: Int): Building? {
        val building = mBuildings.value?.get(position)
        if (building != null) {
            return building
        } else {
            return null
        }
    }

    fun getBuildings(): LiveData<List<Building>> {
        return mBuildings
    }
}