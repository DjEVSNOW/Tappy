package com.example.tapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import com.example.tapp.R
import com.example.tapp.databinding.HomeFragmentBinding
import com.example.tapp.model.Destination
import com.example.tapp.model.Transfer
import com.example.tapp.ui.BaseFragment
import com.example.tapp.utils.showToast
import com.google.android.material.chip.Chip
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment<HomeViewModel, HomeFragmentBinding>(HomeViewModel::class) {

    override fun getLayoutRes(): Int = R.layout.home_fragment

    private val tags = mutableListOf<String>()
    private val tagsSelected = mutableListOf<String>()
    private val adapter = GroupAdapter<GroupieViewHolder>()
    private val destinations = listOf(
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
    private fun showTags () {
        binding.destinationTagsCG.removeAllViews()
        tags.forEachIndexed {index,  tag ->
            val chip = layoutInflater.inflate(R.layout.layout_chip_choice, binding.destinationTagsCG, false) as Chip
            chip.text = tag
            chip.id = index
            chip.setOnCheckedChangeListener { buttonView, isChecked ->
                updateList()
                val tag = tags[index]
                showToast(tag)
                if (isChecked)
                    tagsSelected.add(tag)
                else
                    tagsSelected.remove(tag)
                updateList()
            }
            destinationTagsCG.addView(chip as View)

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateList()
        binding.destinationList.adapter = adapter
        destinations.forEach { destination ->
            tags.addAll(destination.tags)
        }
        showTags ()
    }
    private fun updateList()
    {
        adapter.clear()
        destinations.forEach { destination ->
            var isEnabled = true
            if (tagsSelected.size > 0) {
                isEnabled = false
                destination.tags.forEach tags@ { tag ->
                    if (tagsSelected.contains(tag))
                    {
                        isEnabled = true
                        return@tags
                    }
                }
            }
            if (isEnabled) {
                adapter.add(DestinationItem(destination))
            }
        }

    }

}