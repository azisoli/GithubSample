package com.example.kotlinapp.utils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.kotlinapp.R
import kotlinx.android.synthetic.main.custom_snack.view.*

import com.google.android.material.snackbar.BaseTransientBottomBar

class CustomSnackBar (
    parent: ViewGroup,
    content: CustomSnackBarView
) : BaseTransientBottomBar<CustomSnackBar>(parent, content, content) {

    init {
        getView().setBackgroundColor(ContextCompat.getColor(view.context, android.R.color.transparent))
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {

        fun make(view: View, update:String, fork:Int, star:Int): CustomSnackBar {

            // First we find a suitable parent for our custom view
            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No parent."
            )

            val customView = LayoutInflater.from(view.context).inflate(
                R.layout.layout_snack,
                parent,
                false
            ) as CustomSnackBarView
            customView.forks.text = fork.toString()
            customView.star.text = star.toString()
            customView.update.text = update.toString()
            return CustomSnackBar(
                parent,
                customView
            )
        }

    }

}

