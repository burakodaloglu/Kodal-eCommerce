package com.burakkodaloglu.my_e_commerce_app.domain.repository

import com.burakkodaloglu.my_e_commerce_app.data.model.AddToCart
import com.burakkodaloglu.my_e_commerce_app.data.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.data.model.CategoryResponse
import com.burakkodaloglu.my_e_commerce_app.data.model.ClearCart
import com.burakkodaloglu.my_e_commerce_app.data.model.DeleteCartRequest
import com.burakkodaloglu.my_e_commerce_app.data.model.Login
import com.burakkodaloglu.my_e_commerce_app.data.model.LoginResponse
import com.burakkodaloglu.my_e_commerce_app.data.model.Product
import com.burakkodaloglu.my_e_commerce_app.data.model.ProductResponse
import com.burakkodaloglu.my_e_commerce_app.data.model.Signup
import com.burakkodaloglu.my_e_commerce_app.data.model.UserResponse
import com.burakkodaloglu.my_e_commerce_app.domain.AppResult

interface ProductRepository {
    suspend fun clearCart(clearCart: ClearCart): AppResult<CRUD>
    suspend fun deleteProductCart(deleteCartRequest: DeleteCartRequest): AppResult<CRUD>
    suspend fun addToCart(addToCart: AddToCart): AppResult<CRUD>
    suspend fun getCartProducts(userId: String): AppResult<ProductResponse>
    suspend fun getFavoriteProduct(): List<Product>
    suspend fun deleteProductFavorites(productEntity: Product)
    suspend fun addToFavorite(productEntity: Product)
    suspend fun getProducts(): AppResult<ProductResponse>

    /*
     Bu fonksiyon, belirli bir kategoriye ait ürünleri almak için kullanılır.
     category parametresi, istenen kategoriyi belirtmek için kullanılır.
     Döndürdüğü değer, yine AppResult sınıfının bir örneği olan ProductBody tipindedir.
     */
    suspend fun searchProduct(query: String): AppResult<ProductResponse>
    suspend fun getCategory(category: String): AppResult<ProductResponse>
    suspend fun getCategoryList(): AppResult<CategoryResponse>
    suspend fun getSaleProduct(): AppResult<ProductResponse>
    suspend fun login(login: Login): AppResult<LoginResponse>
    suspend fun signup(signUp: Signup): AppResult<CRUD>
    suspend fun getUser(userId: String): AppResult<UserResponse>

}