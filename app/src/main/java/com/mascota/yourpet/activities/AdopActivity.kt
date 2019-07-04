package com.mascota.yourpet.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.mascota.yourpet.Fragments.Adopciones
import com.mascota.yourpet.Fragments.DarAdopFragment
import com.mascota.yourpet.R
import kotlinx.android.synthetic.main.fragment_dar_adop.*
import java.io.InputStream

class AdopActivity : AppCompatActivity(), Adopciones.Other, DarAdopFragment.Formulario {

    var adopFragment : Fragment = Adopciones()
    var darAdopFragment : Fragment = DarAdopFragment()
    val REQUEST_IMAGE_CAPTURE:Int = 1
    var img:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adop)

        changeFragment(R.id.adop_activity,adopFragment)
    }

    override fun change(op: Int) {
        when(op){
            2 ->{
                changeFragment(R.id.adop_activity,darAdopFragment)
            }
        }
    }

    override fun sendInfo(op: Int) {
        when(op){
            1->{
                var picture = Intent()
                picture.setType("image/*").setAction(Intent.ACTION_GET_CONTENT)
                startActivityForResult(Intent.createChooser(picture, "Seleccione una imagen"),REQUEST_IMAGE_CAPTURE)
            }
            3->{
                changeFragment(R.id.adop_activity,adopFragment)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            REQUEST_IMAGE_CAPTURE->{
                if(resultCode == RESULT_OK){
                    img = data?.data
                    var path = img?.path
                    if(path != null){
                        var imgStream:InputStream?
                        imgStream = getContentResolver().openInputStream(img)
                        var bmp:Bitmap = BitmapFactory.decodeStream(imgStream)
                        img_pet.setImageBitmap(bmp)
                    }
                }
            }
        }
    }

    private fun changeFragment(id: Int, frag: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(id, frag)
            .commit()
    }
}
