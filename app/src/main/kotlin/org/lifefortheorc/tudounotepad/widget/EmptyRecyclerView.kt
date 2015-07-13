package org.lifefortheorc.tudounotepad.widget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View

/**
 * Created by geminiwen on 15/7/13.
 */

public class EmptyRecyclerView: RecyclerView{

    constructor(ctx: Context): this(ctx, null) {

    }

    constructor(ctx: Context, attr: AttributeSet?): this(ctx, attr, 0) {
    }

    constructor(ctx: Context, attr: AttributeSet?, defStyle: Int): super(ctx, attr, defStyle) {

    }

    private var emptyView: View? = null

    fun checkIfEmpty() {
        if (emptyView != null && getAdapter() != null) {
            val emptyViewVisible = getAdapter().getItemCount() == 0
            emptyView?.setVisibility(if (emptyViewVisible) View.VISIBLE else View.GONE)
            setVisibility(if (emptyViewVisible) View.GONE else View.VISIBLE)
        }
    }

    private val observer = object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            checkIfEmpty()
        }
    }

    override  fun setAdapter(adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>?) {
        val oldAdapter = getAdapter();
        oldAdapter?.unregisterAdapterDataObserver(observer)
        super.setAdapter(adapter);
        adapter?.registerAdapterDataObserver(observer)

        checkIfEmpty();
    }

    public fun setEmptyView(emptyView: View) {
        this.emptyView = emptyView
        checkIfEmpty()
    }

}
