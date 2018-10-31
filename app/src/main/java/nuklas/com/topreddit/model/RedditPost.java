package nuklas.com.topreddit.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class RedditPost {

    @SerializedName("data")
    public PostData data;

    public static class PostData implements Parcelable {

        public PostData() {
            isNewPost = true;
        }

        @SerializedName("author")
        public String author;

        @SerializedName("title")
        public String title;

        @SerializedName("created_utc")
        public long created;

        @SerializedName("num_comments")
        public int commentCount;

        @SerializedName("thumbnail")
        public String imageThumbnailUrl;

        @SerializedName("url")
        public String urlString;

        public boolean isNewPost;

        protected PostData(Parcel in) {
            author = in.readString();
            title = in.readString();
            created = in.readLong();
            commentCount = in.readInt();
            imageThumbnailUrl = in.readString();
            urlString = in.readString();
            isNewPost = in.readInt() == 1;
        }

        public static final Creator<PostData> CREATOR = new Creator<PostData>() {
            @Override
            public PostData createFromParcel(Parcel in) {
                return new PostData(in);
            }

            @Override
            public PostData[] newArray(int size) {
                return new PostData[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeString(author);
            parcel.writeString(title);
            parcel.writeLong(created);
            parcel.writeInt(commentCount);
            parcel.writeString(imageThumbnailUrl);
            parcel.writeString(urlString);
            parcel.writeInt(isNewPost ? 1 : 0);
        }
    }
}
