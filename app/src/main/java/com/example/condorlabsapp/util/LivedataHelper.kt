package com.example.condorlabsapp.util

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.condorlabsapp.business.domain.state.State
import kotlinx.android.synthetic.main.activity_main.*

fun <T> Fragment.observe(liveData: LiveData<State<T>>, success: (T) -> Unit) {

    liveData.observe(this.viewLifecycleOwner, { state ->
        when (state) {

            is State.Loading -> {
                Log.i("subscribeObservers", "Loading: $state")
                this.activity?.progress_circular?.visibility = View.VISIBLE
            }

            is State.Success -> {
                Log.i("subscribeObservers", "Success: $state")
                success(state.data)
                this.activity?.progress_circular?.visibility = View.GONE
            }

            is State.Failed -> {
                Log.i("subscribeObservers", "Failed: $state")
                this.activity?.progress_circular?.visibility = View.GONE
            }
        }
    })
}

fun <T> Fragment.observeAndPreventsHandleEventAgain(
    liveData: LiveData<State<T>>,
    success: (T) -> Unit
) {

    liveData.observe(this.viewLifecycleOwner, Observer { state ->
        when (state) {
            is State.Loading -> {
                Log.i("subscribeObservers", "Loading: $state")
                this.activity?.progress_circular?.visibility = View.VISIBLE
            }

            is State.Success -> {
                Log.i("subscribeObservers", "Success: $state")
                state.getContentIfNotHandled()?.let {
                    success(state.data)
                    this.activity?.progress_circular?.visibility = View.GONE
                }
            }

            is State.Failed -> {
                Log.i("subscribeObservers", "Failed: $state")
                this.activity?.progress_circular?.visibility = View.GONE
            }
        }
    })
}
