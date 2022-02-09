package tj.rs.devteam.lineindicator.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import tj.rs.devteam.lineindicator.app.databinding.ViewImageSlideBinding

class ScreenSlidePageFragment() : Fragment() {

    private lateinit var binding: ViewImageSlideBinding

    @DrawableRes
    private var img: Int? = null

    companion object {
        fun newInstance(@DrawableRes imgUrl: Int): ScreenSlidePageFragment {
            val f = ScreenSlidePageFragment()
            val args = Bundle()

            args.putInt("img", imgUrl)

            f.arguments = args
            return f
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ViewImageSlideBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        img = arguments?.getInt("img")

        img?.let {
            binding.imageView.setImageResource(it)
        }
    }
}