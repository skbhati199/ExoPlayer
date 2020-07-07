package com.infoskillstechnology.sinetop.repository

import com.infoskillstechnology.sinetop.mock.Mock
import com.infoskillstechnology.sinetop.model.StoriesDataModel
import javax.inject.Inject

class DataRepository @Inject constructor(private val mock: Mock) {
    fun getStoriesData(): ArrayList<StoriesDataModel>? {
        val dataList = mock.loadMockData()
        return dataList
    }
}