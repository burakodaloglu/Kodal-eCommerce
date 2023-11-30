package com.burakkodaloglu.my_e_commerce_app.domain.repository

import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.data.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.data.model.CategoryBody
import com.burakkodaloglu.my_e_commerce_app.data.model.LoginBody
import com.burakkodaloglu.my_e_commerce_app.data.model.LoginResponse
import com.burakkodaloglu.my_e_commerce_app.data.model.Product
import com.burakkodaloglu.my_e_commerce_app.data.model.ProductBody
import com.burakkodaloglu.my_e_commerce_app.data.model.SignupBody
import com.burakkodaloglu.my_e_commerce_app.data.model.UserResponse

interface ProductRepository {
    suspend fun getFavoriteProduct(): List<Product>
    suspend fun deleteProductFavorites(productEntity: Product)
    suspend fun addToFavorite(productEntity: Product)

    suspend fun getProduct(): AppResult<ProductBody>

    /*
     Bu fonksiyon, belirli bir kategoriye ait ürünleri almak için kullanılır.
     category parametresi, istenen kategoriyi belirtmek için kullanılır.
     Döndürdüğü değer, yine AppResult sınıfının bir örneği olan ProductBody tipindedir.
     */
    suspend fun getCategory(category: String): AppResult<ProductBody>
    suspend fun getCategoryList(): AppResult<CategoryBody>
    suspend fun getSaleProduct(): AppResult<ProductBody>
    suspend fun login(loginBody: LoginBody): AppResult<LoginResponse>
    suspend fun signup(signUpBody: SignupBody): AppResult<CRUD>
    suspend fun getUser(userId: String): AppResult<UserResponse>

}