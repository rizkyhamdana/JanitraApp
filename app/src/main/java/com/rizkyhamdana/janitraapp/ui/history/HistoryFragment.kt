package com.rizkyhamdana.janitraapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rizkyhamdana.janitraapp.R
import com.rizkyhamdana.janitraapp.databinding.FragmentHistoryBinding
import com.rizkyhamdana.janitraapp.ui.adapter.HistoryAdapter

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: HistoryAdapter
    private var _binding: FragmentHistoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.title = "Riwayat"
        historyViewModel =
            ViewModelProvider(this)[HistoryViewModel::class.java]

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HistoryAdapter()

        historyViewModel.getALlOrders().observe(viewLifecycleOwner, {
            if (it.isEmpty()){
              binding.animationNotfound.visibility = View.VISIBLE
                binding.fabDelete.visibility = View.GONE
                binding.rvList.visibility = View.GONE
            }else{
                binding.animationNotfound.visibility = View.GONE
                binding.rvList.visibility = View.VISIBLE
                binding.fabDelete.visibility = View.VISIBLE
                adapter.setHistory(it)
            }
        })
        binding.rvList.adapter= adapter


        binding.fabDelete.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.historyclear), Toast.LENGTH_LONG).show()
            historyViewModel.clearOrders()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}