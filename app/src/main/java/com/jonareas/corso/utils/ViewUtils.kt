package com.jonareas.corso.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jonareas.corso.R

// Utility functions for the View Layer of the App

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Fragment.toast(message : String, displayLength : Int = Toast.LENGTH_SHORT) : Unit =
    Toast.makeText(activity, message, displayLength).show()

fun RecyclerView.attachGoToTopButton(fab: FloatingActionButton) : Unit =
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) = with(fab) {
            if(dy > 0) visible() else gone()
        }
    }).also { fab.setOnClickListener { scrollToPosition(0) } }

inline infix fun<reified T : ViewBinding> ViewGroup.help
            (crossinline inflater : (LayoutInflater, ViewGroup, Boolean) -> T) : T =
    inflater.invoke(LayoutInflater.from(context), this, false)

fun View.navigateByAction(action : NavDirections) : Unit =
    findNavController().navigate(action)

fun Fragment.navigateByAction(action : NavDirections) : Unit =
    findNavController().navigate(action)

fun progressDrawable(context : Context) : CircularProgressDrawable =
    CircularProgressDrawable(context)
        .apply {
            strokeWidth = 10f
            centerRadius = 50f
            start()
        }

fun RecyclerView.attachLinearSnapHelper() : Unit =
    LinearSnapHelper().attachToRecyclerView(this)

fun Fragment.hideToolbar() : Unit =
    (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

fun Fragment.expandToolbar() : Unit =
    (activity as AppCompatActivity?)!!.supportActionBar!!.show()

fun ImageView.loadImage(uri : String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_corso)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}
