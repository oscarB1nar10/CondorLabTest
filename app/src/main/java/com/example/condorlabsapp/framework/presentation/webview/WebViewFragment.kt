package com.example.condorlabsapp.framework.presentation.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.condorlabsapp.R
import com.example.condorlabsapp.util.NavigationArguments.WEB_SIDE
import com.example.condorlabsapp.util.StringFormat.getWebSideWithFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_webview.*
import kotlinx.android.synthetic.main.layout_toolbar_component.*


@AndroidEntryPoint
class WebViewFragment : Fragment(R.layout.fragment_webview) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupWebView()
    }


    private fun setupToolbar() {
        activity?.let { context ->
            context.toolbar_title.text = getString(R.string.web_view_fragment_toolbar_title)
            context.icon_toolbar_left.visibility = View.VISIBLE
            context.icon_toolbar_left.setOnClickListener {
                activity?.onBackPressed()
                context.icon_toolbar_left.visibility = View.GONE
            }
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        activity?.progress_circular?.visibility = View.VISIBLE

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                activity?.progress_circular?.visibility = View.GONE
            }

            override fun onPageCommitVisible(view: WebView?, url: String?) {
                super.onPageCommitVisible(view, url)
                activity?.progress_circular?.visibility = View.GONE
            }
        }

        arguments?.getString(WEB_SIDE)?.let { url ->
            webView.loadUrl(getWebSideWithFormat(url))
        }

    }
}
