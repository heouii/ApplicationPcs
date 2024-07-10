import android.os.Parcel
import android.os.Parcelable

data class Appartement(
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val postal_code: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),

        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(address)
        parcel.writeString(city)
        parcel.writeInt(postal_code)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Appartement> {
        override fun createFromParcel(parcel: Parcel): Appartement {
            return Appartement(parcel)
        }

        override fun newArray(size: Int): Array<Appartement?> {
            return arrayOfNulls(size)
        }
    }
}

data class Reservation(
    val start_time: String,
    val end_time: String,
    val nombre_de_personne: Int,
    val appartement: Appartement,
    val prix: Int,
    val property_type: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readParcelable(Appartement::class.java.classLoader) ?: Appartement(0, "", "", "",0),
        parcel.readInt(),
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(start_time)
        parcel.writeString(end_time)
        parcel.writeInt(nombre_de_personne)
        parcel.writeParcelable(appartement, flags)
        parcel.writeInt(prix)
        parcel.writeString(property_type)
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
