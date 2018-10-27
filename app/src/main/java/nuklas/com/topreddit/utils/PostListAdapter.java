package nuklas.com.topreddit.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import nuklas.com.topreddit.R;
import nuklas.com.topreddit.model.RedditPost;

import java.util.ArrayList;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    private ArrayList<RedditPost> postList;
    private Activity activity;

    public PostListAdapter(ArrayList<RedditPost> postList, Activity activity){
        this.postList = postList;
        this.activity = activity;
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
        holder.author.setText(post.data.author);
        holder.commentCount.setText(post.data.commentCount);
        Glide.with(activity)
                .load(post.data.imageThumbnailUrl)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView author;
        public TextView title;
        public TextView commentCount;
        public ImageView thumbnail;

        ViewHolder(View view) {
            super(view);
            author = view.findViewById(R.id.itemAuthor);
            title = view.findViewById(R.id.postItemTitle);
            commentCount = view.findViewById(R.id.postCommentCount);
            thumbnail = view.findViewById(R.id.postItemImage);
        }
    }
}
