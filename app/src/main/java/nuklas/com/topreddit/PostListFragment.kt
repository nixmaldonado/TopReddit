package nuklas.com.topreddit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nuklas.com.topreddit.utils.PostListAdapter

class PostListFragment : Fragment() {

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
        super.onViewCreated(view, savedInstanceState)
    }

    fun setRecyclerAdapter(adapter: PostListAdapter) {
        view?.let{
            it.findViewById<RecyclerView>(R.id.postListRecycler).adapter = adapter
            it.findViewById<RecyclerView>(R.id.postListRecycler).layoutManager = LinearLayoutManager(activity)
        }
    }

}
