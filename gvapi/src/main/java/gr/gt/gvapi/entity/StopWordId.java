package gr.gt.gvapi.entity;

import java.io.Serializable;

public class StopWordId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String word;
    private String locale;

    public StopWordId() {}

    public StopWordId(String word, String locale) {
        super();
        this.word = word;
        this.locale = locale;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((locale == null) ? 0 : locale.hashCode());
        result = prime * result + ((word == null) ? 0 : word.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StopWordId other = (StopWordId) obj;
        if (locale == null) {
            if (other.locale != null)
                return false;
        } else if (!locale.equals(other.locale))
            return false;
        if (word == null) {
            if (other.word != null)
                return false;
        } else if (!word.equals(other.word))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "StopWordId [word=" + word + ", locale=" + locale + "]";
    }

}
