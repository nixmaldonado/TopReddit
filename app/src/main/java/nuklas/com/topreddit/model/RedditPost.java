package nuklas.com.topreddit.model;

import com.google.gson.annotations.SerializedName;

public class RedditPost {

    @SerializedName("data")
    public PostData data;

    public class PostData {

        @SerializedName("author")
        public String author;

        @SerializedName("title")
        public String title;

        @SerializedName("created")
        public long created;

        @SerializedName("num_comments")
        public int commentCount;

        @SerializedName("thumbnail")
        public String imageThumbnailUrl;
    }
}
