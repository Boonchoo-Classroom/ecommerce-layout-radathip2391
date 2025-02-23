package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import scisrc.mobiledev.ecommercelayout.FavoritesManager
import scisrc.mobiledev.ecommercelayout.ProductModel
import scisrc.mobiledev.ecommercelayout.R

class FavoritesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyTextView: TextView
    private lateinit var adapter: ProductAdapter
    private val favoriteList = mutableListOf<ProductModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        recyclerView = view.findViewById(R.id.recycler_favorites)
        emptyTextView = view.findViewById(R.id.emptyFavoritesText)

        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = ProductAdapter(favoriteList) { product ->
            removeFavorite(product)
        }
        recyclerView.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()
        refreshFavorites()
    }

    private fun refreshFavorites() {
        favoriteList.clear()
        favoriteList.addAll(FavoritesManager.getFavorites())

        if (favoriteList.isEmpty()) {
            recyclerView.visibility = View.GONE
            emptyTextView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            emptyTextView.visibility = View.GONE
        }

        adapter.notifyDataSetChanged()
    }

    private fun removeFavorite(product: ProductModel) {
        val index = favoriteList.indexOf(product)
        if (index != -1) {
            FavoritesManager.removeFromFavorites(product)
            favoriteList.removeAt(index)
            adapter.notifyItemRemoved(index)

            if (favoriteList.isEmpty()) {
                recyclerView.visibility = View.GONE
                emptyTextView.visibility = View.VISIBLE
            }
        }
    }
}
