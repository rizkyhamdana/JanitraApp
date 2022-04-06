package com.rizkyhamdana.janitraapp.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.janitraapp.databinding.FragmentProfileBinding
import com.rizkyhamdana.janitraapp.ui.login.LoginActivity


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var mAuth : FirebaseAuth

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.title = "Profile"
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()

        val currentUser = mAuth.currentUser
        binding.tvNama.text = currentUser?.displayName
        binding.tvEmail.text = currentUser?.email
        Glide.with(requireActivity())
            .load(currentUser?.photoUrl)
            .apply(RequestOptions())
            .into(binding.imgProfile)

        binding.btnLogout.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }
    }

}