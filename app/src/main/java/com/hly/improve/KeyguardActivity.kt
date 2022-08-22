@file:Suppress("DEPRECATION")

package com.hly.improve

import android.app.KeyguardManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hly.improve.databinding.ActivityKeyguardBinding


class KeyguardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKeyguardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKeyguardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.naviCredential.setOnClickListener { jumpToCredentialActivity() }
    }

    private fun jumpToCredentialActivity() {
        var mKeyguardMgr: KeyguardManager? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mKeyguardMgr = this.getSystemService(KeyguardManager::class.java)
        }
        val intent = mKeyguardMgr!!.createConfirmDeviceCredentialIntent(null, null)
        if (intent != null) {
            startActivityForResult(intent, 1000)
        } else {
            Toast.makeText(this, "intent==null", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "校验成功", Toast.LENGTH_LONG).show();
            }
        }
    }

}