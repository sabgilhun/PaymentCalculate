package com.example.socarpaymentcalculate.viewmodel.map

import com.example.socarpaymentcalculate.common.Action
import com.example.socarpaymentcalculate.data.model.Poi

class SetDeparturePointAction(val departurePoint: Poi) : Action

class SetDestinationAction(val destination: Poi) : Action

class ClickSearchButtonAction : Action