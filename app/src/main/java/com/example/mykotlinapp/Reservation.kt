import android.os.Parcel
import android.os.Parcelable

data class Reservation(
    val start_time: String,
    val end_time: String,
    val nombre_de_personne: Int,
    val appartement_id: Int,
    val prix: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(start_time)
        parcel.writeString(end_time)
        parcel.writeInt(nombre_de_personne)
        parcel.writeInt(appartement_id)
        parcel.writeInt(prix)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Reservation> {
        override fun createFromParcel(parcel: Parcel): Reservation {
            return Reservation(parcel)
        }

        override fun newArray(size: Int): Array<Reservation?> {
            return arrayOfNulls(size)
        }
    }
}
