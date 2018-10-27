package nuklas.com.topreddit.utils;

import nuklas.com.topreddit.model.RedditResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {
    @GET("r/all/top.json?limit=50")
    Call<RedditResponse> getTopPosts();
}
