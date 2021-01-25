package friderikslab.bookitphoneapp.repositories

import android.os.Build
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import friderikslab.bookitphoneapp.models.Building
import friderikslab.bookitphoneapp.models.BuildingJson
import friderikslab.bookitphoneapp.network.Singleton
import friderikslab.bookitphoneapp.network.Singleton.moshi
import friderikslab.bookitphoneapp.network.Singleton.service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.Converter.Factory
import retrofit2.Response
import java.lang.reflect.Type

// Singleton pattern
class BuildingRepository {
    var dataSet: ArrayList<Building> = ArrayList()

    companion object {
        private lateinit var instance: BuildingRepository
        fun getInstance(): BuildingRepository {
            //if (instance == null) {
                instance = BuildingRepository()
            //}

            return instance
        }
    }

    fun getBuildings(): MutableLiveData<List<Building>> {
        // pretend to get data from WS
        setBuildings()
        val data = MutableLiveData<List<Building>>()
        data.value = dataSet
        return data
    }

    fun getInitBuildings(): List<Building> {
        setBuildings()
        return dataSet
    }

    fun setBuildings() {
        // tu bi retrievali iz web servisa ali baze
//        var type: Type = Types.newParameterizedType(
//            MutableList::class.java,
//            BuildingJson::class.java
//        )
//        var adapter: JsonAdapter<List<BuildingJson>> = Singleton.moshi.adapter(type)

//    var cards: List<BuildingJson> = adapter.fromJson(cardsJsonResponse)


//        val buildingResponse = service.getBuildings().enqueue(object : Callback<List<BuildingJson>> {
//            override fun onFailure(call: Call<List<BuildingJson>>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onResponse(
//                call: Call<List<BuildingJson>>,
//                response: Response<List<BuildingJson>>
//            ) {
//                TODO("Not yet implemented")
//
//            }
//        })
//        val buildings = buildingResponse.body()
//        println(buildings)

        if (dataSet.count() == 0) {
            dataSet.add(Building(1, "Hotel Maestral (5*)", "Our best hotel", "Kardeljeva ploscad 1, 1000 Ljubljana", 1, price = 100.45, imageUrl = "https://i.imgur.com/UqHQ3MU.jpg"))
            dataSet.add(Building(2, "Hotel Anderburg (3*)", "Looking for a cheap place to stay in Sentjur? Welcome to Hotel Anderburg, named after Sentjur a few hunderth years ago.", "Ulica XIV. divizije 1, 3230 Sentjur", 1, price = 60.42, imageUrl = "https://i.imgur.com/nCfb3iw.jpg"))
            dataSet.add(Building(3, "Hotel Kranjski janez (5*)", "One of our best. Especially if you like skiing. The sights are outstanding here in Kranjska Gora!", "Kranjska gora 63, 0000 Kranjska Gora", 1, price = 59.3, imageUrl = "https://i.imgur.com/JActfLi.jpg"))
            dataSet.add(Building(4, "Hotel ZOO (4*)", "Want to spend a night just along the Zoo and the best computer science faculty in Slovenia? Hotel ZOO is the answer!", "Vecna pot 113, 1000 Ljubljana", 1, price = 45.3, imageUrl = "https://www.startpage.com/av/proxy-image?piurl=https%3A%2F%2Fwww.visitljubljana.com%2Fassets%2FKongresni-urad-Ljubljane%2FDvorane%2FFRI%2FFRI-zgradba.jpg"))
            dataSet.add(Building(5, "Hotel Kranjski janez (5*)", "One of our best. Especially if you like skiing. The sights are outstanding here in Kranjska Gora!", "Kranjska gora 63, 0000 Kranjska Gora", 1, price = 59.3, imageUrl = "https://i.imgur.com/JActfLi.jpg"))

        }

        // network call

    }
}