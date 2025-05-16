package com.stephen.filmsimulation.data

import com.stephen.filmsimulation.R

data class FilmItemBean(val brandId: Int, val brandName: String, val brandIconResId: Int)

object BrandId {
    const val LEICA = 1000
    const val SONY = 1001
    const val FUJIFILM = 1002
    const val KODAK = 1003
    const val NIKON = 1004
    const val CANON = 1005
    const val LUMIX = 1006
    const val RICOH = 1007
    const val HASSELBLAD = 1008
}

val manufacturerList = listOf(
    FilmItemBean(BrandId.LEICA, "徕卡", R.drawable.ic_leica),
    FilmItemBean(BrandId.SONY, "索尼", R.drawable.ic_sony),
    FilmItemBean(BrandId.FUJIFILM, "富士", R.drawable.ic_fujifilm),
    FilmItemBean(BrandId.KODAK, "柯达", R.drawable.ic_kodak),
    FilmItemBean(BrandId.NIKON, "尼康", R.drawable.ic_nikon),
    FilmItemBean(BrandId.CANON, "佳能", R.drawable.ic_canon),
    FilmItemBean(BrandId.LUMIX, "松下", R.drawable.ic_panasonic),
    FilmItemBean(BrandId.RICOH, "理光", R.drawable.ic_ricoh),
    FilmItemBean(BrandId.HASSELBLAD, "哈苏", R.drawable.ic_hasselblad)
)
