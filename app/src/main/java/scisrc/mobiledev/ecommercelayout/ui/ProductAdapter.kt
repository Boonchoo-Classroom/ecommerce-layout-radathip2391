package scisrc.mobiledev.ecommercelayout.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import scisrc.mobiledev.ecommercelayout.CartManager
import scisrc.mobiledev.ecommercelayout.FavoritesManager
import scisrc.mobiledev.ecommercelayout.ProductModel
import scisrc.mobiledev.ecommercelayout.R

class ProductAdapter(
    private val productList: MutableList<ProductModel>,
    private val onFavoriteToggle: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val name: TextView = itemView.findViewById(R.id.productName)
        val description: TextView = itemView.findViewById(R.id.product_description) // ✅ เพิ่ม description
        val price: TextView = itemView.findViewById(R.id.productPrice)
        val btnCart: Button = itemView.findViewById(R.id.btnAddToCart)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.favoriteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        holder.image.setImageResource(product.imageResId)
        holder.name.text = product.name
        holder.description.text = product.description // ✅ เซ็ตค่า description
        holder.price.text = "$ %.2f".format(product.price)

        holder.favoriteIcon.setImageResource(
            if (FavoritesManager.isFavorite(product)) R.drawable.ic_heart_filled
            else R.drawable.ic_heart_outline
        )

        holder.favoriteIcon.setOnClickListener {
            val context = holder.itemView.context
            if (FavoritesManager.isFavorite(product)) {
                FavoritesManager.removeFromFavorites(product)
                Toast.makeText(context, "${product.name} ถูกลบจากสิ่งที่สนใจ", Toast.LENGTH_SHORT).show()
            } else {
                FavoritesManager.addToFavorites(product)
                Toast.makeText(context, "${product.name} ถูกเพิ่มลงในสิ่งที่สนใจ", Toast.LENGTH_SHORT).show()
            }
            notifyItemChanged(position)  // รีเฟรช UI
            onFavoriteToggle(product)  // แจ้ง Fragment ให้รีเฟรช
        }

        holder.btnCart.setOnClickListener {
            CartManager.addToCart(product)
            Toast.makeText(holder.itemView.context, "${product.name} ถูกเพิ่มลงตะกร้า!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount() = productList.size
}
