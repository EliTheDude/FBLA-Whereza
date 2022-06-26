package com.fbla.whereza

//import android.content.Context
//import android.util.AttributeSet
//import android.util.Log
//import com.bumptech.glide.Glide
//
//class MyImageView : androidx.appcompat.widget.AppCompatImageView {
//
//    var url: String = ""
//        set(value){
//            Log.d("check", "yep")
//            field = value
//            Glide.with(this).load(value).into(this)
//        }
//
//    constructor(context: Context) : super(context)
//
//    constructor(context: Context, attrs : AttributeSet) : super(context,attrs){
//        val array = context.obtainStyledAttributes(attrs, R.styleable.MyImageView)
//        val url = array.getString(R.styleable.MyImageView_url)
//        Glide.with(this).load(url).into(this)
//        array.recycle()
//    }
//
//    constructor(context: Context,  attrs: AttributeSet , defStyleAttr : Int) : super(context, attrs, defStyleAttr)
//}