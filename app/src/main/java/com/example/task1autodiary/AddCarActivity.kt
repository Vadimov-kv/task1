package com.example.task1autodiary

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_add_car.*
import kotlinx.android.synthetic.main.activity_main.*

class AddCarActivity : AppCompatActivity() {

    val SELECT_IMAGE_CODE =1
    var imgUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)


        button.setOnClickListener {
            Intent().apply {
                putExtra("EXTRA_BRAND", etBrand.text.toString())?:"vbfv"
                putExtra("EXTRA_MODEL", etModel.text.toString())
                putExtra("EXTRA_YEAR", etYear.text.toString())
                putExtra("EXTRA_URI", imgUri.toString())

                setResult(Activity.RESULT_OK,this)

                finish()

            }
        }


        ivCar.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,SELECT_IMAGE_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode==SELECT_IMAGE_CODE){
            imgUri = data?.data
            ivCar.setImageURI(imgUri)
        }
    }
}