package nuklas.com.topreddit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import nuklas.com.topreddit.model.RedditPost.PostData
import nuklas.com.topreddit.utils.POSTS_KEY
import nuklas.com.topreddit.utils.POST_KEY
import nuklas.com.topreddit.utils.PostListAdapter

class MainActivity : AppCompatActivity() {

    private var isLandscape = false
    private val presenter: MainActivityPresenter = MainActivityPresenter(this)
    val postFragment: PostFragment = PostFragment()
    val postListFragment: PostListFragment = PostListFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            if (it.containsKey(POSTS_KEY)) {
                presenter.posts.clear()
                presenter.posts = it.getParcelableArrayList<PostData>(POSTS_KEY)
            }
        }

        frameLayout?.let {
            isLandscape = false
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, postListFragment).commit()
            supportFragmentManager.executePendingTransactions()
        }
        postListFragmentContainerLand?.let {
            isLandscape = true
            supportFragmentManager.beginTransaction().replace(R.id.postListFragmentContainerLand, postListFragment)
                .commit()
            supportFragmentManager.beginTransaction().replace(R.id.postFragmentContainerLand, postFragment).commit()
            supportFragmentManager.executePendingTransactions()
        }
    }

    fun initializePresenter() {
        presenter.initialize()
    }

    fun displayPost(post: PostData) {
        if (isLandscape) {
            postFragment.displayPost(post)
        } else {
            val bundle = Bundle()
            bundle.putParcelable(POST_KEY, post)
            postFragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, postFragment).addToBackStack("list").commit()
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelableArrayList(POSTS_KEY, presenter.posts)
        super.onSaveInstanceState(outState)
    }

    fun setRecyclerAdapterToPostListFragment(adapter: PostListAdapter) {
        if (isLandscape) {
            val fragment: PostListFragment = supportFragmentManager.findFragmentById(R.id.postListFragmentContainerLand) as PostListFragment
            fragment.setRecyclerAdapter(adapter)
        } else {
            val fragment: PostListFragment = supportFragmentManager.findFragmentById(R.id.frameLayout) as PostListFragment
            fragment.setRecyclerAdapter(adapter)
        }
    }
}
