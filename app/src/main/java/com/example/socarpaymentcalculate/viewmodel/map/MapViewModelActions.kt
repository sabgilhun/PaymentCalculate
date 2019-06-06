package com.example.socarpaymentcalculate.viewmodel.map

import com.example.socarpaymentcalculate.common.Action
import com.example.socarpaymentcalculate.data.model.Poi

class SetStartPointAction(val startPoint: Poi) : Action

class SetEndPointAction(val endPoint: Poi) : Action

class ClickSearchButtonAction : Action