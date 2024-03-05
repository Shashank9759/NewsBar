package com.example.newsbar

import MySingleton
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.android.volley.Request
import com.android.volley.Response

import com.android.volley.toolbox.JsonObjectRequest



class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: myadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.TvrecyclerView)
        supportActionBar?.hide()

        setRecyclerview()
    }

    fun setRecyclerview() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        alldataput()
        mAdapter = myadapter(this)


        recyclerView.adapter = mAdapter
//        mAdapter.setonItemClickListner(object : myadapter.onItemclicklistner {
//            override fun onItemClicking(position: Int) {
//
//            }
//        })
    }

    fun alldataput(){

        val newsArr = ArrayList<DataView>()
        val url = "https://newsapi.org/v2/top-headlines?country=in&apiKey=214722558fee474b817376ffeba03504"

        val jsconrequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->

                val newsjsonArr = response.getJSONArray("articles")

                for (i in 0 until newsjsonArr.length()) {
                    val newsJsonObject = newsjsonArr.getJSONObject(i)
                    val news = DataView(
                        newsJsonObject.getString("urlToImage"),
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("description"),
                        newsJsonObject.getString("url")


                    )
                    newsArr.add(news)
                }
                mAdapter.updateNews(newsArr)

            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Something went wrong ", Toast.LENGTH_SHORT).show()
                Log.e("@@@@",error.toString())

            })
        MySingleton.getInstance(this).addToRequestQueue(jsconrequest)
    }





    }

