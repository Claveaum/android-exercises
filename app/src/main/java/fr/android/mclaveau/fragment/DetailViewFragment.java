package fr.android.mclaveau.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import fr.android.mclaveau.R;
import fr.android.mclaveau.model.Book;


public class DetailViewFragment extends Fragment {
    public static final String BOOK_PARCELABLE_ID = "book_parcelable_id";

    private Book book;

    public DetailViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(BOOK_PARCELABLE_ID)) {
            book = (Book) getArguments().getParcelable(BOOK_PARCELABLE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detail_view, container, false);

        if (book != null) {
            TextView titleView = (TextView) rootView.findViewById(R.id.title);
            titleView.setText(book.getTitle());
            titleView.setTextSize(35);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.image);
            Glide.with(this)
                    .load(book.getCover())
                    .into(imageView);
            TextView priceView = (TextView) rootView.findViewById(R.id.price);
            priceView.setText(book.getPrice()+"€");
            TextView synopsisView = (TextView) rootView.findViewById(R.id.synopsis);
            synopsisView.setText(book.getSynopsisAgregate());
        }

        return rootView;
    }
}
