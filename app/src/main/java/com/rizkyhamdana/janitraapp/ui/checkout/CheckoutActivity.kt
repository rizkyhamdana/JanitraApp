package com.rizkyhamdana.janitraapp.ui.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.rizkyhamdana.janitraapp.MainActivity
import com.rizkyhamdana.janitraapp.R
import com.rizkyhamdana.janitraapp.database.Checkout
import com.rizkyhamdana.janitraapp.database.Orders
import com.rizkyhamdana.janitraapp.databinding.ActivityCheckoutBinding
import com.rizkyhamdana.janitraapp.ui.adapter.CheckoutAdapter
import java.text.SimpleDateFormat
import java.util.*

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var viewModel: CheckoutViewModel
    private lateinit var adapter: CheckoutAdapter
    private var totalBayar: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Checkout"
        mAuth = FirebaseAuth.getInstance()

        val currentUser = mAuth.currentUser
        viewModel = ViewModelProvider(this)[CheckoutViewModel::class.java]
        adapter = CheckoutAdapter()
        viewModel.getAllCheckoutt().observe(this, {
            if(it.isEmpty()){
                binding.layoutCheckout.layoutCo.visibility = View.GONE
                binding.animationNotfound.visibility = View.VISIBLE
            }else{
                binding.layoutCheckout.layoutCo.visibility = View.VISIBLE
                binding.layoutCheckout.etNama.setText(currentUser?.displayName)
                binding.animationNotfound.visibility = View.GONE
                adapter.setFavorites(it)
                for (element in it){
                    totalBayar += element.total
                }
                binding.layoutCheckout.tvTotalValue.text = totalBayar.toString()
            }
        })

        adapter.setOnItemClickCallback(object : CheckoutAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Checkout) {
                viewModel.deleteCheckout(data)
                Toast.makeText(applicationContext, getString(R.string.delete_item_message), Toast.LENGTH_LONG).show()
            }

        })
        binding.layoutCheckout.rvCheckout.adapter = adapter

        binding.layoutCheckout.btnCancel.setOnClickListener {
            viewModel.clearCheckout()
            Toast.makeText(this, getString(R.string.pesananbatal), Toast.LENGTH_LONG).show()
        }

        binding.layoutCheckout.btnOrdernow.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy")
            val currentDate = sdf.format(Date())
            val orders = Orders(
                date = currentDate.toString(),
                name = binding.layoutCheckout.etNama.text.toString(),
                whatsapp = binding.layoutCheckout.etPhone.text.toString(),
                address = binding.layoutCheckout.etAddress.text.toString()
            )
            if (binding.layoutCheckout.etNama.text.isNotEmpty() && binding.layoutCheckout.etAddress.text.isNotEmpty() && binding.layoutCheckout.etPhone.text.isNotEmpty()){
                viewModel.insertOrders(orders)
                viewModel.clearCheckout()
                Toast.makeText(this, getString(R.string.pesanandiproses), Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                Toast.makeText(this, getString(R.string.databelumlengkap), Toast.LENGTH_LONG).show()
            }

        }

    }

}