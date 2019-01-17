package tpformy.bunghole.weatherforecast.forecast.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_forecast.*
import tpformy.bunghole.weatherforecast.R
import tpformy.bunghole.weatherforecast.forecast.ForecastPresenter
import tpformy.bunghole.weatherforecast.forecast.ForecastView
import tpformy.bunghole.weatherforecast.forecast.model.ForecastStoreImpl
import tpformy.bunghole.weatherforecast.forecast.presenter.ForecastPresenterImpl
import tpformy.bunghole.weatherforecast.utils.net.ApiFactory
import tpformy.bunghole.weatherforecast.utils.persistence.AndroidPersistence
import tpformy.bunghole.weatherforecast.utils.task.TaskManagerFactory

class ForecastActivity : AppCompatActivity(), ForecastView {

    private lateinit var presenter: ForecastPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        configure()
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

    private fun configure() {
        val api = ApiFactory.createApi()
        val persistence = AndroidPersistence(getPreferences(Context.MODE_PRIVATE))
        val store = ForecastStoreImpl(api, persistence)
        val taskManager = TaskManagerFactory.createTaskManager()
        presenter = ForecastPresenterImpl(store, taskManager)
    }
}