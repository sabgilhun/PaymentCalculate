package com.example.socarpaymentcalculate.data.enums

import androidx.annotation.StringRes
import com.example.socarpaymentcalculate.R

enum class CarType(@StringRes val nameRes: Int) {
    SUBCOMPACT(nameRes = R.string.subcompact),
    COMPACT(nameRes = R.string.compact),
    SEMI_MIDSIZE(nameRes = R.string.semi_midsize),
    ELECTRIC_VEHICLE(nameRes = R.string.electric_vehicle),
    MIDSIZE(nameRes = R.string.midsize),
    SEMI_FULLSIZE(nameRes = R.string.semi_fullsize),
    FULLSIZE(nameRes = R.string.fullsize),
    COMPACT_SUV(nameRes = R.string.compact_suv),
    SEMI_MIDSIZE_SUV(nameRes = R.string.semi_midsize_suv),
    MIDSIZE_SUV(nameRes = R.string.midsize_suv),
    VAN(nameRes = R.string.van),
    HIGH_CLASS(nameRes = R.string.high_class)
}