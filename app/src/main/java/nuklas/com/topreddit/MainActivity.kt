package nuklas.com.topreddit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import nuklas.com.topreddit.model.RedditPost.PostData

class MainActivity : AppCompatActivity() {

    private var isLandscape = false
    private val presenter: MainActivityPresenter = MainActivityPresenter(this)
    val postFragment: PostFragment = PostFragment()
    val postListFragment: PostListFragment = PostListFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frameLayout?.let {
            isLandscape = false
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, postListFragment).commit()
            supportFragmentManager.executePendingTransactions()
        }
        postListFragmentContainerLand?.let {
            isLandscape = true
            supportFragmentManager.beginTransaction().replace(R.id.postListFragmentContainerLand, postListFragment).commit()
            supportFragmentManager.beginTransaction().replace(R.id.postFragmentContainerLand, postFragment).commit()
            supportFragmentManager.executePendingTransactions()
        }
    }

    fun initializePresenter() {
        presenter.initialize()
    }

    fun displayPost(post: PostData) {
        if (isLandscape) { postFragment.displayPost(post) }
    }
}
