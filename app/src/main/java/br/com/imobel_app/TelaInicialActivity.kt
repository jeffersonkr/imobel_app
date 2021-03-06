package br.com.imobel_app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.pagina_main.*
import kotlinx.android.synthetic.main.toolbar.*


class TelaInicialActivity : DebugActivity(),
    NavigationView.OnNavigationItemSelectedListener{

    private val context: Context get() = this
    private var imoveis = listOf<Imovel>()
    var recycler: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_main)

        // acessar parametros da intnet
        // intent é um atributo herdado de Activity
        val args = intent.extras
        // recuperar o parâmetro do tipo String
        val nome = args.getString("nome")

        // recuperar parâmetro simplificado
        val numero = intent.getIntExtra("nome",0)

        Toast.makeText(context, "Parâmetro: $nome", Toast.LENGTH_LONG).show()
        Toast.makeText(context, "Numero: $numero", Toast.LENGTH_LONG).show()

        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        configuraMenuLateral()

        recycler = recyclerImoveis
        recycler?.layoutManager = LinearLayoutManager(context)
        recycler?.itemAnimator = DefaultItemAnimator()
        recycler?.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
        taskImoveis()
    }

    fun taskImoveis(){
        Thread {
            this.imoveis = ImovelServices.getImoveis(context)
            runOnUiThread{
                recycler?.adapter = ImovelAdapter(imoveis){onClickImoveis(it)}
            }
        }.start()
    }

    fun onClickImoveis(imovel: Imovel){
        Toast.makeText(context, "${imovel.endereco}", Toast.LENGTH_SHORT).show()

    }

    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do BrewerApp");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }


    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE ) {
            // atualizar lista de disciplinas
            taskImoveis()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if  (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_cadastrar_imovel) {
            val intent = Intent(context, ImovelCadastroActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun configuraMenuLateral(){
        var toggle = ActionBarDrawerToggle(
            this,
            layoutMenuLateral,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        layoutMenuLateral.addDrawerListener(toggle)
        toggle.syncState()
        menu_lateral.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_cliente -> {
                Toast.makeText(this, "Clicou cliente", Toast.LENGTH_LONG).show()
            }

            R.id.nav_proprietario -> {
                Toast.makeText(this, "Clicou proprietario", Toast.LENGTH_LONG).show()
            }

            R.id.nav_imoveis -> {
                Toast.makeText(this, "Clicou imoveis", Toast.LENGTH_LONG).show()
            }

            R.id.nav_corretor -> {
                Toast.makeText(this, "Clicou corretor", Toast.LENGTH_LONG).show()
            }

            R.id.nav_sair -> {
                cliqueSair()
            }
        }
        layoutMenuLateral.closeDrawer(GravityCompat.START)
        return true
    }
}