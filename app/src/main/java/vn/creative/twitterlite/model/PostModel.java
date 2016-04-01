package vn.creative.twitterlite.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public class PostModel implements Parcelable {
    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private long id;

    @SerializedName("text")
    private String text;

    @SerializedName("entities")
    private EntitiesModel entities;

    @SerializedName("user")
    private UserModel user;

    @SerializedName("retweet_count")
    private int retweetCount;

    @SerializedName("favorite_count")
    private int favoriteCount;

    public String getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public EntitiesModel getEntities() {
        return entities;
    }

    public UserModel getUser() {
        return user;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "createdAt='" + createdAt + '\'' +
                ", id=" + id +
                ", text='" + text + '\'' +
                ", entities=" + entities +
                ", user=" + user +
                ", retweetCount=" + retweetCount +
                ", favoriteCount=" + favoriteCount +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createdAt);
        dest.writeLong(this.id);
        dest.writeString(this.text);
        dest.writeParcelable(this.entities, flags);
        dest.writeParcelable(this.user, flags);
        dest.writeInt(this.retweetCount);
        dest.writeInt(this.favoriteCount);
    }

    public PostModel() {
    }

    protected PostModel(Parcel in) {
        this.createdAt = in.readString();
        this.id = in.readLong();
        this.text = in.readString();
        this.entities = in.readParcelable(EntitiesModel.class.getClassLoader());
        this.user = in.readParcelable(UserModel.class.getClassLoader());
        this.retweetCount = in.readInt();
        this.favoriteCount = in.readInt();
    }

    public static final Creator<PostModel> CREATOR = new Creator<PostModel>() {
        @Override
        public PostModel createFromParcel(Parcel source) {
            return new PostModel(source);
        }

        @Override
        public PostModel[] newArray(int size) {
            return new PostModel[size];
        }
    };
}
