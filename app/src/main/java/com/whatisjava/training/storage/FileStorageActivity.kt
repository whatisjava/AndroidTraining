package com.whatisjava.training.storage

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.whatisjava.training.databinding.ActivityFileStorageBinding

class FileStorageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFileStorageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFileStorageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.osVersionText.text = "Android SDK 版本 ${Build.VERSION.SDK_INT}"

        val internalFilesDir = filesDir
        val internalCacheDir = cacheDir

        binding.filesDirText.text = filesDir.path
        binding.cacheDirText.text = cacheDir.path

        Log.d("internalFilesDir", internalFilesDir.path)
        Log.d("internalCacheDir", internalCacheDir.path)

//        android 5.1
//        03-05 17:50:29.155 D/internalFilesDir: /data/data/com.whatisjava.training/files
//        03-05 17:50:29.156 D/internalCacheDir: /data/data/com.whatisjava.training/cache

//        android 10
//        2021-03-05 17:57:06.689 D/internalFilesDir: /data/user/0/com.whatisjava.training/files
//        2021-03-05 17:57:06.689 D/internalCacheDir: /data/user/0/com.whatisjava.training/cache

//        android 11
//        2021-02-01 11:21:05.079 D/internalFilesDir: /data/user/0/com.whatisjava.training/files
//        2021-02-01 11:21:05.079 D/internalCacheDir: /data/user/0/com.whatisjava.training/cache

        /*
        * @param type The type of files directory to return. May be {@code null}
        *        for the root of the files directory or one of the following
        *        constants for a subdirectory:
        * {@link android.os.Environment#DIRECTORY_MUSIC},
        * {@link android.os.Environment#DIRECTORY_PODCASTS},
        * {@link android.os.Environment#DIRECTORY_RINGTONES},
        * {@link android.os.Environment#DIRECTORY_ALARMS},
        * {@link android.os.Environment#DIRECTORY_NOTIFICATIONS},
        * {@link android.os.Environment#DIRECTORY_PICTURES}, or
        * {@link android.os.Environment#DIRECTORY_MOVIES}.
        */
        val externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val externalCacheDir = externalCacheDir

        binding.externalFilesDirText.text = externalFilesDir?.path
        binding.externalCacheDirText.text = externalCacheDir?.path

        Log.d("externalFilesDir", externalFilesDir?.path ?: "")
        Log.d("externalCacheDir", externalCacheDir?.path ?: "")

//        android 5.1
//        2021-02-01 11:21:05.146 D/externalFilesDir: /storage/emulated/0/Android/data/com.whatisjava.training/files/Pictures
//        2021-02-01 11:21:05.146 D/externalCacheDir: /storage/emulated/0/Android/data/com.whatisjava.training/cache

//        android 10
//        2021-03-05 17:57:06.714 D/externalFilesDir: /storage/emulated/0/Android/data/com.whatisjava.training/files/Pictures
//        2021-03-05 17:57:06.714 D/externalCacheDir: /storage/emulated/0/Android/data/com.whatisjava.training/cache

//        android 11
//        03-05 17:50:29.156 D/externalFilesDir: /storage/sdcard/Android/data/com.whatisjava.training/files/Pictures
//        03-05 17:50:29.157 D/externalCacheDir: /storage/sdcard/Android/data/com.whatisjava.training/cache

    }
}