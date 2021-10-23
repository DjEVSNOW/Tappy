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



    private val adapter = GroupAdapter<GroupieViewHolder>()

    private fun showTags () {
        binding.destinationTagsCG.removeAllViews()
        viewModel.tags.forEachIndexed {index,  tag ->
            val chip = layoutInflater.inflate(R.layout.layout_chip_choice, binding.destinationTagsCG, false) as Chip
            chip.text = tag
            chip.id = index
            chip.setOnCheckedChangeListener { buttonView, isChecked ->
                updateList()
                val tag = viewModel.tags[index]
                showToast(tag)
                if (isChecked)
                    viewModel.tagsSelected.add(tag)
                else
                    viewModel.tagsSelected.remove(tag)
                updateList()
            }
            destinationTagsCG.addView(chip as View)

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateList()
        binding.destinationList.adapter = adapter
        viewModel.destinations.forEach { destination ->
            viewModel.tags.addAll(destination.tags)
        }
        showTags ()
    }
    private fun updateList()
    {
        adapter.clear()
        viewModel.destinations.forEach { destination ->

            if (viewModel.shouldItemBeShown(destination)) {
                adapter.add(DestinationItem(destination))
            }
        }

    }

}