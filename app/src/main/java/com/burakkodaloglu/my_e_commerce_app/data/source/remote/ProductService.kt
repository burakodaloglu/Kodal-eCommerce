package com.burakkodaloglu.my_e_commerce_app.data.source.remote

import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.domain.model.LoginBody
import com.burakkodaloglu.my_e_commerce_app.domain.model.LoginResponse
import com.burakkodaloglu.my_e_commerce_app.domain.model.ProductBody
import com.burakkodaloglu.my_e_commerce_app.domain.model.SignupBody
import com.burakkodaloglu.my_e_commerce_app.domain.model.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductService {
    @POST("sign_in")
    suspend fun login(
        @Body loginBody: LoginBody
    ): AppResult<LoginResponse>

    @POST("sign_up")
    suspend fun register(
        @Body signUpBody: SignupBody
    ): AppResult<CRUD>

    @GET("get_sale_products")
    suspend fun getSaleProduct(): AppResult<ProductBody>

    @GET("get_user")
    suspend fun getUser(
        @Query("userId") userId: String
    ): AppResult<UserResponse>
}