package com.example.digikala.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.digikala.R
import com.example.digikala.data.model.domain.Products

/**
 * This class is adapter for product RecyclerView
 */
class ProductAdapter(var dataList: List<Products>, var context: Context) :
    RecyclerView.Adapter<ProductAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        Glide.with(context).load(dataList[position].images?.get(0)?.src).into(holder.imageView)
        holder.productName.text = dataList[position].name.toString()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: AppCompatImageView = itemView.findViewById(R.id.imageView)
        var productName: AppCompatTextView = itemView.findViewById(R.id.product_name)
    }
}
