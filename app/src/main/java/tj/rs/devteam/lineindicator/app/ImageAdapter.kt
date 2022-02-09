package tj.rs.devteam.lineindicator.app

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ImageAdapter(
    private val fa: FragmentActivity,
    private val items: List<Int>
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun createFragment(position: Int): Fragment {
        return ScreenSlidePageFragment.newInstance(items[position])
    }
}