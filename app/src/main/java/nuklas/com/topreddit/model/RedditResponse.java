package nuklas.com.topreddit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RedditResponse {

    @SerializedName("data")
    public RedditData data;

    public class RedditData {

        @SerializedName("children")
        public ArrayList<RedditPost> postList;
    }
}
