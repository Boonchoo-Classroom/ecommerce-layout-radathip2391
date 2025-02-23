package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import scisrc.mobiledev.ecommercelayout.CartManager
import scisrc.mobiledev.ecommercelayout.ProductModel
import scisrc.mobiledev.ecommercelayout.R

class CartFragment : Fragment() {

    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var emptyTextView: TextView
    private lateinit var totalPriceTextView: TextView
    private lateinit var checkoutButton: Button
    private lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        cartRecyclerView = view.findViewById(R.id.cartRecyclerView)
        emptyTextView = view.findViewById(R.id.emptyTextView)
        totalPriceTextView = view.findViewById(R.id.txt_total_price)
        checkoutButton = view.findViewById(R.id.btn_checkout)

        setupRecyclerView()
        updateTotalPrice()

        checkoutButton.setOnClickListener {
            if (CartManager.getCartItems().isNotEmpty()) {
                Toast.makeText(requireContext(), "สั่งซื้อสินค้าเรียบร้อย!", Toast.LENGTH_SHORT).show()
                CartManager.clearCart()
                updateCart()
            } else {
                Toast.makeText(requireContext(), "ตะกร้าว่างเปล่า", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun setupRecyclerView() {
        val cartItems = CartManager.getCartItems()

        if (cartItems.isEmpty()) {
            emptyTextView.visibility = View.VISIBLE
            cartRecyclerView.visibility = View.GONE
        } else {
            emptyTextView.visibility = View.GONE
            cartRecyclerView.visibility = View.VISIBLE
        }

        cartAdapter = CartAdapter(cartItems) { product ->
            CartManager.removeFromCart(product)
            updateCart()
            Toast.makeText(requireContext(), "${product.name} ถูกลบออกจากตะกร้า", Toast.LENGTH_SHORT).show()
        }
        cartRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        cartRecyclerView.adapter = cartAdapter
    }

    private fun updateCart() {
        cartAdapter.notifyDataSetChanged()
        val cartItems = CartManager.getCartItems()

        if (cartItems.isEmpty()) {
            emptyTextView.visibility = View.VISIBLE
            cartRecyclerView.visibility = View.GONE
        } else {
            emptyTextView.visibility = View.GONE
            cartRecyclerView.visibility = View.VISIBLE
        }

        updateTotalPrice()
    }

    private fun updateTotalPrice() {
        val total = CartManager.getCartItems().sumOf { it.price }
        totalPriceTextView.text = "ราคารวม: $ %.2f".format(total)
    }
}
