package fr.android.mclaveau.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.android.mclaveau.R;
import fr.android.mclaveau.activity.LibraryActivity;
import fr.android.mclaveau.fragment.DetailViewFragment;
import fr.android.mclaveau.model.Book;
import fr.android.mclaveau.view.BookItemView;

/**
 * Created by mclaveau on 22/02/2017.
 */

public class BookRecyclerAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private final List<Book> books;
    public DetailViewFragment detailViewFragment;

    public BookRecyclerAdapter(LayoutInflater inflater, List<Book> books){
        this.inflater = inflater;
        this.books = books;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.custom_view_item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final BookItemView bookItemView = (BookItemView) holder.itemView;
        bookItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Bundle arguments = new Bundle();
                    arguments.putParcelable(DetailViewFragment.BOOK_PARCELABLE_ID, books.get(position));
                    detailViewFragment = new DetailViewFragment();
                    detailViewFragment.setArguments(arguments);
                    ((LibraryActivity)(v.getContext())).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.detail_frame, detailViewFragment)
                            .commit();
            }
        });
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