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
            imovel.foto = "https://images.imoveis-sc.com.br/media/thumb-350-250/blumenau-casa-velha-606867-0-imagem_imovel_5c598f278237d1072736948000.jpeg"
            imoveis.add(imovel)
        }
        return imoveis
    }
}