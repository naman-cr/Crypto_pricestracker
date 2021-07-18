package com.example.newsfresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var mAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager=LinearLayoutManager(this)
        fetchData()
        mAdapter= NewsListAdapter(this)
        recyclerView.adapter=mAdapter
    }

    private fun fetchData(){
        val url = "https://api.coinstats.app/public/v1/coins?skip=0&currency=INR"
        val jsonObjectRequest= JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener{
               val newsJsonArray = it.getJSONArray("coins")
                val newsArray=ArrayList<News>()
                for(i in 0 until newsJsonArray.length()){
                    val newsJsonObject= newsJsonArray.getJSONObject(i)
                    val news=News(
                        newsJsonObject.getString("name"),
                        newsJsonObject.getString("symbol"),
                        newsJsonObject.getString("price"),
                        newsJsonObject.getString("priceChange1h"),
                        newsJsonObject.getString("icon"),
                    )
                    newsArray.add(news)
                }
                mAdapter.updateNews(newsArray)
            },
            Response.ErrorListener{

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News) {

    }
}