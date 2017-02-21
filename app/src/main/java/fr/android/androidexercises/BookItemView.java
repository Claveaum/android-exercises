package fr.android.androidexercises;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookItemView extends LinearLayout {

    private TextView name, price;

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
        // TODO findViewById()
        name = (TextView) findViewById(R.id.nameTextView);
        price = (TextView) findViewById(R.id.priceTextView);
    }

    public void bindView(Book book) {
        // TODO setText()
        name.setText(book.name);
        price.setText(String.valueOf(book.price));
    }
}
