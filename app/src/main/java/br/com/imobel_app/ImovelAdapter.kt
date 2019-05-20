package br.com.imobel_app

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_imovel.view.*

class ImovelAdapter (val imoveis: List<Imovel>, val onClick: (Imovel) -> Unit): RecyclerView.Adapter<ImovelAdapter.ImovelViewHolder>() {

    class ImovelViewHolder(view: View): RecyclerView.ViewHolder(view){
        val cardNome: TextView
        val cardImg: ImageView
        var cardView: CardView

        init {
            cardNome = view.cardNome
            cardImg = view.cardImg
            cardView = view.card_imoveis
        }
    }

    override fun getItemCount(): Int {
        return this.imoveis.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImovelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_imovel, parent, false)
        val holder = ImovelViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: ImovelViewHolder, position: Int) {
        val context = holder.itemView.context
        val imovel = this.imoveis[position]

        holder.cardNome.text = imovel.endereco
        Picasso.with(context).load(imovel.foto).fit().into(holder.cardImg, object: com.squareup.picasso.Callback{
                override fun onSuccess() {

                }

            override fun onError() {

            }
            })
        holder.itemView.setOnClickListener{onClick(imovel)}
    }

}