package com.micky.pantomim.ui.game

val correctVibrate   = longArrayOf(200, 100, 200, 100)
val panicVibrate     = longArrayOf(0,200)
val gameOverVibrate  = longArrayOf(0, 1000)
val gameNoVibrate    = longArrayOf(0,500)

enum class BuzzType (val pattern: LongArray) {

    CORRECT_VIBRATE (correctVibrate),
    PANIC_VIBRATE (panicVibrate),
    NO_VIBRATE (gameNoVibrate),
    GAME_OVER_VIBRATE (gameOverVibrate)

}