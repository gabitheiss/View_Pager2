package com.example.view_pager2.di

data class ListImages(
    var title : String,
    var description : String,
    var type: TabType
)

enum class TabType(value: String) {
    CARD("card"),
    PAY("pay"),
    BILLET("billet"),
    BONUS("bonus"),
    PIX("pix"),
}
