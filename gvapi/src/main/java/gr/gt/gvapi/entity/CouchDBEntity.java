package gr.gt.gvapi.entity;

import java.util.List;

public class CouchDBEntity {

    private String _id;
    private String _rev;
    private List<String> media_list;
    private List<String> tweet_list;
    private List<String> hashtag_list;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_rev() {
        return _rev;
    }

    public void set_rev(String _rev) {
        this._rev = _rev;
    }

    public List<String> getMedia_list() {
        return media_list;
    }

    public void setMedia_list(List<String> media_list) {
        this.media_list = media_list;
    }

    public List<String> getTweet_list() {
        return tweet_list;
    }

    public void setTweet_list(List<String> tweet_list) {
        this.tweet_list = tweet_list;
    }

    public List<String> getHashtag_list() {
        return hashtag_list;
    }

    public void setHashtag_list(List<String> hashtag_list) {
        this.hashtag_list = hashtag_list;
    }

}
