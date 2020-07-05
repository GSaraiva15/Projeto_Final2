package com.example.projeto_final;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorDoentes extends RecyclerView.Adapter<AdaptadorDoentes.ViewHolderDoentes> {
    private final Context context;
    private Cursor cursor = null;

    public void setCursor(Cursor cursor) {
        if (cursor != this.cursor) {
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }
    public AdaptadorDoentes(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDoentes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemDoente = LayoutInflater.from(context).inflate(R.layout.item_doente, parent, false);
        return new ViewHolderDoentes(itemDoente);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolderDoentes holder, int position) {
        cursor.moveToPosition(position);
        Doentes doentes = Converte.cursorToDoente(cursor);
        holder.setDoentes(doentes);

    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }


    private ViewHolderDoentes viewHolderDoenteSelecionado = null;

    public Doentes getDoenteSelecionado() {
        if (viewHolderDoenteSelecionado == null) return null;
        return viewHolderDoenteSelecionado.doentes;
    }



    public class ViewHolderDoentes extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Doentes doentes = null;

        private final TextView textViewNomeDoDoente;
        private final TextView textViewConcelhoDoDoente;
        private final TextView textViewEstadoDoDoente;
        private final TextView textViewDataDeNascimentoDoDoente;
        private final TextView textViewSexoDoDoente;
        private final TextView textViewDataEstadoAtualDoDoente;
        private final TextView textViewCronicoDoDoente;
        private final TextView textViewTelefoneDoDoente;


        public ViewHolderDoentes(@NonNull View itemView) {
            super(itemView);

            textViewNomeDoDoente = (TextView) itemView.findViewById(R.id.textViewNomeDoDoente);
            textViewConcelhoDoDoente = (TextView) itemView.findViewById(R.id.textViewConcelhoDoDoente);
            textViewEstadoDoDoente = (TextView) itemView.findViewById(R.id.textViewEstadoDoDoente);
            textViewDataDeNascimentoDoDoente = (TextView) itemView.findViewById(R.id.textViewDataDeNascimentoDoDoente);
            textViewSexoDoDoente = (TextView) itemView.findViewById(R.id.textViewSexoDoDoente);
            textViewDataEstadoAtualDoDoente = (TextView) itemView.findViewById(R.id.textViewDataEstadoAtualDoDoente);
            textViewCronicoDoDoente = (TextView) itemView.findViewById(R.id.textViewCronicoDoDoente);
            textViewTelefoneDoDoente = (TextView) itemView.findViewById(R.id.textViewTelefoneDoDoente);
            itemView.setOnClickListener(this);
        }

        public void setDoentes(Doentes doentes) {
            this.doentes = doentes;
            textViewNomeDoDoente.setText(String.valueOf(doentes.getNome_doente()));
            textViewConcelhoDoDoente.setText(String.valueOf(doentes.getId_concelho()));
            textViewEstadoDoDoente.setText(String.valueOf(doentes.getEstado_doente()));
            textViewDataDeNascimentoDoDoente.setText(String.valueOf(doentes.getNascimento_doente()));
            textViewSexoDoDoente.setText(String.valueOf(doentes.getSexo_doente()));
            textViewDataEstadoAtualDoDoente.setText(String.valueOf(doentes.getData_estado()));
            textViewCronicoDoDoente.setText(String.valueOf(doentes.getCronico_doente()));
            textViewTelefoneDoDoente.setText(String.valueOf(doentes.getTelemovel_doente()));
        }

        @Override
        public void onClick(View v) {
            if (viewHolderDoenteSelecionado == this) {
                return;
            }
            if (viewHolderDoenteSelecionado != null) {
                viewHolderDoenteSelecionado.desSeleciona();
            }
            viewHolderDoenteSelecionado = this;
            seleciona();
            DisplayMostrar displayMostrar = (DisplayMostrar) AdaptadorDoentes.this.context;

        }
        private void desSeleciona() {
            itemView.setBackgroundResource(android.R.color.white);
        }
        private void seleciona(){
            itemView.setBackgroundResource(R.color.colorAccent);
        }
    }
}