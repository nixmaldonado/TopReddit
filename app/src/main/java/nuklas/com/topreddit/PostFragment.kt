package nuklas.com.topreddit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_post.*
import nuklas.com.topreddit.model.RedditPost.PostData
import nuklas.com.topreddit.utils.POST_KEY

class PostFragment : Fragment() {

    private var post: PostData? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            arguments?.let { post = it.get(POST_KEY) as PostData }
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        post?.let { displayPost(it) }
        super.onViewCreated(view, savedInstanceState)
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
