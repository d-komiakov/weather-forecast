package tpformy.bunghole.weatherforecast.forecast.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_forecast.*
import tpformy.bunghole.weatherforecast.ForecastApp
import tpformy.bunghole.weatherforecast.R
import tpformy.bunghole.weatherforecast.forecast.ForecastPresenter
import tpformy.bunghole.weatherforecast.forecast.ForecastView
import javax.inject.Inject

class ForecastActivity : AppCompatActivity(), ForecastView {

    @Inject lateinit var presenter: ForecastPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as ForecastApp).getComponent().inject(this)
        setContentView(R.layout.activity_forecast)
        buttonUpdate.setOnClickListener { presenter.updateForecast() }
    }

    override fun onResume() {
        super.onResume()
        presenter.bindView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.releaseView()
    }

    override fun showForecast(temperature: Int, windSpeed: Int, clouds: Int, isUpToDate: Boolean) {
        textTemperature.text = getString(R.string.temperature, temperature)
        textWindSpeed.text = getString(R.string.wind_speed, windSpeed)
        textClouds.text = getString(R.string.clouds, clouds)
        if (!isUpToDate) {
            Toast.makeText(this, R.string.message_can_not_update_data, Toast.LENGTH_LONG).show()
        }
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showError() {
        Toast.makeText(this, R.string.message_error, Toast.LENGTH_LONG).show()
    }
}