package com.example.socarpaymentcalculate.viewmodel.fare

import com.example.socarpaymentcalculate.common.Action
import com.example.socarpaymentcalculate.data.enums.CarModel
import com.example.socarpaymentcalculate.data.enums.CarType
import com.example.socarpaymentcalculate.data.model.Route

class DetermineRouteAction(val route: Route) : Action

class SelectCarTypeAction(val carType: CarType) : Action

class SelectCarModelAction(val carModel: CarModel) : Action
