package com.mascota.yourpet.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mascota.yourpet.R
import kotlinx.android.synthetic.main.activity_leyes.*

class leyes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leyes)

        pdf.fromAsset("Leyes-Contra-Maltrato-Animal-SV.pdf").load()
    }
}
