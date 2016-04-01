package vn.creative.twitterlite.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minhtan512 on 3/27/2016.
 */
public class EntitiesModel implements Parcelable {
    @SerializedName("media")
    private List<Media> media;

    @SerializedName("urls")
    private List<URLs> urls;

    @SerializedName("hashtags")
    private List<HashTag> hashTags;

    public List<Media> getMedia() {
        return media;
    }

    public List<URLs> getUrls() {
        return urls;
    }

    public List<HashTag> getHashTags() {
        return hashTags;
    }

    @Override
    public String toString() {
        return "EntitiesModel{" +
                "media=" + media +
                ", urls=" + urls +
                ", hashTags=" + hashTags +
                '}';
    }

    public class Media {
        @SerializedName("media_url")
        private String url;

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "Media{" +
                    "url='" + url + '\'' +
                    '}';
        }
    }

    public class URLs {
        @SerializedName("display_url")
        private String displayUrl;

        @SerializedName("url")
        private String url;

        public String getDisplayUrl() {
            return displayUrl;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "URLs{" +
                    "displayUrl='" + displayUrl + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    public class HashTag {
        @SerializedName("text")
        private String text;

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return "HashTag{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.media);
        dest.writeList(this.urls);
        dest.writeList(this.hashTags);
    }

    public EntitiesModel() {
    }

    protected EntitiesModel(Parcel in) {
        this.media = new ArrayList<Media>();
        in.readList(this.media, Media.class.getClassLoader());
        this.urls = new ArrayList<URLs>();
        in.readList(this.urls, URLs.class.getClassLoader());
        this.hashTags = new ArrayList<HashTag>();
        in.readList(this.hashTags, HashTag.class.getClassLoader());
    }

    public static final Creator<EntitiesModel> CREATOR = new Creator<EntitiesModel>() {
        @Override
        public EntitiesModel createFromParcel(Parcel source) {
            return new EntitiesModel(source);
        }

        @Override
        public EntitiesModel[] newArray(int size) {
            return new EntitiesModel[size];
        }
    };
}
