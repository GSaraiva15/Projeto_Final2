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

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public ViewHolderTestes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemTestes = LayoutInflater.from(context).inflate(R.layout.item_testes, parent, false);
        return new ViewHolderTestes(itemTestes);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
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

            textViewNomeDoTeste = (TextView) itemView.findViewById(R.id.textViewNomeDoDoente);
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
            DisplayMostrar displayMostrar = (DisplayMostrar) AdaptadorTestes.this.context;

        }
        private void desSeleciona() {
            itemView.setBackgroundResource(android.R.color.white);
        }
        private void seleciona(){
            itemView.setBackgroundResource(R.color.colorAccent);
        }
    }
}