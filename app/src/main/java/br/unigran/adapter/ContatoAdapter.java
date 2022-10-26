package br.unigran.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.unigran.agenda.Contato;
import br.unigran.agenda.R;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoHolder>{
    List<Contato> dados;

    public ContatoAdapter(List<Contato> dados) {
        this.dados = dados;
    }
    @NonNull
    @Override
    public ContatoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha,parent,false);
        return new ContatoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContatoHolder holder, int position) {
        holder.nome.setText(dados.get(position).getNome());
        holder.numero.setText(dados.get(position).getTelefone());
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ContatoHolder extends RecyclerView.ViewHolder {
        public TextView nome;
        public TextView numero;
        public ContatoHolder(@NonNull View itemView) {
            super(itemView);
            nome =itemView.findViewById(R.id.nomeid);
            numero =itemView.findViewById(R.id.numeroid);
        }
    }
}
