package scisrc.mobiledev.ecommercelayout.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import scisrc.mobiledev.ecommercelayout.ProductModel
import scisrc.mobiledev.ecommercelayout.R

class CartAdapter(
    private val cartList: List<ProductModel>,
    private val onRemoveClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.cartProductImage)
        val name: TextView = itemView.findViewById(R.id.cartProductName)
        val price: TextView = itemView.findViewById(R.id.cartProductPrice)
        val btnRemove: Button = itemView.findViewById(R.id.btnRemoveFromCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart_product, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartList[position]

        holder.image.setImageResource(product.imageResId)
        holder.name.text = product.name
        holder.price.text = "$ %.2f".format(product.price)

        holder.btnRemove.setOnClickListener {
            onRemoveClick(product)
        }
    }

    override fun getItemCount() = cartList.size
}
