package com.haneet.assignment.ui.list.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil

import com.haneet.assignment.R
import com.haneet.assignment.base.BaseClass
import com.haneet.assignment.base.BaseFragment
import com.haneet.assignment.constant.Task
import com.haneet.assignment.data.data_model.LocationTable
import com.haneet.assignment.databinding.ActivityDateLoginBinding
import com.haneet.assignment.databinding.FragmentBlankThreeBinding
import com.haneet.assignment.databinding.FragmentBlankTwoBinding
import com.haneet.assignment.domain.DataState
import com.haneet.assignment.ui.MainActivity
import com.haneet.assignment.ui.list.ListActivityViewModel
import com.haneet.assignment.ui.list.MainListStateEvent
import com.haneet.assignment.ui.list.adapter.ListAdapter
import com.haneet.assignment.ui.location.LocationFetchFromMapActivity
import com.haneet.assignment.utils.ListDiffCallback

class HelpFragment : BaseFragment() {


    private val viewModel by viewModels<ListActivityViewModel>()
    lateinit var binding: FragmentBlankThreeBinding


    val MAX_PROGRESS = 100
    private lateinit var pageUrl: String
    override fun attachViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_blank_three,
            container,
            false
        )

        val pageUrl = "https://github.com/HaneetGH/WeatherWithMvi/blob/master/README.md"
        initWebView()
        setWebClient()
        handlePullToRefresh()
        loadUrl(pageUrl)



        return binding.root
    }


    private fun handlePullToRefresh() {
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.settings.loadWithOverviewMode = true
        binding.webview.settings.useWideViewPort = true
        binding.webview.settings.domStorageEnabled = true
        binding.webview.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }
    }

    private fun setWebClient() {
        binding.webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
                view: WebView,
                newProgress: Int
            ) {
                super.onProgressChanged(view, newProgress)
                binding.progressCircular.progress = newProgress
                if (newProgress < MAX_PROGRESS && binding.progressCircular.visibility == View.GONE) {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                if (newProgress == MAX_PROGRESS) {
                    binding.progressCircular.visibility = View.GONE
                }
            }
        }
    }


    private fun loadUrl(pageUrl: String) {
        binding.webview.loadUrl(pageUrl)
    }
}





