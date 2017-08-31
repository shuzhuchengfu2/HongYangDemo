package com.zhy.calc.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * author： xiongdejin
 * date: 2017/8/31
 * describe:
 */

public class Person implements Parcelable {
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    private String name;

    public Person(String name) {
        this.name = name;
    }

    protected Person(Parcel in) {
        Log.d("client","Person");
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
