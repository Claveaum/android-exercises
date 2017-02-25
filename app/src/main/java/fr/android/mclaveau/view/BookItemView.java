package fr.android.mclaveau.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fr.android.mclaveau.model.Book;
import fr.android.mclaveau.R;

public class BookItemView extends LinearLayout {

    private TextView title, price;

    private ImageView cover;

    public BookItemView(Context context) {
        this(context, null);
    }

    public BookItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BookItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        title = (TextView) findViewById(R.id.titleTextView);
        price = (TextView) findViewById(R.id.priceTextView);
        cover = (ImageView) findViewById(R.id.coverImageView);
    }

    public void bindView(Book book) {
        title.setText(book.getTitle());
        price.setText(String.valueOf(book.getPrice()));
        Glide.with(getContext()).load(book.getCover()).fitCenter().crossFade().into(cover);
    }
}