package com.rsschool.quiz.ui

import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toolbar

fun Button.unable(){
    this.isEnabled = false
}

fun Button.enabled(){
    this.isEnabled = true
}

fun RadioButton.isChosen(){
    this.isChecked = true
}

fun androidx.appcompat.widget.Toolbar.hideNavigation(){
    this.navigationIcon = null
}