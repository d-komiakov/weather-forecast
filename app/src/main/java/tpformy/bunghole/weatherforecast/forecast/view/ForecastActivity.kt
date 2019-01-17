package tpformy.bunghole.weatherforecast.forecast.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun configure() {
        val api = ApiFactory.createApi()
        val persistence = AndroidPersistence(getPreferences(Context.MODE_PRIVATE))
        val store = ForecastStoreImpl(api, persistence)
        val taskManager = TaskManagerFactory.createTaskManager()
        presenter = ForecastPresenterImpl(store, taskManager)
    }
}