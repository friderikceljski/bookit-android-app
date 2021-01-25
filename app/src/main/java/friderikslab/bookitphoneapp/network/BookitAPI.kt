package friderikslab.bookitphoneapp.network

import friderikslab.bookitphoneapp.models.Building
import friderikslab.bookitphoneapp.models.BuildingJson
import retrofit2.Call
import retrofit2.http.GET

interface BookitAPI {
    @GET("v1/BuildingApi")
    fun getBuildings(): Call<List<BuildingJson>>
}