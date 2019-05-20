package br.com.imobel_app

import android.content.Context
import android.util.Log
import br.com.fernandosousa.lmsapp.HttpHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

object ImovelServices {

    val host = "http://lucascanhaleite.pythonanywhere.com/api"
    val TAG = "WS_AppImobel"

    fun getImoveis(context: Context): List<Imovel> {
        val url = "$host/imovel"
        val json = URL(url).readText()
        Log.d(TAG, json)

        return parseJson<List<Imovel>>(json)
    }

    fun saveImovel(imovel: Imovel): Response {
        var json = HttpHelper.post("$host/imovel", imovel.toJson())
    }

    inline fun <reified T> parseJson(json: String) : T {
        val type = object: TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}