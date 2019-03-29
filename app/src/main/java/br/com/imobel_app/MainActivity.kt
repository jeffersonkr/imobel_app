package br.com.imobel_app


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.widget.*


class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // encontra objeto pelo id
        val imagem = findViewById<ImageView>(R.id.imageView)
        imagem.setImageResource(R.drawable.sk_image)

        val botaoLogin = findViewById<Button>(R.id.botao_login)
        botaoLogin.setOnClickListener {onClickLogin() }

    }

    fun onClickLogin(){
        val campoUsuario = findViewById<EditText>(R.id.campo_usuario)
        val campoSenha = findViewById<EditText>(R.id.campo_senha)
        val valorUsuario = campoUsuario.text.toString()
        val valorSenha = campoSenha.text.toString()
        if(valorUsuario == "aluno" && valorSenha == "impacta") {
            Toast.makeText(context, "Usuario e senha correta", Toast.LENGTH_LONG).show()
            val intent = Intent(this, TelaInicialActivity::class.java)
            val params = Bundle()
            params.putString("nome", "$valorUsuario")
            intent.putExtras(params)
            intent.putExtra("numero", 10)
            startActivity(intent)
        } else {
            Toast.makeText(context, "Usuario e senha incorreta", Toast.LENGTH_LONG).show()
        }

    }
}