package invin.mvvm_invin.ext

import java.util.concurrent.TimeUnit


// 5분
inline val TimeUnit.EXPIRED_TIME_LIMIT: Long
    get() = TimeUnit.SECONDS.toSeconds(50L)