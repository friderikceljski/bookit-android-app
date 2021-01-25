package friderikslab.bookitphoneapp.network
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object Singleton {
    val okhttp = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val moshi = Moshi.Builder().build()

    const val BASE_URL = "https://localhost:5001/api/"


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okhttp)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service = retrofit.create(BookitAPI::class.java)
}