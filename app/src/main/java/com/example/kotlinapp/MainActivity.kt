package com.example.kotlinapp

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinapp.model.User
import com.example.kotlinapp.model.UserRepos
import com.example.kotlinapp.utils.ResponseHelper
import com.example.kotlinapp.viewModel.UserViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewModel = ViewModelProviders.of(this@MainActivity).get(UserViewModel::class.java)

        search.setOnClickListener {

            viewModel.getUser(user_id.text.toString()).observe(this, Observer { userInfo: ResponseHelper<User>? ->
                userInfo?.data?.let { it -> loadAvatar(it) } ?: userInfo?.message?.let{showMassage(it)}
                viewModel.getRepos(user_id.text.toString()).observe(this, Observer { repos: ResponseHelper<List<UserRepos>>? ->
                    repos?.data?.let{loadData(it)}  ?: repos?.message?.let{showMassage(it)}
                });
            });
        }

    }

    fun showMassage(st:String){
        Toast.makeText(this@MainActivity, st, Toast.LENGTH_SHORT).show()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.let{
            val im = (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).apply {
                hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun loadData(repos:List<UserRepos>) {
        var userAdapter =  UserAdapter(repos, this@MainActivity)
        var manager = LinearLayoutManager(this@MainActivity)

        rec.layoutManager = manager
        rec.adapter = userAdapter
        rec.setHasFixedSize(false)
    }

    private fun loadAvatar(userInfo: User) {

        name.text = userInfo.name
        userInfo.avatar_url?.let {it ->
            Picasso.with(this)
                .load(it)
                .resize(200, 200)
                .centerCrop()
                .into(avatar)
        }


    }
}
