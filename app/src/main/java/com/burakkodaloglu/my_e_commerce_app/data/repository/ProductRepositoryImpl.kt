package com.burakkodaloglu.my_e_commerce_app.data.repository

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
import com.burakkodaloglu.my_e_commerce_app.data.source.local.ProductDao
import com.burakkodaloglu.my_e_commerce_app.data.source.remote.ProductService
import com.burakkodaloglu.my_e_commerce_app.util.common.AppResult
import com.burakkodaloglu.my_e_commerce_app.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService,
    private val productDao: ProductDao
) : ProductRepository {

    override suspend fun clearCart(clearCart: ClearCart): AppResult<CRUD> =
        productService.clearCart(clearCart)

    override suspend fun deleteProductCart(deleteCartRequest: DeleteCartRequest): AppResult<CRUD> =
        productService.deleteProductCart(deleteCartRequest)

    override suspend fun addToCart(addToCart: AddToCart): AppResult<CRUD> =
        productService.addToCart(addToCart)

    override suspend fun getCartProducts(userId: String): AppResult<ProductResponse> =
        productService.getCartProducts(userId)

    override suspend fun getFavoriteProduct(): List<Product> = productDao.getFavoriteProduct()

    override suspend fun deleteProductFavorites(productEntity: Product) =
        productDao.deleteFavoriteProduct(productEntity)

    override suspend fun addToFavorite(productEntity: Product) =
        productDao.addFavoriteProduct(productEntity)

    override suspend fun getUser(userId: String): AppResult<UserResponse> =
        productService.getUser(userId)

    override suspend fun getProducts(): AppResult<ProductResponse> = productService.getProducts()
    override suspend fun searchProduct(query: String): AppResult<ProductResponse> =
        productService.searchProduct(query)

    /*
    Bu fonksiyon, productService üzerinden getCategory(category)
    fonksiyonunu çağırmaktadır ve döndürdüğü değeri doğrudan geri döndürmektedir.
    category parametresi, getCategory() fonksiyonuna iletilmektedir.
     */
    override suspend fun getCategory(category: String): AppResult<ProductResponse> =
        productService.getCategoryProduct(category)

    override suspend fun getCategoryList(): AppResult<CategoryResponse> =
        productService.getCategoryList()

    override suspend fun getSaleProduct(): AppResult<ProductResponse> =
        productService.getSaleProduct()

    override suspend fun login(login: Login): AppResult<LoginResponse> =
        productService.login(login)

    override suspend fun signup(signUp: Signup): AppResult<CRUD> =
        productService.register(signUp)

}