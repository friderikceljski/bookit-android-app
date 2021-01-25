package friderikslab.bookitphoneapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import friderikslab.bookitphoneapp.models.Building
import friderikslab.bookitphoneapp.repositories.BuildingRepository
import friderikslab.bookitphoneapp.repositories.DealsRepository

class MainActivityViewModel : ViewModel() {
    private lateinit var mBuildings: MutableLiveData<List<Building>>
    private lateinit var mRepo: DealsRepository

    fun init() {
        //if (mBuildings != null) {
        //    return
        //}

        mRepo = DealsRepository.getInstance()
        mBuildings = mRepo.getBuildingDeals()
    }

    fun getInitBuildingDeals(): List<Building> {
        return mRepo.getInitBuildingDeals()
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