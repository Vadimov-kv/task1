
package com.example.task1autodiary

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task1autodiary.adapter.CarListAdapter
import com.example.task1autodiary.data.CarListItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val ADD_CAR_CODE = 1
    var carList = mutableListOf<CarListItem>()
    lateinit var carsAdapter: CarListAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listInit()

        carsAdapter = CarListAdapter(carList)
        rv_carList.adapter = carsAdapter
        rv_carList.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            Intent(this, AddCarActivity::class.java).also {
                //it.putExtra("")
                startActivityForResult(it, ADD_CAR_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==ADD_CAR_CODE){
            if (resultCode == Activity.RESULT_OK){
                data?.let {
                    val brand = it.getStringExtra("EXTRA_BRAND") ?: "Unknown"
                    val model = it.getStringExtra("EXTRA_MODEL")
                    val year = it.getStringExtra("EXTRA_YEAR")
                    val imageUri = it.getStringExtra("EXTRA_URI")
                    var img: Drawable?=null
                    imageUri?.let {
                        img = uriToDrawable(imageUri)
                    }

                    val newCar = CarListItem(img,brand,model,year)

                    carList.add(newCar)
                    carsAdapter.notifyItemInserted(carList.size-1)

                }
            }
        }
    }

    private fun uriToDrawable(imgUri:String?) :Drawable?{
        return if(imgUri=="null")
            ContextCompat.getDrawable(applicationContext, R.drawable.deafult)
        else {
            val inputStream = contentResolver.openInputStream(Uri.parse(imgUri))
            Drawable.createFromStream(inputStream, imgUri)
        }
    }

    fun listInit(){
        carList = mutableListOf(
            CarListItem(
                ContextCompat.getDrawable(applicationContext, R.drawable.audi),
                "audi",
                "a8",
                "2000"
            ),

            CarListItem(
                ContextCompat.getDrawable(applicationContext, R.drawable.bmw),
                "bmw",
                "f36",
                "2020"
            ),

            CarListItem(
                ContextCompat.getDrawable(applicationContext, R.drawable.porsche),
                "porsche",
                "cayenne",
                "2005"
            ),
        )
    }

}