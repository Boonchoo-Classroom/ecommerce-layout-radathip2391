package scisrc.mobiledev.ecommercelayout

object CartManager {
    private val cartItems = mutableListOf<ProductModel>()

    fun addToCart(product: ProductModel) {
        cartItems.add(product)
    }

    fun removeFromCart(product: ProductModel) {
        cartItems.remove(product)
    }

    fun getCartItems(): List<ProductModel> {
        return cartItems
    }

    // ✅ เพิ่มฟังก์ชันนี้เพื่อเคลียร์ตะกร้า
    fun clearCart() {
        cartItems.clear()
    }
}
