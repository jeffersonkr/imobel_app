package br.com.imobel_app

class Imovel {
    var id:Long = 0
    var foto = ""
    var endereco = ""
    var cidade = ""
    var bairro = ""
    var cep = ""

    override fun toString(): String {
        return "Imovel: $id"
    }
}