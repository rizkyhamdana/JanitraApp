package com.rizkyhamdana.janitraapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rizkyhamdana.janitraapp.data.CategoryEntity
import com.rizkyhamdana.janitraapp.database.Products
import com.rizkyhamdana.janitraapp.databinding.FragmentHomeBinding
import com.rizkyhamdana.janitraapp.ui.adapter.CategoryAdapter
import com.rizkyhamdana.janitraapp.ui.adapter.ListAdapter
import com.rizkyhamdana.janitraapp.ui.category.CategoryActivity
import com.rizkyhamdana.janitraapp.ui.detail.DetailActivity
import com.rizkyhamdana.janitraapp.ui.search.SearchActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var listAdapter: ListAdapter
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activity?.title = " "

        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]


        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        categoryAdapter = CategoryAdapter()
        val category = homeViewModel.getCategory()
        categoryAdapter.setCategories(category)


        listAdapter = ListAdapter()
        homeViewModel.setData()
        homeViewModel.getData().observe(viewLifecycleOwner, {
            listAdapter.setProducts(it)
            homeViewModel.insertAllProducts(it)
        })
        binding.rvCategory.adapter = categoryAdapter
        categoryAdapter.setOnItemClickCallback(object : CategoryAdapter.OnItemClickCallback{
            override fun onItemClicked(data: CategoryEntity) {
                val intent = Intent(requireContext(), CategoryActivity::class.java)
                intent.putExtra(CategoryActivity.EXTRA_CATEGORY, data.name)
                startActivity(intent)
            }
        })
        binding.rvList.adapter = listAdapter
        listAdapter.setOnItemClickCallback(object : ListAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Products) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(intent)
            }

        })

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(requireContext(), SearchActivity::class.java)
                intent.putExtra(SearchActivity.EXTRA_SEARCH, query)
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}