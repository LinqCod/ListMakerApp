package com.linqcod.listmakerapp.ui.main.model

import android.os.Parcel
import android.os.Parcelable

class TaskList(
    val title: String,
    val tasks : ArrayList<String> = ArrayList()
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeStringList(tasks)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<TaskList> {
        override fun createFromParcel(parcel: Parcel) = TaskList(parcel)
        override fun newArray(size: Int): Array<TaskList?> = arrayOfNulls(size)
    }
}
