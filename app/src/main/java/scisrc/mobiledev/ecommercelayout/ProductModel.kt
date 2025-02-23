package scisrc.mobiledev.ecommercelayout

data class ProductModel(
    val name: String,
    val description: String,
    val price: Double,
    val imageResId: Int,
    var isFavorite: Boolean = false // ✅ เพิ่มค่า Default เป็น false
)
