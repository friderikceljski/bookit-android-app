package friderikslab.bookitphoneapp.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Building(val buildingID: Int,
                    val name: String,
                    val description: String,
                    val address: String,
                    val type: Int,
                    val imageUrl: String,
                    val price: Double)

@JsonClass(generateAdapter = true)
data class BuildingJson(@field:Json(name = "buildingID") val buildingID: Int,
                        @field:Json(name = "buildingName") val name: String,
                        @field:Json(name = "description") val description: String,
                        @field:Json(name = "address") val address: String,
                        @field:Json(name = "type") val type: Int)
//                        @field:Json(name = "rooms") val rooms: List<Room>,
//                        @field:Json(name = "reservations") val reservations: List<Reservation>)

data class Reservation(@field:Json(name = "reservationID") val reservationID: Int,
                       @field:Json(name = "dateFrom") val dateFrom: String,
                       @field:Json(name = "dateTo") val dateTo: String,
                       @field:Json(name = "roomID") val roomID: Int,
                       @field:Json(name = "applicationUserId") val applicationUserId: String,
                       @field:Json(name = "buildingID") val buildingID: Int,
                       @field:Json(name = "confirmed") val confirmed: Boolean,
                       @field:Json(name = "applicationUser") val applicationUser: ApplicationUser)

data class ApplicationUser(@field:Json(name = "id") val id: String,
                           @field:Json(name = "email") val email: String,
                           @field:Json(name = "phoneNumber") val phoneNumber: String,
                           @field:Json(name = "lastName") val lastName: String,
                           @field:Json(name = "firstName") val firstName: String,
                           @field:Json(name = "address") val address: String,
                           @field:Json(name = "birthDate") val birthDate: String) // TODO: reservations

@JsonClass(generateAdapter = true)
data class Room(@field:Json(name = "roomID") val roomID: Int,
                @field:Json(name = "roomNumber") val roomNumber: Int,
                @field:Json(name = "persons") val persons: Int,
                @field:Json(name = "view") val view: Boolean,
                @field:Json(name = "pricePerNight") val pricePerNight: Double,
                @field:Json(name = "type") val type: String,
                @field:Json(name = "buildingID") val buildingID: Int)
