package com.example.unclep.testpages

//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.os.AsyncTask
//import android.widget.ImageView
//import android.widget.Toast
//import java.io.Serializable
//import java.net.URL


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.provider.ContactsContract
import android.widget.ImageView
import android.widget.Toast
import java.io.Serializable
import java.net.URL
import android.R.attr.author
import java.lang.reflect.Constructor

class Credentials:Serializable{
    var email:String
    var password:String
    var username:String
    var phone_number:String
    constructor(email: String, username:String, password:String, phone_number: String){
        this.email = email
        this.username = username
        this.password = password
        this.phone_number = phone_number
    }


}

class OpeningHours: Serializable {
    constructor()
    constructor(day: String?, open: String?, close: String?) {
        this.day = day
        this.open = open
        this.close = close
    }

    var day:String? = null
    var open:String? = null
    var close:String? = null
}

class ContactInformation: Serializable {

    constructor()
    constructor(email: String?, address: Address?, phone_number: String?, website: String?, social_accounts: SocialAccounts?, sameEmailLogin:Boolean?, sameWhatsappNumber:Boolean) {
        this.email = email
        this.address = address
        this.phone_number = phone_number
        this.website = website
        this.social_accounts = social_accounts
        this.loginEmailSameAsContact = sameEmailLogin
        this.loginNumberSameAsWhatsapp = sameWhatsappNumber
    }

    var email:String? = null
    var address:Address? = null
    var phone_number:String? = null
    var website:String? = null
    var social_accounts:SocialAccounts? = null

    var loginEmailSameAsContact:Boolean? = null
    var loginNumberSameAsWhatsapp:Boolean? = null

}

class Address: Serializable {
    var street:String? = null
    var other:String? = null
    constructor()

    constructor(street: String?, other: String?, city: String?, state: String?, country: String?) {
        this.street = street
        this.other = other
        this.city = city
        this.state = state
        this.country = country
    }

    var city:String? = null
    var state:String? = null
    var country:String? = null

}

class SocialAccounts: Serializable {
    var instagram:String? = null
    var twitter:String? = null
    var facebook:String? = null
    var whatsapp:String? = null
    var other:String? = null

    constructor()
    constructor(instagram: String?, twitter: String?, facebook: String?, whatsapp: String?, other: String?) {
        this.instagram = instagram
        this.twitter = twitter
        this.facebook = facebook
        this.whatsapp = whatsapp
        this.other = other
    }
}

class OwnerProfile: Serializable {
    var name:String? = null
    var email:String? = null

    constructor(name: String?, email: String?, phone_number: String?, address: Address?) {
        this.name = name
        this.email = email
        this.phone_number = phone_number
        this.address = address
    }
    constructor()
    var phone_number:String? = null
    var address:Address?= null
}

class BusinessProfile: Serializable {
    var name:String = ""
    var description:String = ""



    var category:String = ""
    var tags:ArrayList<String>? = null
    var contact_information:ContactInformation? = null
    var opening_hours:ArrayList<OpeningHours>? = null
    constructor()
    constructor(name: String, description: String, category: String, tags: ArrayList<String>?, contact_information: ContactInformation?, opening_hours: ArrayList<OpeningHours>?) {
        this.name = name
        this.description = description
        this.category = category
        this.tags = tags
        this.contact_information = contact_information
        this.opening_hours = opening_hours
    }
}

class User: Serializable {
    var business_profile:BusinessProfile? = null
    var type:String? = null

    constructor(credentials: Credentials, business_profile: BusinessProfile?, type: String?, products: ArrayList<Product>?, owner_profile: OwnerProfile?) {
        this.business_profile = business_profile
        this.type = type
        this.products = products
        this.owner_profile = owner_profile
        this.credentials = credentials
    }

    var products:ArrayList<Product>? = null
    var owner_profile:OwnerProfile? = null
    var credentials:Credentials? = null
}

class PhotoItem: Serializable {
    var name: String = ""
    var image_bitmap: Bitmap? = null
    var image_url: String? = null

    constructor(name: String, imageBitmap: Bitmap?, imageURL: String?){
        this.name = name
        this.image_bitmap = imageBitmap
        this.image_url = imageURL
    }

    constructor()
}


class Product: Serializable {
    var product_id:String = ""
    var owner_id:String = ""
    var name:String = ""
    var description:String = ""
    var price: Int = -1
    var quantity:Int = -1
    var highlights:ArrayList<String>? = null
    var photos:ArrayList<PhotoItem>? = null
    var available:Boolean = false

    constructor(){

    }

    constructor(name:String, description:String, price:Int, quantity:Int, highlights:ArrayList<String>, photos:ArrayList<PhotoItem>, available:Boolean){
        this.name = name
        this.description = description
        this.price = price
        this.quantity = quantity
        this.highlights = highlights
        this.photos = photos
        this.available = available
    }

    constructor(name:String, description:String, price:Int, quantity:Int, highlights:ArrayList<String>,available:Boolean){
        this.name = name
        this.description = description
        this.price = price
        this.quantity = quantity
        this.highlights = highlights
        this.available = available
    }

    constructor(name:String, description:String, price:Int,  photos:ArrayList<PhotoItem>, quantity:Int,available:Boolean){
        this.name = name
        this.description = description
        this.price = price
        this.quantity = quantity
        this.photos = photos
        this.available = available
    }

    constructor(name:String, description:String, price:Int, quantity:Int,available:Boolean){
        this.name = name
        this.description = description
        this.price = price
        this.quantity = quantity
        this.available = available
    }


    fun toMap(): MutableMap <String, Any> {
        val result = mutableMapOf<String, Any>()
        result["name"] = this.name
        result["description"] = this.description
        result["price"] = this.price
        result["quantity"] = this.quantity
        result["product_id"] = this.product_id
        result["owner_id"] = this.owner_id

        if(this.photos != null){
            result["photos"] = this.photos!!
        }
        if(this.highlights != null){
            result["highlights"] = this.highlights!!
        }
        result["available"] = this.available

        return result
    }

    fun copy(): Product {

        return Product(name, description, price, quantity, highlights!!, photos!!, available)

    }
}


// Class to download an image from url and display it into an image view
internal class DownLoadImageTask(internal val imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
    override fun doInBackground(vararg urls: String): Bitmap? {
        val urlOfImage = urls[0]
        return try {
            val inputStream = URL(urlOfImage).openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) { // Catch the download exception
            e.printStackTrace()
            null
        }
    }
    override fun onPostExecute(result: Bitmap?) {
        if(result!=null){
            // Display the downloaded image into image view
            Toast.makeText(imageView.context,"download success", Toast.LENGTH_SHORT).show()
            imageView.setImageBitmap(result)
        }else{
            Toast.makeText(imageView.context,"Error downloading", Toast.LENGTH_SHORT).show()
        }
    }
}

