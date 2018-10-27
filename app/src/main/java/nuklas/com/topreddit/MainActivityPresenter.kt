package nuklas.com.topreddit

import nuklas.com.topreddit.model.RedditPost
import nuklas.com.topreddit.model.RedditResponse
import nuklas.com.topreddit.utils.PostListAdapter
import nuklas.com.topreddit.utils.WebClientBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter(var view: MainActivity): Callback<RedditResponse> {

    private lateinit var posts: ArrayList<RedditPost>
    private var adapter: PostListAdapter? = null

    fun initialize() {
        WebClientBuilder.getClient()?.topPosts?.enqueue(this)
    }

    override fun onResponse(call: Call<RedditResponse>, response: Response<RedditResponse>) {
            posts = response.body()?.data?.postList as ArrayList<RedditPost>
            adapter = PostListAdapter(posts, view)
    }

    override fun onFailure(call: Call<RedditResponse>, t: Throwable) = Unit

}