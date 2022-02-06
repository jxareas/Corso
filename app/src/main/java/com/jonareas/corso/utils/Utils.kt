package com.jonareas.corso.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline infix fun<reified T : ViewBinding> ViewGroup.help
            (crossinline inflater : (LayoutInflater, ViewGroup, Boolean) -> T) : T =
    inflater.invoke(LayoutInflater.from(context), this, false)