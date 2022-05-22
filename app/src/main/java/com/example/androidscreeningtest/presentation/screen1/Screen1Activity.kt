package com.example.androidscreeningtest.presentation.screen1

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProviders
import com.example.androidscreeningtest.Logger
import com.example.androidscreeningtest.R
import com.example.androidscreeningtest.presentation.screen2.SecondActivity
import com.example.androidscreeningtest.data.local.UserTable
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class Screen1Activity : AppCompatActivity() {
    val TAG = "MainActivity"
    private var isPalindrome = false
    val OPERATION_PICK_IMAGE = 1
    val OPERATION_CAPTURE_PHOTO = 2
    var uriImageSelected = ""
    private lateinit var viewModel: Screen1ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.e(TAG, "oncreate")
        val factory = Screen1ViewModelFactory(application)
        viewModel = ViewModelProviders.of(this, factory)[Screen1ViewModel::class.java]

        circle_image.setOnClickListener {
            showDialogPickImageFrom()
        }

        btn_next.setOnClickListener {
            Logger.e(TAG, "btn next clicked")
            if (isPalindromeString(et_palindrome.text.toString().trim())) {
                if (checkData()) {
                    saveDataToLocal(et_name.text.toString(), uriImageSelected)
                }
                else Toast.makeText(this, "Lengkapi data dan memilih gambarterlebih dahulu dan pastikan sudah melakukan pengecekan Palindrome", Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this, "Lengkapi data dan memilih gambar terlebih dahulu dan pastikan sudah melakukan pengecekan Palindrome", Toast.LENGTH_LONG).show()
            }
        }

        btn_check_palindrome.setOnClickListener {
            val pal = et_palindrome.text.toString().trim()
            if (isPalindromeString(pal)) {
                isPalindrome = true
                Logger.e(TAG, "$pal is palindrome")
                Toast.makeText(this, "Ok! This is palindrome", Toast.LENGTH_LONG).show()
            }
            else {
                isPalindrome = false
                Logger.e(TAG, "$pal is not palindrome")
                Toast.makeText(this, "This is NOT palindrome", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun saveDataToLocal(name:String, imageUri: String) {
        try {
            viewModel.insertEvent(UserTable(null, name, imageUri))
            Toast.makeText(this, "User Added", Toast.LENGTH_LONG).show()

            val bundle = Bundle()
            bundle.putString("username_saved", et_name.text.toString())
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)

        }
        catch (e: java.lang.Exception) {
            Toast.makeText(this, "Failed to add User Data", Toast.LENGTH_LONG).show()
        }
    }

    private fun showDialogPickImageFrom() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_pick_image_from, null)
        val customDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .show()
        customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnKamera = dialogView.findViewById<Button>(R.id.btn_from_camera)
        val btnGallery = dialogView.findViewById<Button>(R.id.btn_from_gallery)

        btnGallery.setOnClickListener {
            customDialog.dismiss()
            openGallery()
        }

        btnKamera.setOnClickListener {
            customDialog.dismiss()
            cameraWithLibrary()
        }
    }

    private fun openGallery() {
        ImagePicker.with(this)
            .galleryOnly()
            .start(OPERATION_PICK_IMAGE)
    }

    private fun cameraWithLibrary() {
        ImagePicker.with(this)
            .cameraOnly()	//User can only capture image using Camera
            .start(OPERATION_CAPTURE_PHOTO)
    }

    private fun getRealPathFromURI(uri: Uri): String? {
        var path = ""
        if (this.contentResolver != null) {
            val cursor = this?.contentResolver?.query(uri, null, null, null, null)
            if (cursor != null) {
                cursor.moveToFirst()
                val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                path = cursor.getString(idx)
                cursor.close()
            }
        }
        return path
    }

    private fun getImageUri(inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val imageName =  SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val path: String = MediaStore.Images.Media.insertImage(
            this.contentResolver,
            inImage,
            "smTest_$imageName",
            null
        )
        return Uri.parse(path)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (requestCode == OPERATION_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
                val fileUri = data?.data!!
                circle_image.setImageURI(fileUri)
                val newBitmap = circle_image.drawable.toBitmap()
                val tempUri = getImageUri(newBitmap)
                uriImageSelected = tempUri.toString()
                Logger.e(TAG, "uri image $uriImageSelected")
                tv_add_image.visibility = View.GONE
            }
            else if (requestCode == OPERATION_CAPTURE_PHOTO) {
                val fileUri = data?.data!!
                circle_image.setImageURI(fileUri)
                val newBitmap = circle_image.drawable.toBitmap()
                val tempUri = getImageUri(newBitmap)
                uriImageSelected = tempUri.toString()
                Logger.e(TAG, "uri image $uriImageSelected")
                tv_add_image.visibility = View.GONE
            }
            else {
                Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show()
            }

        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Something went wrong ${e.message} ,, ${e.localizedMessage} ,, ${e.cause.toString()}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun isPalindromeString(inputStr: String): Boolean {
        val sb = StringBuilder(inputStr)
        val reverseStr = sb.reverse().toString()
        return inputStr.equals(reverseStr, ignoreCase = true)
    }

    private fun checkData(): Boolean {
        return isPalindrome && et_palindrome.text.isNotEmpty() && et_name.text.isNotEmpty() && uriImageSelected!=""
    }

    private fun runWithPermission(
        permissions: Array<String>,
        onPermissionGranted: () -> Unit
    ) {
        runWithPermission(permissions, onPermissionGranted)
    }

    private val REQUIRE_PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
}