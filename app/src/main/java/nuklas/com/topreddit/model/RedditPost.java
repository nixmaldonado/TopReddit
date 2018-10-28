package nuklas.com.topreddit.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class RedditPost {

    @SerializedName("data")
    public PostData data;

    public static class PostData implements Parcelable {

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

        protected PostData(Parcel in) {
            author = in.readString();
            title = in.readString();
            created = in.readLong();
            commentCount = in.readInt();
            imageThumbnailUrl = in.readString();
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
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(author);
            dest.writeString(title);
            dest.writeLong(created);
            dest.writeInt(commentCount);
            dest.writeString(imageThumbnailUrl);
        }
    }
}
