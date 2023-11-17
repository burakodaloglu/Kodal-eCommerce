package com.burakkodaloglu.my_e_commerce_app.util.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.downloadFromUrl(url: String) {
    Picasso.get()
        .load(url)
        .into(this)
}