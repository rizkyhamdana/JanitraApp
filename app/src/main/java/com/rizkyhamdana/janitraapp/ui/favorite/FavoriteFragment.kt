package com.rizkyhamdana.janitraapp.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rizkyhamdana.janitraapp.database.Favorite
import com.rizkyhamdana.janitraapp.database.Products
import com.rizkyhamdana.janitraapp.databinding.FragmentFavoriteBinding
import com.rizkyhamdana.janitraapp.ui.adapter.FavoriteAdapter
import com.rizkyhamdana.janitraapp.ui.detail.DetailActivity

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var adapter: FavoriteAdapter
    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activity?.title = "Favorit"

        favoriteViewModel =
            ViewModelProvider(this)[FavoriteViewModel::class.java]

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FavoriteAdapter()
        favoriteViewModel.getAllFavorite().observe(viewLifecycleOwner,{
            if(it.isEmpty()){
                binding.animationNotfound.visibility = View.VISIBLE
                binding.rvList.visibility = View.GONE
            }else{
                binding.animationNotfound.visibility = View.GONE
                binding.rvList.visibility = View.VISIBLE
                adapter.setFavorites(it)
            }
        })
        binding.rvList.adapter = adapter
        adapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Favorite) {
                val products = Products(
                    data.id,
                    data.name,
                    data.price,
                    data.description,
                    data.category,
                    data.image
                )
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, products)
                startActivity(intent)
            }

        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}