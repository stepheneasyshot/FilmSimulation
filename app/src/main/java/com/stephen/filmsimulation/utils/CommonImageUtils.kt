package com.stephen.filmsimulation.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import com.stephen.commonhelper.utils.debugLog
import com.stephen.commonhelper.utils.errorLog
import com.stephen.filmsimulation.base.appContext
import java.text.SimpleDateFormat

object CommonImageUtils {

    private const val TAG = "CommonImageUtils"

    /**
     * 保存一个bitmap到本地sdcard的Pictures目录
     */
    @SuppressLint("SimpleDateFormat")
    fun saveImageToGallery(
        bitmap: Bitmap,
        filename: String = "FilmSimulation_${
            SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis())
        }.jpg",
    ) {
        val values = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg") // 文件类型
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }
        runCatching {
            appContext.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                ?.let {
                    appContext.contentResolver.openOutputStream(it)?.apply {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, this)
                        flush()
                        close()
                    }
                    appContext.contentResolver.notifyChange(it, null)
                    debugLog("Image saved successfuly!")
                }
        }.onFailure {
            errorLog("save image error:${it.message}", TAG)
        }
    }
}