package scisrc.mobiledev.ecommercelayout.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import scisrc.mobiledev.ecommercelayout.R

class PromotionAdapter(private val promotions: List<PromotionModel>) :
    RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder>() {

    class PromotionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val promotionImage: ImageView = view.findViewById(R.id.promotionImage)
        val promotionTitle: TextView = view.findViewById(R.id.promotionTitle)
        val promotionDescription: TextView = view.findViewById(R.id.promotionDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_promotion, parent, false)
        return PromotionViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromotionViewHolder, position: Int) {
        val promotion = promotions[position]
        holder.promotionImage.setImageResource(promotion.imageRes)
        holder.promotionTitle.text = promotion.title
        holder.promotionDescription.text = promotion.description
    }

    override fun getItemCount(): Int = promotions.size
}
