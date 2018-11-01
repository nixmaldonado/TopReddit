package nuklas.com.topreddit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_post_list.*
import nuklas.com.topreddit.model.RedditPost
import nuklas.com.topreddit.model.RedditResponse
import nuklas.com.topreddit.utils.PostListAdapter
import nuklas.com.topreddit.utils.WebClientBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostListFragment : Fragment() {

    lateinit var adapter: PostListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as MainActivity).initializePresenter()
        dismissAllButton.setOnClickListener { dismissAll() }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun dismissAll() {
        adapter.clearAll()
    }

    fun setRecyclerAdapter(adapter: PostListAdapter) {
        view?.let {
            this.adapter = adapter
            val swipeLayout: android.support.v4.widget.SwipeRefreshLayout = it.findViewById(R.id.swipeContainer)
            swipeLayout.setOnRefreshListener { refreshPostList(adapter, swipeLayout) }
            it.findViewById<RecyclerView>(R.id.postListRecycler).adapter = adapter
            it.findViewById<RecyclerView>(R.id.postListRecycler).layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun refreshPostList(adapter: PostListAdapter, swipeLayout: SwipeRefreshLayout) {
        WebClientBuilder.getClient()?.topPosts?.enqueue(object : Callback<RedditResponse> {
            override fun onResponse(call: Call<RedditResponse>, response: Response<RedditResponse>) {
                response.body()?.data?.postList.let {
                    adapter.clearAll()
                    val postList: ArrayList<RedditPost> = it as ArrayList<RedditPost>
                    val postDataList: ArrayList<RedditPost.PostData> = ArrayList()
                    for (post in postList) postDataList.add(post.data)
                    adapter.addAll(postDataList)
                }
                swipeLayout.isRefreshing = false
            }

            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                swipeLayout.isRefreshing = false
            }

        })
    }

}
