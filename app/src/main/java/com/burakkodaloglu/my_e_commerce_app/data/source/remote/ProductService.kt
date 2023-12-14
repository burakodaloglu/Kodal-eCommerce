package com.burakkodaloglu.my_e_commerce_app.data.source.remote

import com.burakkodaloglu.my_e_commerce_app.data.model.AddToCart
import com.burakkodaloglu.my_e_commerce_app.util.common.AppResult
import com.burakkodaloglu.my_e_commerce_app.data.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.data.model.CategoryResponse
import com.burakkodaloglu.my_e_commerce_app.data.model.ClearCart
import com.burakkodaloglu.my_e_commerce_app.data.model.DeleteCartRequest
import com.burakkodaloglu.my_e_commerce_app.data.model.Login
import com.burakkodaloglu.my_e_commerce_app.data.model.LoginResponse
import com.burakkodaloglu.my_e_commerce_app.data.model.ProductResponse
import com.burakkodaloglu.my_e_commerce_app.data.model.Signup
import com.burakkodaloglu.my_e_commerce_app.data.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductService {
    @POST("clear_cart")
    suspend fun clearCart(
        @Body clearCart: ClearCart
    ): AppResult<CRUD>

    @GET("get_cart_products")
    suspend fun getCartProducts(
        @Query("userId") userId: String
    ): AppResult<ProductResponse>

    @POST("add_to_cart")
    suspend fun addToCart(
        @Body addToCart: AddToCart
    ): AppResult<CRUD>

    @POST("delete_from_cart")
    suspend fun deleteProductCart(
        @Body deleteCartRequest: DeleteCartRequest
    ): AppResult<CRUD>

    @GET("get_products")
    suspend fun getProducts(): AppResult<ProductResponse>

    @GET("get_products_by_category")
    suspend fun getCategoryProduct(
        @Query("category") category: String
    ): AppResult<ProductResponse>

    @GET("get_categories")
    suspend fun getCategoryList(): AppResult<CategoryResponse>

    @GET("search_product")
    suspend fun searchProduct(@Query("query") query: String): AppResult<ProductResponse>

    @POST("sign_in")
    suspend fun login(
        @Body login: Login
    ): AppResult<LoginResponse>

    @POST("sign_up")
    suspend fun register(
        @Body signUp: Signup
    ): AppResult<CRUD>

    @GET("get_sale_products")
    suspend fun getSaleProduct(): AppResult<ProductResponse>

    @GET("get_user")
    suspend fun getUser(
        @Query("userId") userId: String
    ): AppResult<UserResponse>
}