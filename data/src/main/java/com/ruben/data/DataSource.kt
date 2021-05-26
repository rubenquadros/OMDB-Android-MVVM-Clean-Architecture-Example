package com.ruben.data

import com.ruben.remote.NetworkManager

/**
 * Created by ruben.quadros on 26/05/21.
 **/
interface DataSource {
    fun api(): NetworkManager
}