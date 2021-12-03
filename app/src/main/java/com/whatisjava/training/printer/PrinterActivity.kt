package com.whatisjava.training.printer

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.print.PrintHelper
import com.whatisjava.training.R
import com.whatisjava.training.databinding.ActivityPrinterBinding

class PrinterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrinterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrinterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        doPhotoPrint()
    }

    private fun doPhotoPrint() {
        this.also { context ->
            PrintHelper(context).apply {
                scaleMode = PrintHelper.SCALE_MODE_FIT
            }.also { printHelper ->
                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)
                printHelper.printBitmap("test.png - test print", bitmap)
            }
        }

    }

}