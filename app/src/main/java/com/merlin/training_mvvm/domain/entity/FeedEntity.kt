package com.merlin.training_mvvm.domain.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.anko.db.AUTOINCREMENT


@Entity(tableName = FeedEntity.TABLE_NAME)
open class FeedEntity() : com.merlin.training_mvvm.support.room.BaseEntity(), Parcelable {

    object Fields {
        const val ID = "id"
        const val ROOM_NAME = "room_name"
        const val LIVE = "live"
        const val UPDATED_ON = "updated_on"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Fields.ID)
    var id: Int = 0

    @ColumnInfo(name = Fields.ROOM_NAME)
    var room_name: String = ""

    @ColumnInfo(name = Fields.LIVE)
    var live: Boolean = true

    @ColumnInfo(name = Fields.UPDATED_ON)
    var updated_on: Long = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        room_name = parcel.readString()!!
        live = parcel.readByte() != 0.toByte()
        updated_on = parcel.readLong()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(room_name)
        parcel.writeByte(if (live) 1 else 0)
        parcel.writeLong(updated_on)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FeedEntity> {
        override fun createFromParcel(parcel: Parcel): FeedEntity {
            return FeedEntity(parcel)
        }

        override fun newArray(size: Int): Array<FeedEntity?> {
            return arrayOfNulls(size)
        }

        const val TABLE_NAME = "tab_feed"
    }

}