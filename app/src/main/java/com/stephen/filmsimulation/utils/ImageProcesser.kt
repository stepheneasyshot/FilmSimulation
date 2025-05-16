package com.stephen.filmsimulation.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint

class ImageProcesser {

    private lateinit var mTempBitmap: Bitmap

    fun init(bitmap: Bitmap): ImageProcesser {
        mTempBitmap = bitmap
        return this
    }

    /**
     * 调整图片亮度
     * 0-255
     */
    fun adjustBrightness(brightness: Float): ImageProcesser {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(
            floatArrayOf(
                1f, 0f, 0f, 0f, brightness,
                0f, 1f, 0f, 0f, brightness,
                0f, 0f, 1f, 0f, brightness,
                0f, 0f, 0f, 1f, 0f
            )
        )
        val paint = Paint()
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        val adjustedBitmap =
            Bitmap.createBitmap(mTempBitmap.width, mTempBitmap.height, mTempBitmap.config)
        val canvas = Canvas(adjustedBitmap)
        canvas.drawBitmap(mTempBitmap, 0f, 0f, paint)
        mTempBitmap = adjustedBitmap
        return this
    }

    /**
     * 调整图片对比度
     * contrast: 这个参数用于调整图像的对比度。
     * 它的取值范围通常也是从 -1.0 到 1.0，
     * 其中 -1.0 表示最低对比度，0.0 表示不变，1.0 表示最高对比度。
     * 值大于 1.0 或小于 -1.0 可能会导致图像对比度异常，
     * 失去自然感。
     */
    fun adjustContrast(contrast: Float): ImageProcesser {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(
            floatArrayOf(
                contrast, 0f, 0f, 0f, 0f,
                0f, contrast, 0f, 0f, 0f,
                0f, 0f, contrast, 0f, 0f,
                0f, 0f, 0f, 1f, 0f
            )
        )
        val paint = Paint()
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        val adjustedBitmap =
            Bitmap.createBitmap(mTempBitmap.width, mTempBitmap.height, mTempBitmap.config)
        val canvas = Canvas(adjustedBitmap)
        canvas.drawBitmap(mTempBitmap, 0f, 0f, paint)
        mTempBitmap = adjustedBitmap
        return this
    }

    /**
     * 调整图片饱和度
     * saturation: 这个参数用于调整图像的饱和度。
     * 它的取值范围通常是从 -1.0 到 1.0，
     * 其中 -1.0 表示完全去饱和（灰度图像），0.0 表示不变，1.0 表示最大饱和度。
     * 值大于 1.0 或小于 -1.0 可能会导致图像颜色失真。
     */
    fun adjustSaturation(saturation: Float): ImageProcesser {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(
            floatArrayOf(
                0.213f + saturation * 0.787f,
                0.715f - saturation * 0.715f,
                0.072f - saturation * 0.072f,
                0f,
                0f,
                0.213f - saturation * 0.213f,
                0.715f + saturation * 0.285f,
                0.072f - saturation * 0.072f,
                0f,
                0f,
                0.213f - saturation * 0.213f,
                0.715f - saturation * 0.715f,
                0.072f + saturation * 0.928f,
                0f,
                0f,
                0f,
                0f,
                0f,
                1f,
                0f
            )
        )
        val paint = Paint()
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        val adjustedBitmap =
            Bitmap.createBitmap(mTempBitmap.width, mTempBitmap.height, mTempBitmap.config)
        val canvas = Canvas(adjustedBitmap)
        canvas.drawBitmap(mTempBitmap, 0f, 0f, paint)
        mTempBitmap = adjustedBitmap
        return this
    }

    /**
     * 调整图片色调
     * hue: 这个参数用于调整图像的色调。
     * 它的取值范围通常是从 -180.0 到 180.0，
     * 表示色相环上的角度偏移。
     * 0.0 表示不变，正值表示顺时针旋转色相环，负值表示逆时针旋转色相环。
     * 值大于 180.0 或小于 -180.0 会导致色相环上的重复旋转。
     */
    fun adjustHue(hue: Float): ImageProcesser {
        val colorMatrix = ColorMatrix()
        colorMatrix.set(
            floatArrayOf(
                1f, 0f, 0f, 0f, hue,
                0f, 1f, 0f, 0f, hue,
                0f, 0f, 1f, 0f, hue,
                0f, 0f, 0f, 1f, 0f
            )
        )
        val paint = Paint()
        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        val adjustedBitmap =
            Bitmap.createBitmap(mTempBitmap.width, mTempBitmap.height, mTempBitmap.config)
        val canvas = Canvas(adjustedBitmap)
        canvas.drawBitmap(mTempBitmap, 0f, 0f, paint)
        mTempBitmap = adjustedBitmap
        return this
    }

    fun execute(): Bitmap {
        return mTempBitmap
    }

}

// 扩展函数，用于初始化 ImageProcesser 并开始链式调用
fun Bitmap.process(): ImageProcesser {
    return ImageProcesser().init(this)
}
