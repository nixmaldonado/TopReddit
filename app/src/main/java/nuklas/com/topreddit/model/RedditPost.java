package nuklas.com.topreddit.model;

import com.google.gson.annotations.SerializedName;

class RedditPost {

    @SerializedName("data")
    public PostData postData;

    private class PostData {

        @SerializedName("author")
        public String author;

        @SerializedName("title")
        public String title;

        @SerializedName("created")
        public long created;

        @SerializedName("num_comments")
        public int commentsCount;
    }
}
