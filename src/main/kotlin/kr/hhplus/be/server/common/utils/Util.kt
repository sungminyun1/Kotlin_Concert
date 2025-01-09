package kr.hhplus.be.server.common.utils

import java.util.*

class Util {

    companion object {

        fun generateUUID(length: Int): String {
            val uuid = UUID.randomUUID().toString().replace("-", "")
            return if (length > 0 && length <= uuid.length) {
                uuid.substring(0, length)
            } else {
                throw IllegalArgumentException("Invalid length: $length. Must be between 1 and ${uuid.length}")
            }
        }

    }
}