package com.superman.animate

import android.widget.TextView
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class TestClickAdapter(
    layoutResId: Int,
    data: MutableList<String>
) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId, data) {

    override fun convert(holder: BaseViewHolder, item: String) {
        LogUtils.w("item.text = $item")
        holder.getView<TextView>(R.id.btn_text).text = item
    }
}