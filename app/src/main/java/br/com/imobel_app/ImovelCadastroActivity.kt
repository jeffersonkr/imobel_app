package br.com.imobel_app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.cadastro_imovel.*

class ImovelCadastroActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cadastro_imovel)
        setTitle("Novo Imovel")
        criarImovel.setOnClickListener {
            val imovel = Imovel()
            imovel.descricao = descricaoImovel.text.toString()
            imovel.endereco = enderecoImovel.text.toString()
            imovel.bairro = bairroImovel.text.toString()
            imovel.cep = cepImovel.text.toString()
            taskCriar(imovel)
        }
    }
    private fun taskCriar(imovel: Imovel) {
        Thread {
            ImovelServices.saveImovel(imovel)
            runOnUiThread {
                finish()
            }
        }.start()
    }
}