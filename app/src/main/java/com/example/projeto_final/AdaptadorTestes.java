package com.example.projeto_final;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorTestes extends RecyclerView.Adapter<AdaptadorTestes.ViewHolderTestes>  {
    private final Context context ;
    private Cursor cursor = null;

    public void setCursor(Cursor cursor) {
        if (cursor != this.cursor) {
            this.cursor = cursor;
            notifyDataSetChanged();
        }
    }
    public AdaptadorTestes(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolderTestes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemTestes = LayoutInflater.from(context).inflate(R.layout.item_testes, parent, false);
        return new ViewHolderTestes(itemTestes);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderTestes holder, int position) {
        cursor.moveToPosition(position);
        Testes testes = Converte.cursorToTestes(cursor);
        holder.setTestes(testes);
    }
    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        }
        return cursor.getCount();
    }
    private ViewHolderTestes viewHolderTestesSelecionado = null;

    public Testes getTesteSelecionado() {
        if (viewHolderTestesSelecionado == null) return null;
        return viewHolderTestesSelecionado.testes;
    }
    public class ViewHolderTestes extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Testes  testes = null;

        private final TextView textViewNomeDoTeste;
        private final TextView textViewResultadoDoTeste;
        private final TextView textViewDataDoTeste;

        public ViewHolderTestes(@NonNull View itemView) {
            super(itemView);

            textViewNomeDoTeste = (TextView) itemView.findViewById(R.id.textViewNomeDoTeste);
            textViewResultadoDoTeste =(TextView) itemView.findViewById(R.id.textViewResultadoDoTeste);
            textViewDataDoTeste = (TextView) itemView.findViewById(R.id.textViewDataDoTeste);

             itemView.setOnClickListener(this);
        }

        public void setTestes(Testes testes) {
            this.testes = testes;
            textViewNomeDoTeste.setText(String.valueOf(testes.getIdDoente()));
            textViewResultadoDoTeste.setText(String.valueOf(testes.getResultado_testes()));
            textViewDataDoTeste.setText(String.valueOf(testes.getData_testes()));

        }

        @Override
        public void onClick(View v) {
            if (viewHolderTestesSelecionado == this) {
                return;
            }
            if (viewHolderTestesSelecionado != null) {
                viewHolderTestesSelecionado.desSeleciona();
            }
            viewHolderTestesSelecionado = this;
            seleciona();
            DisplayMostrarTestes displayMostrarTestes = (DisplayMostrarTestes) AdaptadorTestes.this.context;

        }
        private void desSeleciona() {
            itemView.setBackgroundResource(android.R.color.white);
        }
        private void seleciona(){
            itemView.setBackgroundResource(R.color.colorAccent);
        }
    }
}