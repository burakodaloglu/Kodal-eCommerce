package com.burakkodaloglu.my_e_commerce_app.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.burakkodaloglu.my_e_commerce_app.databinding.ItemProductsBinding
import com.burakkodaloglu.my_e_commerce_app.data.model.Product
import com.burakkodaloglu.my_e_commerce_app.ui.home.HomeFragmentDirections

class CategoryProductsAdapter : RecyclerView.Adapter<CategoryProductsAdapter.ProductsViewHolder>() {

    var onProductClick: (Product) -> Unit = {}
    private val list = ArrayList<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder =
        ProductsViewHolder(
            ItemProductsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = list[position]
        holder.bind(product)
    }
    inner class ProductsViewHolder(private var binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            binding.product = item
            binding.cardView.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(item)
                it.findNavController().navigate(action)
            }

        }
    }

    override fun getItemCount() = list.size

    fun updateList(updatedList: List<Product>) {
        list.clear()
        list.addAll(updatedList)
        notifyDataSetChanged()    }
}