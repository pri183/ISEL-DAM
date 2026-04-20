package dam_A51587.coolweatherapp

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.google.gson.Gson
import java.io.InputStreamReader
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    var day: Boolean = true
    val coordenadas_default = Pair(38.76f, -9.12f)
    var coordenadas = coordenadas_default


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {


        when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                if (day) {
                    println("Dia")

                    setTheme(R.style.Theme_Day)
                } else {
                    println("Noite")

                    setTheme(R.style.Theme_Night)
                }
            }

            Configuration.ORIENTATION_LANDSCAPE -> {
                if (day) {
                    setTheme(R.style.Theme_Day_Land)
                } else {
                    setTheme(R.style.Theme_Night_Land)
                }
            }
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Configurar os editTexts para caso o utilizador coloque fi
        config_editTexts();

        //Obter valores ao abrir o aplicativo-------------------------
        //Obter latitude e longitude
        coordenadas = obter_latitude_longitude()

        //Buscar weather e atualizar UI
        //Este if é por causa das datas
        fetchWeatherData(coordenadas.first, coordenadas.second).start()

        mudar_background()


        //Atribuir função ao botão
        val button = findViewById<Button>(R.id.update_button)

        button?.setOnClickListener()
        {

            //37.566
            //126.9784
            //Obter latitude e longitude
            coordenadas = obter_latitude_longitude()

            fetchWeatherData(coordenadas.first, coordenadas.second).start()


        }
        //-------------------------------------
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun weatherApiCall(lat: Float, long: Float): WeatherData {
        val reqString = buildString {
            append("https://api.open-meteo.com/v1/forecast?")
            append("latitude=${lat}&longitude=${long}&")
            append("current_weather=true&")
            append("hourly=temperature_2m,weathercode,pressure_msl,windspeed_10m")
        }

        val str = reqString.toString()
        val url = URL(reqString.toString())

        url.openStream().use {
            val request = Gson().fromJson(InputStreamReader(it, "UTF-8"), WeatherData::class.java)
            return request
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchWeatherData(lat: Float, long: Float): Thread {
        return Thread {
            val weather = weatherApiCall(lat, long)
            updateUI(weather)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateUI(request: WeatherData) {

        runOnUiThread {

            //Obter a imagem que dita o estado do dia
            val weatherImage: ImageView = findViewById(R.id.estadoDia_imageView)

            //Obter o textView (onde serão colocados os resultados)
            val pressure: TextView = findViewById(R.id.res_SeaLevelPress_textView)
            val windDirection: TextView = findViewById(R.id.res_windDirection_textView)
            val windSpeed: TextView = findViewById(R.id.res_windSpeed_textView)
            val temperature: TextView = findViewById(R.id.res_temperature_textView)
            val time: TextView = findViewById(R.id.res_time_textView)

            //Obter o tempo presente para poder atualizar a previsão
            val date = LocalDateTime.now() //obtém a data local
            val formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:00") //para buscar a hora neste tipo "2011-12-03T10:00"
            var text =
                date.format(formatter) //converte para string com o formato "2011-12-03T10:00"


            //Obter o indice da data atual nos dados (para poder buscar os restantes valores)
            val index_dados_atuais = request.hourly.time.indexOf(text)


            //Trocar o texto para o resultado do resquest feito
            pressure.text =
                request.hourly.pressure_msl.get(index_dados_atuais).toString() + " hPa"
            windDirection.text =
                request.current_weather.winddirection.toString()
            windSpeed.text =
                request.hourly.windspeed_10m.get(index_dados_atuais).toString() + " km/h"
            temperature.text =
                request.hourly.temperature_2m.get(index_dados_atuais).toString() + "ºC"
            time.text =
                request.hourly.time.get(index_dados_atuais)


            day = if (request.current_weather.is_day == 1) true else false
            mudar_background()


            //MUDA A IMAGEM DO ESTADO DO DIA DINAMICAMENTE -------------------------------------------

            //Obter o mapa que relaciona a imagem do tempo, como um código
            val mapt = getWeatherCodeMap()
            //código para a imagem do estado do tempo
            val wCode = mapt.get(request.current_weather.weathercode)

            //Buscar a imagem como o código dado
            val wImage = when (wCode) {
                WMO_WeatherCode.CLEAR_SKY,
                WMO_WeatherCode.MAINLY_CLEAR,
                WMO_WeatherCode.PARTLY_CLOUDY ->
                    if (day) wCode?.image + "day"
                    else wCode?.image + "night"

                else -> wCode?.image
            }

            //Desenhar imagem na condição do dia
            val res = resources //Obter recursos da app
            weatherImage.setImageResource(R.drawable.fog) //Imagem fixa do ImageView

            //Procura um recurso com o nome drawable com o nome obtido no map e retorna o id da imagem
            val resID = res.getIdentifier(
                wImage,
                "drawable",
                packageName
            )

            //Converte um id num objeto Drawable
            val drawable = this.getDrawable(resID)
            //Define a imagem no ImageView
            weatherImage.setImageDrawable(drawable)
        }
    }

    private fun obter_latitude_longitude(): Pair<Float, Float> {

        //Obter editTexts
        val latitude_editText: EditText = findViewById(R.id.latitude_input_editText)
        val longitude_editText: EditText = findViewById(R.id.longitude_input_editText)


        //Obter inputs
        var latitude: Float = latitude_editText.getText().toString().toFloat()
        var longitude: Float = longitude_editText.getText().toString().toFloat()

        //Formatar os inputs
        latitude = String.format("%.2f", latitude).toFloat()
        longitude = String.format("%.2f", longitude).toFloat()

        //Verificar se os resultados são válidos
        //Latitude [-90, +90] ,  longitude [-90, +90]
        if (latitude < -90.00 || latitude > 90.00) {
            latitude = coordenadas_default.first
            Toast.makeText(
                this,
                "Coordenadas mal introduzidas (latitude = [-90;+90])",
                Toast.LENGTH_LONG
            ).show()
        }
        if (longitude < -180.00 || longitude > 180.00) {
            longitude = coordenadas_default.second
            Toast.makeText(
                this,
                "Coordenadas mal introduzidas (longitude = [-180;+180])",
                Toast.LENGTH_LONG
            ).show()
        }


        //Alterar o que está escrito caso necessário
        latitude_editText.setText(latitude.toString())
        longitude_editText.setText(longitude.toString())

        return Pair(latitude, longitude)
    }


    private fun mudar_background() {

        val container: ConstraintLayout = findViewById(R.id.container)

        when(resources.configuration.screenLayout){
            Configuration.SCREENLAYOUT_SIZE_LARGE ->{

                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_PORTRAIT -> {
                        if (day) {
                            container.setBackgroundResource(R.drawable.fundo_portrait_tablet)
                        } else {
                            container.setBackgroundResource(R.drawable.fundo_night_portrait_tablet)
                        }
                    }

                    Configuration.ORIENTATION_LANDSCAPE -> {
                        if (day) {
                            container.setBackgroundResource(R.drawable.fundo_land_tablet)
                        } else {
                            container.setBackgroundResource(R.drawable.fundo_night_land_tablet)
                        }
                    }
                }
            }

            else ->{

                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_PORTRAIT -> {
                        if (day) {
                            container.setBackgroundResource(R.drawable.fundo_portrait)
                        } else {
                            container.setBackgroundResource(R.drawable.fundo_night_portrait)
                        }
                    }

                    Configuration.ORIENTATION_LANDSCAPE -> {
                        if (day) {
                            container.setBackgroundResource(R.drawable.fundo_land)
                        } else {
                            container.setBackgroundResource(R.drawable.fundo_night_land)
                        }
                    }
                }

            }
        }


    }

    private fun config_editTexts() {

        //Obter editTexts
        val latitude_editText: EditText = findViewById(R.id.latitude_input_editText)
        val longitude_editText: EditText = findViewById(R.id.longitude_input_editText)

        latitude_editText.addTextChangedListener {
            if (it?.contains(",") == true) {

                Toast.makeText(this, "Evite virgulas. Use pontos.", Toast.LENGTH_LONG).show()
                val novoTexto = it.toString().replace(",", ".")
                latitude_editText.setText(novoTexto)

            }

        }

        longitude_editText.addTextChangedListener {
            if (it?.contains(",") == true) {

                Toast.makeText(this, "Evite virgulas. Use pontos.", Toast.LENGTH_LONG).show()
                val novoTexto = it.toString().replace(",", ".")
                longitude_editText.setText(novoTexto)

            }
        }

    }
}