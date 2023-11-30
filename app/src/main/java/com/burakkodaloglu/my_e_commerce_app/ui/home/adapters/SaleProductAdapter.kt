package com.burakkodaloglu.my_e_commerce_app.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burakkodaloglu.my_e_commerce_app.R
import com.burakkodaloglu.my_e_commerce_app.databinding.ItemSaleProductBinding
import com.burakkodaloglu.my_e_commerce_app.data.model.Product

class SaleProductsAdapter : RecyclerView.Adapter<SaleProductsAdapter.ProductsViewHolder>() {

    private val list = ArrayList<Product>()

    var onProductClick: (Product) -> Unit = {}
    var onFavoriteClick: (Product) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder =
        ProductsViewHolder(
            ItemSaleProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) =
        holder.bind(list[position])

    inner class ProductsViewHolder(private var binding: ItemSaleProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            with(binding) {
                saleProduct = item
                imgFavorite.setOnClickListener {
                    if (item.isFavorite) {
                        onFavoriteClick(item)
                        imgFavorite.setImageResource(R.drawable.ic_favorite_unselected)
                    } else {
                        onFavoriteClick(item)
                        imgFavorite.setImageResource(R.drawable.ic_favorite_selected)
                    }
                }

                cardView.setOnClickListener {
                    onProductClick(item)
                }
            }

        }
    }

    override fun getItemCount() = list.size

    fun updateList(updatedList: List<Product>) {
        list.clear()
        list.addAll(updatedList)
        notifyItemRangeInserted(0, updatedList.size)
    }
}