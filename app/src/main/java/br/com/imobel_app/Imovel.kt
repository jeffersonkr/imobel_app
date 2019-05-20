package br.com.imobel_app

import com.google.gson.GsonBuilder

class Imovel {
    var id:Long = 0
    var foto:String? = ""
    var endereco:String? = ""
    var cidade:String? = ""
    var bairro:String? = ""
    var cep:String?= ""
    var metro_quadrado:String?= ""
    var iptu:String? = ""
    var descricao:String? = ""
    var matricula:String? = ""

    override fun toString(): String {
        return "Imovel: $id"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}