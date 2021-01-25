package friderikslab.bookitphoneapp.repositories

import androidx.lifecycle.MutableLiveData
import friderikslab.bookitphoneapp.models.Building

class DealsRepository {
    var dataSet: ArrayList<Building> = ArrayList()

    companion object {
        private lateinit var instance: DealsRepository
        fun getInstance(): DealsRepository {
            //if (instance == null) {
            instance = DealsRepository()
            //}

            return instance
        }
    }

    fun getBuildingDeals(): MutableLiveData<List<Building>> {
        // pretend to get data from WS
        setBuildingDeals()
        val data = MutableLiveData<List<Building>>()
        data.value = dataSet
        return data
    }

    fun getInitBuildingDeals(): List<Building> {
        setBuildingDeals()
        return dataSet
    }

    fun setBuildingDeals() {
        // tu bi retrievali iz web servisa ali baze

        if (dataSet.count() == 0) {
            dataSet.add(Building(1, "Hotel Maestral (5*)", "Our best hotel", "Kardeljeva ploscad 1, 1000 Ljubljana", 1, price = 100.45, imageUrl = "https://i.imgur.com/UqHQ3MU.jpg"))
            dataSet.add(Building(2, "Hotel Anderburg (3*)", "Looking for a cheap place to stay in Sentjur? Welcome to Hotel Anderburg, named after Sentjur a few hunderth years ago.", "Ulica XIV. divizije 1, 3230 Sentjur", 1, price = 60.42, imageUrl = "https://i.imgur.com/nCfb3iw.jpg"))
            dataSet.add(Building(3, "Hotel Kranjski janez (5*)", "One of our best. Especially if you like skiing. The sights are outstanding here in Kranjska Gora!", "Kranjska gora 63, 0000 Kranjska Gora", 1, price = 59.3, imageUrl = "https://i.imgur.com/JActfLi.jpg"))
        }

        // network call

    }
}