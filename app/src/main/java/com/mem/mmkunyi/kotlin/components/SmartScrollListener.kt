package com.mem.mmkunyi.kotlin.components

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class SmartScrollListener (private val mSmartScrollListener: OnSmartScrollListener ) : RecyclerView.OnScrollListener() {

    private var isListEndReached = false

    interface OnSmartScrollListener {
        fun onListEndReach()
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = recyclerView!!.layoutManager.childCount
        val totalItemCount = recyclerView.layoutManager.itemCount
        val pastVisibleItems = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (visibleItemCount + pastVisibleItems < totalItemCount) {
            isListEndReached = false
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_IDLE
                && (recyclerView!!.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == recyclerView.adapter.itemCount - 1
                && !isListEndReached) {
            isListEndReached = true
            mSmartScrollListener.onListEndReach()
        }
    }
}