package fr.android.androidexercises;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by mclaveau on 21/02/2017.
 */

public class BookRecyclerAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private final List<Book> books;

    public BookRecyclerAdapter(LayoutInflater inflater, List<Book> books){
        this.inflater = inflater;
        this.books = books;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.custom_view_item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BookItemView bookItemView = (BookItemView) holder.itemView;
        bookItemView.bindView(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView){
            super(itemView);
        }
    }
}
