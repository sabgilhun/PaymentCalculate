package com.example.socarpaymentcalculate.data.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.socarpaymentcalculate.R
import com.example.socarpaymentcalculate.data.enums.CarType.*

enum class CarModel(
    val type: CarType,
    @StringRes val nameRes: Int,
    @DrawableRes val imageRes: Int,
    val farePerKiloMeter: Int,
    val weekdayFarePerTenMinute: Int,
    val weekendFarePerTenMinute: Int
) {

    /* SUBCOMPACT */
    ALL_NEW_MORNING(
        type = SUBCOMPACT,
        nameRes = R.string.all_new_morning,
        imageRes = R.drawable.img_all_new_morning,
        farePerKiloMeter = 170,
        weekdayFarePerTenMinute = 370,
        weekendFarePerTenMinute = 620
    ),
    NEXT_SPARK(
        type = SUBCOMPACT,
        nameRes = R.string.next_spark,
        imageRes = R.drawable.img_next_spark,
        farePerKiloMeter = 170,
        weekdayFarePerTenMinute = 370,
        weekendFarePerTenMinute = 620
    ),
    THE_NEW_SPARK(
        type = SUBCOMPACT,
        nameRes = R.string.the_new_spark,
        imageRes = R.drawable.img_the_new_spark,
        farePerKiloMeter = 180,
        weekdayFarePerTenMinute = 370,
        weekendFarePerTenMinute = 620
    ),
    RAY(
        type = SUBCOMPACT,
        nameRes = R.string.ray,
        imageRes = R.drawable.img_ray,
        farePerKiloMeter = 180,
        weekdayFarePerTenMinute = 370,
        weekendFarePerTenMinute = 620
    ),
    THE_NEW_RAY(
        type = SUBCOMPACT,
        nameRes = R.string.the_new_ray,
        imageRes = R.drawable.img_the_new_ray,
        farePerKiloMeter = 180,
        weekdayFarePerTenMinute = 370,
        weekendFarePerTenMinute = 620
    ),

    /* COMPACT */
    PRIDE(
        type = COMPACT,
        nameRes = R.string.pride,
        imageRes = R.drawable.img_pride,
        farePerKiloMeter = 180,
        weekdayFarePerTenMinute = 430,
        weekendFarePerTenMinute = 710
    ),
    CLIO(
        type = COMPACT,
        nameRes = R.string.clio,
        imageRes = R.drawable.img_clio,
        farePerKiloMeter = 180,
        weekdayFarePerTenMinute = 500,
        weekendFarePerTenMinute = 830
    ),

    /* SEMI_MIDSIZE */
    I_30(
        type = SEMI_MIDSIZE,
        nameRes = R.string.i_30,
        imageRes = R.drawable.img_i30,
        farePerKiloMeter = 180,
        weekdayFarePerTenMinute = 650,
        weekendFarePerTenMinute = 1080
    ),
    ALL_NEW_K3(
        type = SEMI_MIDSIZE,
        nameRes = R.string.all_new_k3,
        imageRes = R.drawable.img_all_new_k3,
        farePerKiloMeter = 180,
        weekdayFarePerTenMinute = 470,
        weekendFarePerTenMinute = 790
    ),
    AVANTE_AD(
        type = SEMI_MIDSIZE,
        nameRes = R.string.avante_ad,
        imageRes = R.drawable.img_avante_ad,
        farePerKiloMeter = 180,
        weekdayFarePerTenMinute = 470,
        weekendFarePerTenMinute = 790
    ),
    THE_NEW_AVANTE(
        type = SEMI_MIDSIZE,
        nameRes = R.string.the_new_avante,
        imageRes = R.drawable.img_the_new_avante,
        farePerKiloMeter = 180,
        weekdayFarePerTenMinute = 470,
        weekendFarePerTenMinute = 790
    ),

    /* ELECTRIC_VEHICLE */
    IONIC_ELECTRIC(
        type = ELECTRIC_VEHICLE,
        nameRes = R.string.ionic_electric,
        imageRes = R.drawable.img_ionic_electric,
        farePerKiloMeter = 0,
        weekdayFarePerTenMinute = 900,
        weekendFarePerTenMinute = 1500
    ),
    VOLT_EV(
        type = ELECTRIC_VEHICLE,
        nameRes = R.string.volt_ev,
        imageRes = R.drawable.img_volt_ev,
        farePerKiloMeter = 0,
        weekdayFarePerTenMinute = 1000,
        weekendFarePerTenMinute = 1660
    ),

    /* MIDSIZE */
    K5(
        type = MIDSIZE,
        nameRes = R.string.k5,
        imageRes = R.drawable.img_k5,
        farePerKiloMeter = 190,
        weekdayFarePerTenMinute = 650,
        weekendFarePerTenMinute = 1080
    ),
    K5_LPG(
        type = MIDSIZE
        , nameRes = R.string.k5_lpg,
        imageRes = R.drawable.img_k5,
        farePerKiloMeter = 150,
        weekdayFarePerTenMinute = 650,
        weekendFarePerTenMinute = 1080
    ),
    SONATA_NEW_RISE(
        type = MIDSIZE,
        nameRes = R.string.sonata_new_rise,
        imageRes = R.drawable.img_sonata_new_rise,
        farePerKiloMeter = 200,
        weekdayFarePerTenMinute = 650,
        weekendFarePerTenMinute = 1080
    ),
    SONATA_NEW_RISE_LPG(
        type = MIDSIZE,
        nameRes = R.string.sonata_new_rise_lpg,
        imageRes = R.drawable.img_sonata_new_rise,
        farePerKiloMeter = 150,
        weekdayFarePerTenMinute = 650,
        weekendFarePerTenMinute = 1080
    ),
    MALIBU(
        type = MIDSIZE,
        nameRes = R.string.malibu,
        imageRes = R.drawable.img_malibu,
        farePerKiloMeter = 190,
        weekdayFarePerTenMinute = 800,
        weekendFarePerTenMinute = 1330
    ),
    SM6(
        type = MIDSIZE,
        nameRes = R.string.sm6,
        imageRes = R.drawable.img_sm6,
        farePerKiloMeter = 200,
        weekdayFarePerTenMinute = 900,
        weekendFarePerTenMinute = 1500
    ),

    /* SEMI_FULLSIZE */
    STINGER(
        type = SEMI_FULLSIZE,
        nameRes = R.string.stinger,
        imageRes = R.drawable.img_stinger,
        farePerKiloMeter = 210,
        weekdayFarePerTenMinute = 1250,
        weekendFarePerTenMinute = 2080
    ),
    K7(
        type = SEMI_FULLSIZE,
        nameRes = R.string.k7,
        imageRes = R.drawable.img_k7,
        farePerKiloMeter = 170,
        weekdayFarePerTenMinute = 1000,
        weekendFarePerTenMinute = 1660
    ),
    GRANDEUR_IG(
        type = SEMI_FULLSIZE,
        nameRes = R.string.grandeur_ig,
        imageRes = R.drawable.img_grandeur,
        farePerKiloMeter = 210,
        weekdayFarePerTenMinute = 1250,
        weekendFarePerTenMinute = 2080
    ),
    GRANDEUR_IG_LPG(
        type = SEMI_FULLSIZE,
        nameRes = R.string.grandeur_ig_lpg,
        imageRes = R.drawable.img_grandeur,
        farePerKiloMeter = 170,
        weekdayFarePerTenMinute = 1250,
        weekendFarePerTenMinute = 2080
    ),

    /* FULLSIZE */
    G80(
        type = FULLSIZE,
        nameRes = R.string.g80,
        imageRes = R.drawable.img_g80,
        farePerKiloMeter = 210,
        weekdayFarePerTenMinute = 1750,
        weekendFarePerTenMinute = 2910
    ),

    /* COMPACT_SUV */
    QM3(
        type = COMPACT_SUV,
        nameRes = R.string.qm3_diesel,
        imageRes = R.drawable.img_qm3,
        farePerKiloMeter = 130,
        weekdayFarePerTenMinute = 900,
        weekendFarePerTenMinute = 1500
    ),
    STONIC(
        type = COMPACT_SUV,
        nameRes = R.string.stonic,
        imageRes = R.drawable.img_stonic,
        farePerKiloMeter = 170,
        weekdayFarePerTenMinute = 900,
        weekendFarePerTenMinute = 1500
    ),
    STONIC_DIESEL(
        type = COMPACT_SUV,
        nameRes = R.string.starex_diesel,
        imageRes = R.drawable.img_stonic,
        farePerKiloMeter = 130,
        weekdayFarePerTenMinute = 900,
        weekendFarePerTenMinute = 1500
    ),
    KONA(
        type = COMPACT_SUV,
        nameRes = R.string.kona,
        imageRes = R.drawable.img_kona,
        farePerKiloMeter = 170,
        weekdayFarePerTenMinute = 900,
        weekendFarePerTenMinute = 1500
    ),
    TRAX(
        type = COMPACT_SUV,
        nameRes = R.string.trax_diesel,
        imageRes = R.drawable.img_no_photo,
        farePerKiloMeter = 170,
        weekdayFarePerTenMinute = 900,
        weekendFarePerTenMinute = 1500
    ),
    TIVOLI(
        type = COMPACT_SUV,
        nameRes = R.string.tivoli,
        imageRes = R.drawable.img_tivoli,
        farePerKiloMeter = 180,
        weekdayFarePerTenMinute = 900,
        weekendFarePerTenMinute = 1500
    ),
    TIVOLI_DIESEL(
        type = COMPACT_SUV,
        nameRes = R.string.tivoli_diesel,
        imageRes = R.drawable.img_tivoli,
        farePerKiloMeter = 170,
        weekdayFarePerTenMinute = 900,
        weekendFarePerTenMinute = 1500
    ),
    ROOF_BOX_TIVOLI(
        type = COMPACT_SUV,
        nameRes = R.string.roof_box_tivoli,
        imageRes = R.drawable.img_roof_box_tivoli,
        farePerKiloMeter = 180,
        weekdayFarePerTenMinute = 900,
        weekendFarePerTenMinute = 1500
    ),

    /* SEMI_MIDSIZE_SUV */
    SPORTAGE_THE_BOLD(
        type = SEMI_MIDSIZE_SUV,
        nameRes = R.string.sportage_the_bold,
        imageRes = R.drawable.img_sportage_the_bold,
        farePerKiloMeter = 200,
        weekdayFarePerTenMinute = 900,
        weekendFarePerTenMinute = 1500
    ),
    SPORTAGE(
        type = SEMI_MIDSIZE_SUV,
        nameRes = R.string.sportage_diesel,
        imageRes = R.drawable.img_sportage,
        farePerKiloMeter = 170,
        weekdayFarePerTenMinute = 900,
        weekendFarePerTenMinute = 1500
    ),
    TUCSON(
        type = SEMI_MIDSIZE_SUV,
        nameRes = R.string.tucson_diesel,
        imageRes = R.drawable.img_tucson,
        farePerKiloMeter = 170,
        weekdayFarePerTenMinute = 900,
        weekendFarePerTenMinute = 1500
    ),

    /* MIDSIZE_SUV */
    SANTAFE(
        type = MIDSIZE_SUV,
        nameRes = R.string.santafe,
        imageRes = R.drawable.img_santafe,
        farePerKiloMeter = 210,
        weekdayFarePerTenMinute = 1050,
        weekendFarePerTenMinute = 1750
    ),
    SORENTO(
        type = MIDSIZE_SUV,
        nameRes = R.string.sorento,
        imageRes = R.drawable.img_sorento,
        farePerKiloMeter = 210,
        weekdayFarePerTenMinute = 1050,
        weekendFarePerTenMinute = 1750
    ),
    SORENTO_7(
        type = MIDSIZE_SUV,
        nameRes = R.string.sorento_7,
        imageRes = R.drawable.img_sorento,
        farePerKiloMeter = 210,
        weekdayFarePerTenMinute = 1050,
        weekendFarePerTenMinute = 1750
    ),

    /* VAN */
    STAREX(
        type = VAN,
        nameRes = R.string.starex_diesel,
        imageRes = R.drawable.img_starex,
        farePerKiloMeter = 250,
        weekdayFarePerTenMinute = 1150,
        weekendFarePerTenMinute = 1910
    ),
    GRAND_STAREX(
        type = VAN,
        nameRes = R.string.grand_starex_diesel,
        imageRes = R.drawable.img_starex,
        farePerKiloMeter = 250,
        weekdayFarePerTenMinute = 1150,
        weekendFarePerTenMinute = 1910
    ),
    CARNIVAL(
        type = VAN,
        nameRes = R.string.carnival_diesel,
        imageRes = R.drawable.img_carnival,
        farePerKiloMeter = 230,
        weekdayFarePerTenMinute = 1100,
        weekendFarePerTenMinute = 1830
    ),
    WHEELCHAIR_SLOPE_CARNIVAL(
        type = VAN,
        nameRes = R.string.wheelchair_slope_carnival_diesel,
        imageRes = R.drawable.img_heelchair_slope_carnival,
        farePerKiloMeter = 230,
        weekdayFarePerTenMinute = 1100,
        weekendFarePerTenMinute = 1830
    ),

    /* HIGH_CLASS */
    MINI_CLUB_MAN(
        type = HIGH_CLASS,
        nameRes = R.string.mini_club_man,
        imageRes = R.drawable.img_mini_club_man,
        farePerKiloMeter = 230,
        weekdayFarePerTenMinute = 1300,
        weekendFarePerTenMinute = 2160
    ),
    BENZ_C200(
        type = HIGH_CLASS,
        nameRes = R.string.benz_c200,
        imageRes = R.drawable.img_benz_c200,
        farePerKiloMeter = 230,
        weekdayFarePerTenMinute = 1750,
        weekendFarePerTenMinute = 2910
    ),
    JEEP_RENEGADE(
        type = HIGH_CLASS,
        nameRes = R.string.jeep_renegade,
        imageRes = R.drawable.img_jeep_renegade,
        farePerKiloMeter = 240,
        weekdayFarePerTenMinute = 1300,
        weekendFarePerTenMinute = 2160
    ),
    JAGUAR_E_PACE(
        type = HIGH_CLASS,
        nameRes = R.string.jaguar_e_pace,
        imageRes = R.drawable.img_no_photo,
        farePerKiloMeter = 230,
        weekdayFarePerTenMinute = 1950,
        weekendFarePerTenMinute = 3250
    )

}