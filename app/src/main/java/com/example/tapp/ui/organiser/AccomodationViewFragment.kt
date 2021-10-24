package com.example.tapp.ui.organiser

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.AssetManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.system.Os.read
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.isInvisible
import androidx.fragment.app.findFragment
import androidx.lifecycle.lifecycleScope
import com.example.tapp.R
import com.example.tapp.databinding.AccomodationViewFragmentBinding
import com.example.tapp.ui.BaseFragment
import com.tomtom.online.sdk.common.location.LatLng
import com.tomtom.online.sdk.map.*
import com.tomtom.online.sdk.routing.OnlineRoutingApi
import com.tomtom.online.sdk.routing.RoutingException
import com.tomtom.online.sdk.routing.route.*
import com.tomtom.online.sdk.routing.route.description.TravelMode
import kotlinx.android.synthetic.main.accomodation_view_fragment.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.*


class AccomodationViewFragment : BaseFragment<AccomodationViewViewModel, AccomodationViewFragmentBinding>(
    AccomodationViewViewModel::class
)  {

    override fun getLayoutRes(): Int = R.layout.accomodation_view_fragment
    private lateinit var tomtomMap : TomtomMap
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = map.children.first().findFragment<MapFragment>()
        mapFragment.getAsyncMap { mTomtomMap ->
            tomtomMap = mTomtomMap
            tomtomMap.isMyLocationEnabled = true
            tomtomMap.uiSettings.logoView.applyInvertedLogo()
            tomtomMap.poiSettings.hide()
            val markerBalloon = SimpleMarkerBalloon("Ленинградская")
            val location = LatLng(59.9569284555606, 30.341944370456275)
            tomtomMap.addMarker(
                MarkerBuilder(location)
                    .markerBalloon(markerBalloon)
            )
            tomtomMap.centerOn(
                CameraPosition.builder()
                    .focusPosition(location)
                    .zoom(MapConstants.DEFAULT_ZOOM_LEVEL)
                    .build()
            )
            lifecycleScope.launch {
                while (true) {
                    delay(750)
                    if (tomtomMap.userLocation != null) {

                        val routeDescriptor = RouteDescriptor.Builder()
                            .travelMode(TravelMode.CAR)
                            .considerTraffic(false)
                            .build()

                        val routeCalculationDescriptor = RouteCalculationDescriptor.Builder()
                            .routeDescription(routeDescriptor)
                            .build()
                        val routingApi = OnlineRoutingApi.create(
                            requireContext(),
                            "AwDIXPNqAIMFsVtO3elZXNHQUfEi4qGG"
                        )
                        val spec = RouteSpecification.Builder(
                            LatLng(
                                tomtomMap.userLocation!!.latitude,
                                tomtomMap.userLocation!!.longitude
                            ), location
                        )
                            .routeCalculationDescriptor(routeCalculationDescriptor)
                            .build()
                        val route = routingApi.planRoute(spec, object : RouteCallback {
                            override fun onError(error: RoutingException) {

                            }

                            override fun onSuccess(routePlan: RoutePlan) {
                                val routeBuilder = RouteBuilder(
                                    routePlan.routes.first().getCoordinates()
                                )
                                    .style(RouteStyle.DEFAULT_ROUTE_STYLE)
                                val mapRoute = tomtomMap.addRoute(routeBuilder)

                            }

                        })


                        tomtomMap.centerOn(
                            CameraPosition.builder()
                                .focusPosition(
                                    LatLng(
                                        tomtomMap.userLocation!!.latitude,
                                        tomtomMap.userLocation!!.longitude
                                    )
                                )
                                .zoom(MapConstants.DEFAULT_ZOOM_LEVEL)
                                .build()
                        )
                        break
                    }
                }
            }
        }
        bookingIV.setOnClickListener {
            if (pdfv.visibility == View.VISIBLE) {
                pdfv.visibility = View.GONE
            } else {
                pdfv.visibility = View.VISIBLE
            }
        }
        pdfv.fromAsset("booking_com_confirmation.pdf").load();
    }


}