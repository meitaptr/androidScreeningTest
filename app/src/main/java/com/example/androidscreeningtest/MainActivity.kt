package com.example.androidscreeningtest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidscreeningtest.presentation.screen2.SecondActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    private var isPalindrome = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.e(TAG, "oncreate")

        btn_next.setOnClickListener {
            Logger.e(TAG, "btn next clicked")
//            if (isPalindromeString(et_palindrome.text.toString().trim())) {
//                if (checkData()) startActivity(Intent(this, SecondActivity::class.java))
//                else Toast.makeText(this, "Lengkapi data anda terlebih dahulu dan pastikan sudah melakukan pengecekan Palindrome", Toast.LENGTH_LONG).show()
//            }
//            else {
//                Toast.makeText(this, "Lengkapi data anda terlebih dahulu dan pastikan sudah melakukan pengecekan Palindrome", Toast.LENGTH_LONG).show()
//            }
            startActivity(Intent(this, SecondActivity::class.java))
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

    private fun isPalindromeString(inputStr: String): Boolean {
        val sb = StringBuilder(inputStr)
        val reverseStr = sb.reverse().toString()
        return inputStr.equals(reverseStr, ignoreCase = true)
    }

    private fun checkData(): Boolean {
        return isPalindrome && et_palindrome.text.isNotEmpty() && et_name.text.isNotEmpty()
    }
}