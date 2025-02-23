package scisrc.mobiledev.ecommercelayout

object FavoritesManager {
    private val favoriteList = mutableListOf<ProductModel>()

    fun addToFavorites(product: ProductModel) {
        if (!favoriteList.contains(product)) {
            favoriteList.add(product)
        }
    }

    fun removeFromFavorites(product: ProductModel) {
        favoriteList.remove(product)
    }

    fun getFavorites(): List<ProductModel> {
        return favoriteList
    }

    fun isFavorite(product: ProductModel): Boolean {
        return favoriteList.contains(product)
    }
}
