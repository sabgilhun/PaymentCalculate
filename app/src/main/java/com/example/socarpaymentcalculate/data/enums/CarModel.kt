package com.example.socarpaymentcalculate.data.enums

import androidx.annotation.IdRes
import com.example.socarpaymentcalculate.R
import com.example.socarpaymentcalculate.data.enums.CarType.*

enum class CarModel(val type: CarType, @IdRes val nameRes: Int, val farePerKiloMeter: Int) {

    /* SUBCOMPACT */
    ALL_NEW_MORNING(
        type = SUBCOMPACT,
        nameRes = R.string.all_new_morning,
        farePerKiloMeter = 170
    ),
    NEXT_SPARK(
        type = SUBCOMPACT,
        nameRes = R.string.next_spark,
        farePerKiloMeter = 170
    ),
    THE_NEW_SPARK(
        type = SUBCOMPACT,
        nameRes = R.string.the_new_spark,
        farePerKiloMeter = 180
    ),
    RAY(
        type = SUBCOMPACT,
        nameRes = R.string.ray,
        farePerKiloMeter = 180
    ),
    THE_NEW_RAY(
        type = SUBCOMPACT,
        nameRes = R.string.the_new_ray,
        farePerKiloMeter = 180
    ),

    /* COMPACT */
    PRIDE(
        type = COMPACT,
        nameRes = R.string.pride,
        farePerKiloMeter = 180
    ),
    CLIO(
        type = COMPACT,
        nameRes = R.string.clio
        , farePerKiloMeter = 180
    ),

    /* SEMI_MIDSIZE */
    I_30(
        type = SEMI_MIDSIZE,
        nameRes = R.string.i_30,
        farePerKiloMeter = 180
    ),
    ALL_NEW_K3(
        type = SEMI_MIDSIZE,
        nameRes = R.string.all_new_k3,
        farePerKiloMeter = 180
    ),
    AVANTE_AD(
        type = SEMI_MIDSIZE,
        nameRes = R.string.avante_ad,
        farePerKiloMeter = 180
    ),
    THE_NEW_AVANTE(
        type = SEMI_MIDSIZE,
        nameRes = R.string.the_new_avante,
        farePerKiloMeter = 180
    ),

    /* ELECTRIC_VEHICLE */
    IONIC_ELECTRIC(
        type = ELECTRIC_VEHICLE,
        nameRes = R.string.ionic_electric,
        farePerKiloMeter = 0
    ),
    VOLT_EV(
        type = ELECTRIC_VEHICLE,
        nameRes = R.string.volt_ev,
        farePerKiloMeter = 0
    ),

    /* MIDSIZE */
    K5(
        type = MIDSIZE,
        nameRes = R.string.k5,
        farePerKiloMeter = 190
    ),
    K5_LPG(
        type = MIDSIZE
        , nameRes = R.string.k5_lpg,
        farePerKiloMeter = 150
    ),
    SONATA_NEW_RISE(
        type = MIDSIZE,
        nameRes = R.string.sonata_new_rise,
        farePerKiloMeter = 200
    ),
    SONATA_NEW_RISE_LPG(
        type = MIDSIZE,
        nameRes = R.string.sonata_new_rise_lpg,
        farePerKiloMeter = 150
    ),
    MALIBU(
        type = MIDSIZE,
        nameRes = R.string.malibu,
        farePerKiloMeter = 190
    ),
    SM6(
        type = MIDSIZE,
        nameRes = R.string.sm6,
        farePerKiloMeter = 200
    ),

    /* SEMI_FULLSIZE */
    STINGER(
        type = SEMI_FULLSIZE,
        nameRes = R.string.stinger,
        farePerKiloMeter = 210
    ),
    K7(
        type = SEMI_FULLSIZE,
        nameRes = R.string.k7,
        farePerKiloMeter = 170
    ),
    GRANDEUR_IG(
        type = SEMI_FULLSIZE,
        nameRes = R.string.grandeur_ig,
        farePerKiloMeter = 210
    ),
    GRANDEUR_IG_LPG(
        type = SEMI_FULLSIZE,
        nameRes = R.string.grandeur_ig_lpg,
        farePerKiloMeter = 170
    ),

