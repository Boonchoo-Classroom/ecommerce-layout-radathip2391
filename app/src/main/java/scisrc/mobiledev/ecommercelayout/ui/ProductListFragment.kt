package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import scisrc.mobiledev.ecommercelayout.CartManager
import scisrc.mobiledev.ecommercelayout.FavoritesManager
import scisrc.mobiledev.ecommercelayout.ProductModel
import scisrc.mobiledev.ecommercelayout.R

class ProductListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val productList = mutableListOf<ProductModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_product_list, container, false)

        recyclerView = view.findViewById(R.id.recycler_product_list)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // ✅ Mock Data สำหรับแสดงผลสินค้า
        productList.apply {
            add(ProductModel("ASUS DUAL GEFORCE RTX", "4060 ram 8 gb", 11800.00, R.drawable.ic_pen))
            add(ProductModel("Monitor ACER XV270U Z1BMIIPRX", "ขนาด 27 นิ้ว 270 Hz", 8400.00, R.drawable.ic_notebook))
            add(ProductModel("Cpu Amd Ryzen 7 5700", "8 Cores 16 Threads", 5490.00, R.drawable.ic_pencil))
            add(ProductModel("CORSAIR VENGEANCE RGB PRO SL (WHITE)", "DDR4 3200MHz 64GB", 5690.00, R.drawable.ic_eraser))
            add(ProductModel("MOUSERAZER VIPER 8KHz", "Razer Chroma RGB Up to 20K DPI Focus+ Optical Sensor", 1690.00, R.drawable.ic_ruler))
            add(ProductModel("KEYBOARDRAZER HUNTSMAN V3 PRO MINI (BLACK) ", "Chroma RGB Wired (Detachable USB-C to USB-A)", 6290.00, R.drawable.ic_pencil_case))
        }

        // ✅ ตั้งค่า Adapter พร้อม Callback
        adapter = ProductAdapter(productList) { product ->
            toggleFavorite(product)
        }
        recyclerView.adapter = adapter

        return view
    }

    // ✅ ฟังก์ชันจัดการการกดหัวใจ
    private fun toggleFavorite(product: ProductModel) {
        product.isFavorite = !product.isFavorite
        if (product.isFavorite) {
            FavoritesManager.addToFavorites(product) // ✅ เพิ่มสินค้าใน Favorites
        } else {
            FavoritesManager.removeFromFavorites(product) // ✅ ลบออกจาก Favorites
        }
        adapter.notifyDataSetChanged()
    }
}
