package br.com.imobel_app

import android.content.Context

object ImovelServices {
    fun getImoveis(context: Context): List<Imovel> {
        val imoveis = mutableListOf<Imovel>()
        for ( i in 1..10) {
            val imovel = Imovel()
            imovel.bairro = "bairro chik"
            imovel.cep = "01122-001"
            imovel.cidade = "Sao Paulo"
            imovel.endereco = "Rua impacta, 44"
            imovel.foto = "url de foto"
            imoveis.add(imovel)
        }
        return imoveis
    }
}