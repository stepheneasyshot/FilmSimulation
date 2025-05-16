package com.stephen.filmsimulation.ui

import android.Manifest.permission.POST_NOTIFICATIONS
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.core.view.children
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.stephen.commonhelper.base.BaseActivity
import com.stephen.commonhelper.utils.debugLog
import com.stephen.commonhelper.utils.errorLog
import com.stephen.commonhelper.utils.infoLog
import com.stephen.filmsimulation.R
import com.stephen.filmsimulation.base.FilmItemAdapter
import com.stephen.filmsimulation.data.BrandId
import com.stephen.filmsimulation.data.manufacturerList
import com.stephen.filmsimulation.databinding.ActivityMainBinding
import com.stephen.filmsimulation.utils.BrandStyle
import com.stephen.filmsimulation.utils.CommonImageUtils.saveImageToGallery
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var imageUri: Uri

    private lateinit var rvAdapter: FilmItemAdapter

    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                // 获取选中的图片的 URI
                result.data?.data?.let { uri ->
                    imageUri = uri
                    switchEditView()
                }
            }
        }

    private val permissionsLauncher =
        registerForActivityResult(RequestMultiplePermissions()) { results ->
            results.forEach { (permission, isGranted) ->
                infoLog("permission:${permission},isGranted:${isGranted}")
                when (permission) {
                    READ_MEDIA_IMAGES -> {
                        if (isGranted) {
                            debugLog("READ_MEDIA_IMAGES权限已被授予")
                            // 打开图片选择器
                            val intent = Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                            )
                            imagePickerLauncher.launch(intent)
                        } else {
                            errorLog("READ_MEDIA_IMAGES权限被拒绝")
                        }
                    }
                }
            }
        }

    override fun setBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 获取通知权限
        checkNotificationPermission()

        // 中心的add按钮，首次进入显示
        binding.ivAddpicture.setOnClickListener {
            checkStoragePermission()
        }

        // 左上角的文字按钮，可以打开新图片
        binding.tvOpenPic.setOnClickListener {
            checkStoragePermission()
        }

        // 保存图像到设备
        binding.tvExportPic.setOnClickListener {
            // 检查文件控制权限
            checkSavePermission {
                (binding.ivSelectedpicture.drawable as BitmapDrawable).bitmap.apply {
                    if (!this.isRecycled) {
                        saveImageToGallery(this)
                        Toast.makeText(this@MainActivity, "图片已保存到相册", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this@MainActivity, "图片损坏", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.rvChoosefilm.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rvAdapter = FilmItemAdapter(manufacturerList).apply {
                setOnItemClickListener { _, _, position ->
                    // 更新底部的tab选中
                    setChoosedPosition(position)
                    // 根据不同的品牌选中，执行不同的处理预设
                    applyFilmStyle(getItem(position)?.brandId ?: 0)
                }
            }
            adapter = rvAdapter
        }
    }

    /**
     * 应用不同的滤镜
     */
    private fun applyFilmStyle(brandId: Int) {
        lifecycleScope.launch {
            setEditTabEnable(false)
            binding.ivSelectedpicture.setImageBitmap(
                when (brandId) {
                    BrandId.SONY -> BrandStyle.setSonyStyle(imageUri)
                    BrandId.LEICA -> BrandStyle.setLeicaStyle(imageUri)
                    BrandId.FUJIFILM -> BrandStyle.setFujifilmStyle(imageUri)
                    BrandId.KODAK -> BrandStyle.setKodakStyle(imageUri)
                    BrandId.NIKON -> BrandStyle.setNikonStyle(imageUri)
                    BrandId.CANON -> BrandStyle.setCanonStyle(imageUri)
                    BrandId.LUMIX -> BrandStyle.setLumixStyle(imageUri)
                    BrandId.RICOH -> BrandStyle.setRicohStyle(imageUri)
                    BrandId.HASSELBLAD -> BrandStyle.setHasselBladStyle(imageUri)
                    else -> BrandStyle.setDefaultStyle(imageUri)
                }
            )
            setEditTabEnable(true)
        }
    }

    /**
     * 使下方的操作tab置灰和取消置灰
     */
    fun setEditTabEnable(isEnable: Boolean) {
        binding.llAdjust.alpha = if (isEnable) 1f else 0.5f
        binding.rvChoosefilm.children.forEach {
            it.isClickable = isEnable
        }
    }

    /**
     * 选中后，更新各个控件的可见状态
     */
    private fun switchEditView() {
        binding.ivAddpicture.visibility = View.GONE
        binding.tvExportPic.visibility = View.VISIBLE
        binding.ivSelectedpicture.visibility = View.VISIBLE
        binding.llAdjust.visibility = View.VISIBLE
        rvAdapter.setChoosedPosition(-1)
        binding.rvChoosefilm.adapter = rvAdapter
        Glide.with(this)
            .load(imageUri)
            .into(binding.ivSelectedpicture)
    }

    private fun checkSavePermission(executeSave: () -> Unit) {
        if (Environment.isExternalStorageManager()) {
            // 已经获得了外部存储的管理权限
            infoLog("已经获得了外部存储的管理权限")
            executeSave()
        } else {
            Toast.makeText(
                this,
                "请授予${this.getString(R.string.app_name)}外部存储的管理权限",
                Toast.LENGTH_LONG
            ).show()
            // 没有获得外部存储的管理权限，跳转到设置的请求activity，要求权限
            val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
            startActivity(intent)
        }
    }

    private fun checkNotificationPermission() {
        permissionsLauncher.launch(arrayOf(POST_NOTIFICATIONS))
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun checkStoragePermission() {
        // Permission request logic
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            permissionsLauncher.launch(
                arrayOf(
                    READ_MEDIA_IMAGES,
                    READ_MEDIA_VIDEO,
                    READ_MEDIA_VISUAL_USER_SELECTED
                )
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionsLauncher.launch(arrayOf(READ_MEDIA_IMAGES, READ_MEDIA_VIDEO))
        } else {
            permissionsLauncher.launch(arrayOf(READ_EXTERNAL_STORAGE))
        }
    }
}