package nuklas.com.topreddit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_post.*
import nuklas.com.topreddit.model.RedditPost.PostData

class PostFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    fun displayPost(post: PostData) {
        userName.text = post.author
        postTitle.text = post.title
        activity?.let {
            Glide.with(it)
                .load(post.imageThumbnailUrl)
                .into(postImage)
        }
    }
}
