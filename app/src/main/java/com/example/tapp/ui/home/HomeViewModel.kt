package com.example.tapp.ui.home

import com.example.tapp.data.ApiRepository
import com.example.tapp.model.Destination
import com.example.tapp.model.Transfer
import com.example.tapp.ui.BaseViewModel

class HomeViewModel(private val apiRepository: ApiRepository) : BaseViewModel(apiRepository) {
    val tags = mutableListOf<String>()
    val tagsSelected = mutableListOf<String>()
    val destinations = listOf(
        Destination(0,
            "Анапа",
            "https://www.flagman-travel.ru/upload/resize_cache/iblock/618/1024_682_199139c8f4ec2b19729d2246557347328/618d53a10f041ca2814aeee7dda227bf.jpg",
            100000,15, Transfer.PLANE, listOf("Детям","Пляж","Развлечения")),
        Destination(1,
            "Новгород",
            "https://www.advantour.com/russia/images/veliky-novgorod/veliky-novgorod.jpg",
            1000,3, Transfer.CAR, listOf("История","На выходные","Для всех")),
        Destination(2,
            "Новгород",
            "https://www.advantour.com/russia/images/veliky-novgorod/veliky-novgorod.jpg",
            1000,5, Transfer.BUS, listOf("Эко","Активный","Для взрослых")),
        Destination(3,
            "Казань",
            "https://flysmartavia.com/media/images/city/20200707_kaz.jpg",
            30000,13, Transfer.PLANE, listOf("Гастрономичсекий","Длинный","Для всех")),
        Destination(4,
            "Калининград",
            "https://visit-kaliningrad.ru/upload/0_1003fe_91733604_orig.jpg",
            50000,7, Transfer.PLANE, listOf("История")),

        )
}