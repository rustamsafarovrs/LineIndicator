package tj.rs.devteam.lineindicator.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import tj.rs.devteam.lineindicator.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val images = mutableListOf<Int>()

        images.add(R.drawable.image_5)
        images.add(R.drawable.image_1)
        images.add(R.drawable.image_2)
        images.add(R.drawable.image_3)
        images.add(R.drawable.image_4)
        images.add(R.drawable.image_6)

        binding.viewPager2.adapter = ImageAdapter(this, images)

        binding.lineIndicator.setViewPager2(binding.viewPager2)

    }
}