package com.rizkyhamdana.janitraapp.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.rizkyhamdana.janitraapp.R
import com.rizkyhamdana.janitraapp.database.Checkout
import com.rizkyhamdana.janitraapp.database.Favorite
import com.rizkyhamdana.janitraapp.database.Products
import com.rizkyhamdana.janitraapp.databinding.ActivityDetailBinding
import com.rizkyhamdana.janitraapp.ui.checkout.CheckoutActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding
    private var qty: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Detail"

        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        val data = intent.getParcelableExtra<Products>(EXTRA_DATA) as Products

        setLayout(data)
        val favorite = Favorite(
            data.id,
            data.name,
            data.price,
            data.desc,
            data.category,
            data.image
        )

        viewModel.getFavorite(data.id).observe(this, { dataFavorite ->
            if (dataFavorite == null){
                binding.btnFavorite.text = getString(R.string.favoritkan)
                binding.btnFavorite.setOnClickListener {
                    viewModel.insertFavorite(favorite)
                    Toast.makeText(this@DetailActivity, getString(R.string.insertfavorite), Toast.LENGTH_LONG).show()
                }
            }else{
                binding.btnFavorite.text = getString(R.string.hapus_favorit)
                binding.btnFavorite.setOnClickListener {
                    viewModel.deleteFavorite(dataFavorite)
                    Toast.makeText(this@DetailActivity, getString(R.string.deletefavorite), Toast.LENGTH_LONG).show()
                }
            }
        })
        viewModel.getCheckout(data.id).observe(this, {
            if (it != null){
                qty = it.quantity
            }
        })

        binding.btnOrder.setOnClickListener {
            val stringqty = binding.etKuantitasValue.text.toString()
            val qtyet = Integer.parseInt(stringqty)
            if (qtyet>0){
                val qtyNow = qtyet + qty
                val subtotal = qtyNow * data.price
                val checkout = Checkout(
                    data.id,
                    data.name,
                    data.image,
                    qtyNow,
                    subtotal
                )
                viewModel.insertCheckout(checkout)
                Toast.makeText(
                    this@DetailActivity,
                    getString(R.string.cart_message),
                    Toast.LENGTH_LONG
                ).show()
            } else{
                Toast.makeText(this@DetailActivity, getString(R.string.qty_message), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setLayout(data: Products) {
        with(binding){
            tvName.text = data.name
            (getString(R.string.rupiah) + data.price.toString()).also { tvPrice.text = it }
            tvDeskripsiValue.text = data.desc
            Glide.with(this@DetailActivity)
                .load(data.image)
                .into(imgProduct)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ic_cart -> {
                startActivity(Intent(this, CheckoutActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}