    /* FULLSIZE */
    G80(
        type = FULLSIZE,
        nameRes = R.string.g80,
        farePerKiloMeter = 210
    ),

    /* COMPACT_SUV */
    QM3(
        type = COMPACT_SUV,
        nameRes = R.string.qm3_diesel,
        farePerKiloMeter = 130
    ),
    STONIC(
        type = COMPACT_SUV,
        nameRes = R.string.stonic,
        farePerKiloMeter = 170
    ),
    STONIC_DIESEL(
        type = COMPACT_SUV,
        nameRes = R.string.starex_diesel,
        farePerKiloMeter = 130
    ),
    KONA(
        type = COMPACT_SUV,
        nameRes = R.string.kona,
        farePerKiloMeter = 170
    ),
    TRAX(
        type = COMPACT_SUV,
        nameRes = R.string.trax_diesel,
        farePerKiloMeter = 170
    ),
    TIVOLI(
        type = COMPACT_SUV,
        nameRes = R.string.tivoli,
        farePerKiloMeter = 180
    ),
    TIVOLI_DIESEL(
        type = COMPACT_SUV,
        nameRes = R.string.tivoli_diesel,
        farePerKiloMeter = 170
    ),
    ROOF_BOX_TIVOLI(
        type = COMPACT_SUV,
        nameRes = R.string.roof_box_tivoli,
        farePerKiloMeter = 180
    ),

    /* SEMI_MIDSIZE_SUV */
    SPORTAGE_THE_BOLD(
        type = SEMI_MIDSIZE_SUV,
        nameRes = R.string.sportage_the_bold,
        farePerKiloMeter = 200
    ),
    SPORTAGE(
        type = SEMI_MIDSIZE_SUV,
        nameRes = R.string.sportage_diesel,
        farePerKiloMeter = 170
    ),
    TUCSON(
        type = SEMI_MIDSIZE_SUV,
        nameRes = R.string.tucson_diesel,
        farePerKiloMeter = 70
    ),

    /* MIDSIZE_SUV */
    SANTAFE(
        type = MIDSIZE_SUV,
        nameRes = R.string.santafe,
        farePerKiloMeter = 210
    ),
    SORENTO(
        type = MIDSIZE_SUV,
        nameRes = R.string.sorento,
        farePerKiloMeter = 210
    ),
    SORENTO_7(
        type = MIDSIZE_SUV,
        nameRes = R.string.sorento_7,
        farePerKiloMeter = 210
    ),

    /* VAN */
    STAREX(
        type = VAN,
        nameRes = R.string.starex_diesel,
        farePerKiloMeter = 250
    ),
    GRAND_STAREX(
        type = VAN,
        nameRes = R.string.grand_starex_diesel,
        farePerKiloMeter = 250
    ),
    CARNIVAL(
        type = VAN,
        nameRes = R.string.carnival_diesel,
        farePerKiloMeter = 230
    ),
    WHEELCHAIR_SLOPE_CARNIVAL(
        type = VAN,
        nameRes = R.string.wheelchair_slope_carnival_diesel,
        farePerKiloMeter = 230
    ),

    /* HIGH_CLASS */
    MINI_CLUB_MAN(
        type = HIGH_CLASS,
        nameRes = R.string.mini_club_man,
        farePerKiloMeter = 230
    ),
    BENZ_C200(
        type = HIGH_CLASS,
        nameRes = R.string.benz_c200,
        farePerKiloMeter = 230
    ),
    JEEP_RENEGADE(
        type = HIGH_CLASS,
        nameRes = R.string.jeep_renegade,
        farePerKiloMeter = 240
    ),
    JAGUAR_E_PACE(
        type = HIGH_CLASS,
        nameRes = R.string.jaguar_e_pace,
        farePerKiloMeter = 230
    )

}