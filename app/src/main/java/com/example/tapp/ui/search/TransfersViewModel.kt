package com.example.tapp.ui.search

import androidx.lifecycle.ViewModel
import com.example.tapp.data.ApiRepository
import com.example.tapp.model.Destination
import com.example.tapp.model.Transfer
import com.example.tapp.ui.BaseViewModel

class TransfersViewModel(val apiRepository: ApiRepository) : BaseViewModel(apiRepository) {
	
	val tags = mutableListOf<String>()
	val tagsSelected = mutableListOf<String>()
	fun shouldItemBeShown(transfer : Transfer) : Boolean {
		var isEnabled = true
		if (tagsSelected.size > 0) {
			isEnabled = false
			tagsSelected.forEach tags@ { tag ->
				if (transfer.tags.contains(tag))
				{
					isEnabled = true
					
				}
			}
		}
		return isEnabled
	}
}