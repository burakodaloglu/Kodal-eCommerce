package com.burakkodaloglu.my_e_commerce_app.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.burakkodaloglu.my_e_commerce_app.databinding.ItemCategoryListBinding


class CategoryListAdapter() :
    RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder>() {
    private val onClick: (String) -> Unit = {}
    private val categoryList = ArrayList<String>()
    private var selectedPosition = 0

    inner class CategoryListViewHolder(private val binding: ItemCategoryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(categoryName: String) {
            with(binding) {
                categoryCheckFilter.text = categoryName
                categoryCheckFilter.setOnClickListener {
                    onClick.invoke(categoryName)
                    selectedPosition = layoutPosition
                    notifyItemRangeChanged(0, categoryList.size - 1)
                }
            }
            binding.categoryCheckFilter.isChecked = selectedPosition == layoutPosition
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        val binding =
            ItemCategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryListViewHolder(binding)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category)
    }

    fun updateCategoryName(updateCategoryNameList: List<String>) {
        categoryList.clear()
        categoryList.addAll(updateCategoryNameList)
        notifyDataSetChanged()
    }
}