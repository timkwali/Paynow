package com.timkwali.paynow.common.data.api.model.response

data class ResponseModel<T>(val status:Boolean, val message:String, val data: T)
