package nuklas.com.topreddit.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebClientBuilder {

    private const val BASE_URL = "http://reddit.com/"
    private var client: WebService? = null

    fun getClient(): WebService? {
        if (client == null) {
            buildClient()
        }
        return client
    }

    private fun buildClient() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        client = retrofit.create(WebService::class.java)
    }
}
