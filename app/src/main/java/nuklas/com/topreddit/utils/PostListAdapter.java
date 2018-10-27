package nuklas.com.topreddit.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import nuklas.com.topreddit.PostListFragment;
import nuklas.com.topreddit.R;
import nuklas.com.topreddit.model.RedditPost;

import java.util.ArrayList;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    private ArrayList<RedditPost> postList;
    private PostListFragment fragment;

    public PostListAdapter(ArrayList<RedditPost> postList, PostListFragment fragment){
        this.postList = postList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View categoryView = inflater.inflate(R.layout.post_list_item, parent, false);
        return new ViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RedditPost post = postList.get(position);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView author;
        public TextView title;
        public TextView commentCount;
        public ImageView postImage;

        ViewHolder(View view) {
            super(view);
            author = view.findViewById(R.id.itemAuthor);
            title = view.findViewById(R.id.postItemTitle);
            commentCount = view.findViewById(R.id.postCommentCount);
            postImage = view.findViewById(R.id.postItemImage);
        }
    }
}
