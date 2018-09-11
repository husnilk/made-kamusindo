package net.husnilkamil.kamusindo.db;

import android.os.Parcel;
import android.os.Parcelable;

public class Kamus implements Parcelable {
    public int _id;
    public String word;
    public String definition;

    public Kamus() {
    }

    protected Kamus(Parcel in) {
        this.word = in.readString();
        this.definition = in.readString();
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.word);
        dest.writeString(this.definition);
        dest.writeInt(this._id);
    }

    public static final Parcelable.Creator<Kamus> CREATOR = new Parcelable.Creator<Kamus>() {
        @Override
        public Kamus createFromParcel(Parcel source) {
            return new Kamus(source);
        }

        @Override
        public Kamus[] newArray(int size) {
            return new Kamus[size];
        }
    };
}
