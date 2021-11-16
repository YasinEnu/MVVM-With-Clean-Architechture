package com.yasin.mvvmwithcleanarchitecture.presentation.post_list

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yasin.mvvmwithcleanarchitecture.R
import com.yasin.mvvmwithcleanarchitecture.common.UniversalRecyclerViewAdapter
import com.yasin.mvvmwithcleanarchitecture.data.remote.dto.PostDto
import com.yasin.mvvmwithcleanarchitecture.presentation.post_details.PostDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post_item_design.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait...")
        progressDialog.setCancelable(false)

        postListRV.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        postListRV.itemAnimator = DefaultItemAnimator()

        viewModel.getAllPostSuccessfulData.observe(this, Observer {
            postListRV.adapter=UniversalRecyclerViewAdapter(this@MainActivity,it,R.layout.post_item_design)
            {universalViewHolder, itemData, position ->
                universalViewHolder.itemView.postBodyTV.text=(itemData as PostDto).title
                universalViewHolder.itemView.setOnClickListener {
                    startActivity(Intent(this@MainActivity,PostDetailsActivity::class.java).putExtra("post_id",itemData.id.toString()))
                }
            }
        })
        viewModel.getAllPostFailedData.observe(this, Observer {
            Toast.makeText(this@MainActivity,it,Toast.LENGTH_LONG).show()
        })
        viewModel.progressBarLiveData.observe(this, Observer {
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        })

    }
}