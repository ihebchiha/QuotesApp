package com.ihebchiha.hiltapp.repository

import com.ihebchiha.hiltapp.data.database.remote.ProfileDataSource
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val profileDataSource: ProfileDataSource) {
}