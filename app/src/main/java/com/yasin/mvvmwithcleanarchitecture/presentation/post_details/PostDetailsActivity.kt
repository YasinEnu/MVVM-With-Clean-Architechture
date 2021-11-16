package com.yasin.mvvmwithcleanarchitecture.presentation.post_details

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.yasin.mvvmwithcleanarchitecture.R
import com.yasin.mvvmwithcleanarchitecture.common.UniversalRecyclerViewAdapter
import com.yasin.mvvmwithcleanarchitecture.data.remote.dto.PostDto
import com.yasin.mvvmwithcleanarchitecture.presentation.post_list.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_post_details.*
import kotlinx.android.synthetic.main.post_item_design.view.*

@AndroidEntryPoint
class PostDetailsActivity : AppCompatActivity() {

    private val viewModel: PostDetailsViewModel by viewModels()
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait...")
        progressDialog.setCancelable(false)

        viewModel.getPostSuccessfulData.observe(this, Observer {
            postTitleTV.text=it.title
            postBodyTV.text=it.body
        })
        viewModel.getPostFailedData.observe(this, Observer {
            Toast.makeText(this@PostDetailsActivity,it, Toast.LENGTH_LONG).show()
        })
        viewModel.progressBarLiveData.observe(this, Observer {
            if (it) {
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        })
        viewModel.getSinglePosts(intent.getStringExtra("post_id").toString())
    }
}