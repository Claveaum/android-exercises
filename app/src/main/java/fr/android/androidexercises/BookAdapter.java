package fr.android.androidexercises;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class BookAdapter extends BaseAdapter {

    private final List<Book> books;
    private LayoutInflater inflater;

    public BookAdapter(Context context, List<Book> books) {
        this.books = books;
        // TODO LayoutInflater.from()
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO
        return books.size();
    }

    @Override
    public Book getItem(int position) {
        // TODO
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO
        return books.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO
        BookItemView bookItemView;
        if(convertView == null) {
            bookItemView = (BookItemView) this.inflater.inflate(R.layout.custom_view_item_book, parent, false);
        } else {
            bookItemView = (BookItemView) convertView;
        }

        bookItemView.bindView(this.getItem(position));
        return bookItemView;
    }

}
