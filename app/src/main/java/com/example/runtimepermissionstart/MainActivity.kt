package com.example.runtimepermissionstart

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.file.attribute.AclEntry
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    val  request_code : Int =10 ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // check_permission_granted();
        val Button_c=findViewById<Button>(R.id.button);
        Button_c.setOnClickListener()
        {
            check_read_contacts_permission();
        }


    }
    fun check_read_contacts_permission()
    {
        val permission=ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_CONTACTS);
        if(permission!=PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this,"You have not given permission to read contacts",Toast.LENGTH_SHORT).show();
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.READ_CONTACTS))
        {
                val builder=AlertDialog.Builder(this)
                builder.setMessage("We need permission to read contacts")
                builder.setTitle("Permission required")
                builder.setPositiveButton("ok")
                {
                    dialog, which ->
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS),request_code);
                }
                val dialog=builder.create();
                dialog.show();
            }
        else
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_CONTACTS),request_code);
        }
        }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode)
        {
            request_code->
            {
                if(grantResults.isEmpty() || grantResults[0]!=PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"You have not granted permission",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this,"Thanks you have granted the permission ",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}



//    fun check_permission_granted()
//    {
//        val permission=ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
//        if(permission!=PackageManager.PERMISSION_GRANTED)
//        {
//            Toast.makeText(this,"Permission is not granted",Toast.LENGTH_SHORT).show();
//        }
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.ACCESS_FINE_LOCATION))
//        {
//            val builder=AlertDialog.Builder(this);
//            builder.setMessage("We required permission to access the location for better features")
//            builder.setTitle("Permission Required")
//            builder.setPositiveButton("Ok")
//            {
//                dialogInterface, i ->
//                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),request_code);
//                //Toast.makeText(this,"Thank you",Toast.LENGTH_SHORT).show();
//            }
//            val dialog=builder.create();
//            dialog.show();
//
//        }
//    }
