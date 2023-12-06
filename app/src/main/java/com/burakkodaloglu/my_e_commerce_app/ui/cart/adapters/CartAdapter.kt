package com.burakkodaloglu.my_e_commerce_app.ui.cart.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burakkodaloglu.my_e_commerce_app.data.model.Product
import com.burakkodaloglu.my_e_commerce_app.databinding.ItemCartProductsBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartProductsViewHolder>() {

    private val list = ArrayList<Product>()

    var onIncreaseClick: (Double) -> Unit = {}
    var onDecreaseClick: (Double) -> Unit = {}
    var onDeleteClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductsViewHolder =
        CartProductsViewHolder(
            ItemCartProductsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CartProductsViewHolder, position: Int) =
        holder.bind(list[position])

    inner class CartProductsViewHolder(private var binding: ItemCartProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            with(binding) {

                var productCount = 1

                cart = item

                if (productCount == 1) {
                    count = productCount.toString()
                }
                imgIncrease.setOnClickListener {
                    onIncreaseClick(item.salePrice)
                    productCount++
                    count = productCount.toString()
                }
                imgDecrease.setOnClickListener {
                    if (productCount != 1) {
                        onDecreaseClick(item.salePrice)
                        productCount--
                        count = productCount.toString()
                    } else {
                        onDeleteClick(item.id)
                    }
                }
                imgDelete.setOnClickListener { onDeleteClick(item.id) }
            }
        }
    }

    override fun getItemCount() = list.size

    fun updateList(updatedList: List<Product>) {
        list.clear()
        list.addAll(updatedList)
        notifyDataSetChanged()

    }
}