package vn.creative.twitterlite.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public class UserModel implements Parcelable {
    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("screen_name")
    private String screenName;

    @SerializedName("profile_image_url")
    private String avatar;

    @SerializedName("followers_count")
    private int followersCount;

    @SerializedName("friends_count")
    private int friendsCount;

    @SerializedName("following")
    private boolean following;

    @SerializedName("follow_request_sent")
    private boolean followRequestSent;

    @SerializedName("profile_banner_url")
    private String profileBannerUrl;

    @SerializedName("description")
    private String description;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public boolean isFollowing() {
        return following;
    }

    public boolean isFollowRequestSent() {
        return followRequestSent;
    }

    public String getProfileBannerUrl() {
        return profileBannerUrl;
    }

    public String getDescription() {
        return description;
    }

    public UserModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.screenName);
        dest.writeString(this.avatar);
        dest.writeInt(this.followersCount);
        dest.writeInt(this.friendsCount);
        dest.writeByte(following ? (byte) 1 : (byte) 0);
        dest.writeByte(followRequestSent ? (byte) 1 : (byte) 0);
        dest.writeString(this.profileBannerUrl);
        dest.writeString(this.description);
    }

    protected UserModel(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.screenName = in.readString();
        this.avatar = in.readString();
        this.followersCount = in.readInt();
        this.friendsCount = in.readInt();
        this.following = in.readByte() != 0;
        this.followRequestSent = in.readByte() != 0;
        this.profileBannerUrl = in.readString();
        this.description = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
