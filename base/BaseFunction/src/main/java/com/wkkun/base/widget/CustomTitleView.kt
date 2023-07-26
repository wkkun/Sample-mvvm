package com.wkkun.base.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.wkkun.base.R
import com.wkkun.base.databinding.ItemCustomTitleBinding

/**
 * 通用的标题头 包含左侧返回 和中间部分的标题
 */
class CustomTitleView(context: Context, attributeSet: AttributeSet? = null) :
    FrameLayout(context, attributeSet) {
    private val binding: ItemCustomTitleBinding
    private val defaultHeight: Int =
        context.resources.getDimensionPixelOffset(com.wkkun.screensupport.R.dimen.dp_44)

    init {
        binding = ItemCustomTitleBinding.inflate(LayoutInflater.from(context), this, true)
        val obtainStyledAttributes =
            context.obtainStyledAttributes(attributeSet, R.styleable.CustomTitleView)
        configAttr(obtainStyledAttributes)
        obtainStyledAttributes.recycle()
    }

    private fun configAttr(attributes: TypedArray) {
        binding.tvTitle.text = attributes.getString(R.styleable.CustomTitleView_title)
        if (attributes.getBoolean(R.styleable.CustomTitleView_showCloseIcon, false)) {
            binding.ivBack.visibility = View.VISIBLE
            binding.ivBack.setOnClickListener {

            }
        }
        if (attributes.getBoolean(R.styleable.CustomTitleView_showDivider, false)) {
            binding.dividingTop.visibility = View.VISIBLE
            binding.dividingTop.setBackgroundColor(
                attributes.getColor(
                    R.styleable.CustomTitleView_dividerColor,
                    ContextCompat.getColor(context, R.color.black)
                )
            )
        }


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val mode = MeasureSpec.getMode(heightMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        var heightSpec = heightMeasureSpec
        if (mode == MeasureSpec.AT_MOST) {
            heightSpec =
                MeasureSpec.makeMeasureSpec(defaultHeight.coerceAtMost(height), MeasureSpec.EXACTLY)
        } else if (mode == MeasureSpec.UNSPECIFIED) {
            heightSpec = MeasureSpec.makeMeasureSpec(defaultHeight, MeasureSpec.EXACTLY)
        }
        super.onMeasure(widthMeasureSpec, heightSpec)
    }
}