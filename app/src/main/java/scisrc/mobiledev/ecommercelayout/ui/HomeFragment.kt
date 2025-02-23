package scisrc.mobiledev.ecommercelayout.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3
import scisrc.mobiledev.ecommercelayout.ProductModel
import scisrc.mobiledev.ecommercelayout.R

class HomeFragment : Fragment() {
    private lateinit var recommendedRecyclerView: RecyclerView
    private lateinit var recommendedAdapter: ProductAdapter
    private val recommendedProducts = mutableListOf<ProductModel>()

    private lateinit var promotionsRecyclerView: RecyclerView
    private lateinit var promotionsAdapter: ProductAdapter
    private val promotionsList = mutableListOf<ProductModel>()

    private lateinit var bannerViewPager: ViewPager2
    private lateinit var bannerIndicator: CircleIndicator3
    private val bannerHandler = Handler(Looper.getMainLooper())

    private val bannerRunnable = object : Runnable {
        override fun run() {
            val currentItem = bannerViewPager.currentItem
            val itemCount = bannerViewPager.adapter?.itemCount ?: 0
            if (itemCount > 0) {
                bannerViewPager.currentItem = (currentItem + 1) % itemCount
            }
            bannerHandler.postDelayed(this, 3000) // เลื่อนทุก 3 วินาที
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // เชื่อม RecyclerView สินค้าแนะนำ
        recommendedRecyclerView = view.findViewById(R.id.recommendedProductsRecycler)
        recommendedRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        recommendedProducts.add(ProductModel(" ASUS DUAL GEFORCE RTX", "4060 ram 8 gb", 11800.00, R.drawable.ic_pen))
        recommendedProducts.add(ProductModel("Monitor ACER XV270U Z1BMIIPRX", "ขนาด 27 นิ้ว 270Hz", 8400.00, R.drawable.ic_ruler))
        recommendedProducts.add(ProductModel("Cpu Amd Ryzen 7 5700", "8 Cores 16 Threads", 5490.00, R.drawable.ic_notebook))

        recommendedAdapter = ProductAdapter(recommendedProducts) { updateFavorites() }
        recommendedRecyclerView.adapter = recommendedAdapter

        // เชื่อม RecyclerView โปรโมชั่น
        promotionsRecyclerView = view.findViewById(R.id.promotionsRecycler)
        promotionsRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        promotionsList.add(ProductModel("ซื้อ 1 แถม 1   ", "คอมประกอบ, เมาส์", 17000.00, R.drawable.bundle_a))
        promotionsList.add(ProductModel("โน๊ตบุค + ของแถม", "ปลั๊กไฟ, เมาส์", 15000.00, R.drawable.bundle_b))
        promotionsList.add(ProductModel("คูปองส่วนลด", "รับคูปองส่วนลด 30% สูงสุด3000บาท ", 0.00, R.drawable.bundle_c))

        promotionsAdapter = ProductAdapter(promotionsList) { updateFavorites() }
        promotionsRecyclerView.adapter = promotionsAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bannerViewPager = view.findViewById(R.id.bannerViewPager)
        bannerIndicator = view.findViewById(R.id.bannerIndicator)

        val bannerImages = listOf(
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3
        )

        val adapter = BannerAdapter(bannerImages)
        bannerViewPager.adapter = adapter

        bannerIndicator.setViewPager(bannerViewPager)
        bannerHandler.postDelayed(bannerRunnable, 3000)

        bannerViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bannerHandler.removeCallbacks(bannerRunnable)
                bannerHandler.postDelayed(bannerRunnable, 3000)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bannerHandler.removeCallbacks(bannerRunnable)
    }

    private fun updateFavorites() {
        // อัปเดตรายการโปรด
    }
}
