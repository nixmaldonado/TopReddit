package nuklas.com.topreddit.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import nuklas.com.topreddit.MainActivity;
import nuklas.com.topreddit.R;
import nuklas.com.topreddit.model.RedditPost.PostData;

import java.util.ArrayList;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    private ArrayList<PostData> postList;
    private MainActivity activity;

    public PostListAdapter(ArrayList<PostData> postList, MainActivity activity){
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
        PostData post = postList.get(position);
        holder.author.setText(post.author);
        holder.title.setText(post.title);
        holder.commentCount.setText(String.format("%s comments", String.valueOf(post.commentCount)));
        holder.postTime.setText(DateUtils.getRelativeTimeSpanString(post.created * 1000));
        Glide.with(activity)
                .load(post.imageThumbnailUrl)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView author;
        public TextView title;
        public TextView commentCount;
        public ImageView thumbnail;
        public TextView postTime;

        ViewHolder(View view) {
            super(view);
            author = view.findViewById(R.id.itemAuthor);
            title = view.findViewById(R.id.postItemTitle);
            commentCount = view.findViewById(R.id.postCommentCount);
            thumbnail = view.findViewById(R.id.postItemImage);
            postTime = view.findViewById(R.id.postItemTime);
            view.findViewById(R.id.dismissPostButton).setOnClickListener(this);
            view.findViewById(R.id.middleLayout).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.dismissPostButton:
                    postList.remove(getAdapterPosition());
                    notifyDataSetChanged();
                    break;
                case R.id.middleLayout:
                    activity.displayPost(postList.get(getAdapterPosition()));
                    break;
            }
        }
    }
}
