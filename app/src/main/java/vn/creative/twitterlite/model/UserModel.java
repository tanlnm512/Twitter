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

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", screenName='" + screenName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
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
    }

    public UserModel() {
    }

    protected UserModel(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.screenName = in.readString();
        this.avatar = in.readString();
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
