package com.ruben.domain.model.base

/**
 * Created by ruben.quadros on 28/05/21.
 **/
data class Record<out Record>(
  val status: StatusRecord,
  val data: Record?
)