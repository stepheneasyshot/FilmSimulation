package com.stephen.filmsimulation.utils

import android.graphics.BitmapFactory
import android.net.Uri
import com.stephen.filmsimulation.base.appContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object BrandStyle {

    suspend fun setLeicaStyle(imageUri: Uri) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeStream(appContext.contentResolver.openInputStream(imageUri))
            .process()
            .adjustBrightness(1.2f) // 调整亮度
            .adjustContrast(1.1f) // 调整对比度
            .adjustSaturation(0.9f) // 调整饱和度
            .adjustHue(0.1f) // 调整色调
            .execute()
    }

    suspend fun setSonyStyle(imageUri: Uri) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeStream(appContext.contentResolver.openInputStream(imageUri))
            .process()
            .adjustBrightness(1.2f) // 调整亮度
            .adjustContrast(1.1f) // 调整对比度
            .adjustSaturation(0.9f) // 调整饱和度
            .adjustHue(0.1f) // 调整色调
            .execute()
    }

    suspend fun setFujifilmStyle(imageUri: Uri) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeStream(appContext.contentResolver.openInputStream(imageUri))
            .process()
            .adjustBrightness(1.2f) // 调整亮度
            .adjustContrast(1.1f) // 调整对比度
            .adjustSaturation(0.9f) // 调整饱和度
            .adjustHue(0.1f) // 调整色调
            .execute()
    }

    suspend fun setKodakStyle(imageUri: Uri) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeStream(appContext.contentResolver.openInputStream(imageUri))
            .process()
            .adjustBrightness(1.2f) // 调整亮度
            .adjustContrast(1.1f) // 调整对比度
            .adjustSaturation(0.9f) // 调整饱和度
            .adjustHue(0.1f) // 调整色调
            .execute()
    }

    suspend fun setNikonStyle(imageUri: Uri) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeStream(appContext.contentResolver.openInputStream(imageUri))
            .process()
            .adjustBrightness(1.2f) // 调整亮度
            .adjustContrast(1.1f) // 调整对比度
            .adjustSaturation(0.9f) // 调整饱和度
            .adjustHue(0.1f) // 调整色调
            .execute()
    }


    suspend fun setCanonStyle(imageUri: Uri) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeStream(appContext.contentResolver.openInputStream(imageUri))
            .process()
            .adjustBrightness(1.2f) // 调整亮度
            .adjustContrast(1.1f) // 调整对比度
            .adjustSaturation(0.9f) // 调整饱和度
            .adjustHue(0.1f) // 调整色调
            .execute()
    }

    suspend fun setLumixStyle(imageUri: Uri) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeStream(appContext.contentResolver.openInputStream(imageUri))
            .process()
            .adjustBrightness(1.2f) // 调整亮度
            .adjustContrast(1.1f) // 调整对比度
            .adjustSaturation(0.9f) // 调整饱和度
            .adjustHue(0.1f) // 调整色调
            .execute()
    }

    suspend fun setRicohStyle(imageUri: Uri) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeStream(appContext.contentResolver.openInputStream(imageUri))
            .process()
            .adjustBrightness(1.2f) // 调整亮度
            .adjustContrast(1.1f) // 调整对比度
            .adjustSaturation(0.9f) // 调整饱和度
            .adjustHue(0.1f) // 调整色调
            .execute()
    }

    suspend fun setHasselBladStyle(imageUri: Uri) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeStream(appContext.contentResolver.openInputStream(imageUri))
            .process()
            .adjustBrightness(1.2f) // 调整亮度
            .adjustContrast(1.1f) // 调整对比度
            .adjustSaturation(0.9f) // 调整饱和度
            .adjustHue(0.1f) // 调整色调
            .execute()
    }

    suspend fun setDefaultStyle(imageUri: Uri) = withContext(Dispatchers.IO) {
        BitmapFactory.decodeStream(appContext.contentResolver.openInputStream(imageUri))
            .process()
            .adjustBrightness(1.2f) // 调整亮度
            .adjustContrast(1.1f) // 调整对比度
            .adjustSaturation(0.9f) // 调整饱和度
            .adjustHue(0.1f) // 调整色调
            .execute()
    }
}