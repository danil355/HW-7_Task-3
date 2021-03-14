package kz.step.hw_7_task_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_RESULT = 1
        const val KEY_PERSONS_NAME = "KEY_PERSONS_NAME"
    }

    var imageView: ImageView? = null
    var nameOfUser: EditText? = null
    var surnameOfUser: EditText? = null
    var button: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        initializeLiseners()
    }

    private fun initializeViews() {
        imageView = findViewById(R.id.imageview_user)
        nameOfUser = findViewById(R.id.edittext_name)
        surnameOfUser = findViewById(R.id.edittext_surname)
        button = findViewById(R.id.button_sent)
    }

    private fun initializeLiseners() {
        button?.setOnClickListener {
            initiateDisplaySecondActivity()
        }
    }

    fun initiateDisplaySecondActivity() {
        val name = nameOfUser?.text?.toString()
        val surname = surnameOfUser?.text?.toString()
        val image = imageView?.setImageResource(R.drawable.volf)
        startActivityForResult(
            Intent(this, ActivitySecond::class.java).apply {
                putExtra("Имя", name)
                putExtra("Фамилия", surname)
                putExtra("Фото", image?.toString())
            }, REQUEST_CODE_RESULT
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_RESULT -> {
                Toast.makeText(
                    this,
                    data?.getStringExtra(KEY_PERSONS_NAME),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}