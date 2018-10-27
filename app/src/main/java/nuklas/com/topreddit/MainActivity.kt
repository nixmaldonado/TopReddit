package nuklas.com.topreddit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val presenter: MainActivityPresenter = MainActivityPresenter(this)
    val postFragment: PostFragment = PostFragment()
    val postListFragment: PostListFragment = PostListFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, postListFragment).commit()
        presenter.initialize()
    }
}
