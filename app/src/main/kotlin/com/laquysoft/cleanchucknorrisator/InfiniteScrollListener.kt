package com.laquysoft.cleanchucknorrisator

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import com.laquysoft.cleanchucknorrisator.features.neverendingjokes.NeverEndingAdapter

/**
 * Created by joaobiriba on 06/11/2017.
 */

class InfiniteScrollListener(
        val func: () -> Unit,
        val layoutManager: LinearLayoutManager,
        val neverEndingAdapter: NeverEndingAdapter) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var loading = true
    private var visibleThreshold = 2
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                    neverEndingAdapter.showLoader = false

                }
            }
            if (!loading && (totalItemCount - visibleItemCount)
                    <= (firstVisibleItem + visibleThreshold)) {
                // End has been reached
                Log.i("InfiniteScrollListener", "End reached")
                func()
                loading = true
                neverEndingAdapter.showLoader = true
            }
        }
    }
}