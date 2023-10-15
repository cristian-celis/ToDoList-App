package com.example.todoproject.presentation.core

import android.view.View
import com.example.todoproject.databinding.FragmentMainBinding


fun FragmentMainBinding.listIsEmpty(){
    this.centerText.visibility = View.VISIBLE
    this.goBackBtt.visibility = View.VISIBLE
}

fun FragmentMainBinding.listIsNotEmpty(){
    this.centerText.visibility = View.INVISIBLE
    this.searchViewTxt.visibility = View.VISIBLE
}