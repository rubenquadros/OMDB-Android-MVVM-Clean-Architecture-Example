package com.ruben.data

import com.ruben.remote.NetworkManager
import com.ruben.remote.NetworkManagerImpl

/**
 * Created by ruben.quadros on 26/05/21.
 **/
class DataSourceImpl: DataSource {

    private val api: NetworkManager = NetworkManagerImpl()

    override fun api(): NetworkManager {
        return api
    }
}