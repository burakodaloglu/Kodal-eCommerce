package com.burakkodaloglu.my_e_commerce_app.data.repository

import com.burakkodaloglu.my_e_commerce_app.data.model.CRUD
import com.burakkodaloglu.my_e_commerce_app.data.model.CategoryBody
import com.burakkodaloglu.my_e_commerce_app.data.model.LoginBody
import com.burakkodaloglu.my_e_commerce_app.data.model.LoginResponse
import com.burakkodaloglu.my_e_commerce_app.data.model.Product
import com.burakkodaloglu.my_e_commerce_app.data.model.ProductBody
import com.burakkodaloglu.my_e_commerce_app.data.model.SignupBody
import com.burakkodaloglu.my_e_commerce_app.data.model.UserResponse
import com.burakkodaloglu.my_e_commerce_app.data.source.local.ProductDao
import com.burakkodaloglu.my_e_commerce_app.data.source.remote.ProductService
import com.burakkodaloglu.my_e_commerce_app.domain.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productDao: ProductDao
) : ProductRepository {
    override suspend fun getFavoriteProduct(): List<Product> = productDao.getFavoriteProduct()

    override suspend fun deleteProductFavorites(productEntity: Product) =
        productDao.deleteFavoriteProduct(productEntity)

    override suspend fun addToFavorite(productEntity: Product) =
        productDao.addFavoriteProduct(productEntity)

    override suspend fun getUser(userId: String): AppResult<UserResponse> =
        productService.getUser(userId)

    override suspend fun getProduct(): AppResult<ProductBody> = productService.getProduct()


    /*
    Bu fonksiyon, productService üzerinden getCategory(category)
    fonksiyonunu çağırmaktadır ve döndürdüğü değeri doğrudan geri döndürmektedir.
    category parametresi, getCategory() fonksiyonuna iletilmektedir.
     */
    override suspend fun getCategory(category: String): AppResult<ProductBody> =
        productService.getCategoryProduct(category)

    override suspend fun getCategoryList(): AppResult<CategoryBody> =
        productService.getCategoryList()

    override suspend fun getSaleProduct(): AppResult<ProductBody> = productService.getSaleProduct()

    override suspend fun login(loginBody: LoginBody): AppResult<LoginResponse> =
        productService.login(loginBody)

    override suspend fun signup(signUpBody: SignupBody): AppResult<CRUD> =
        productService.register(signUpBody)

}