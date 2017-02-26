package fr.android.mclaveau.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import fr.android.mclaveau.R;
import fr.android.mclaveau.adapter.BookRecyclerAdapter;
import fr.android.mclaveau.model.Book;
import fr.android.mclaveau.service.HenriPotierService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class LibraryActivity extends AppCompatActivity   {

    private ArrayList<Book> books;
    private RecyclerView recyclerView;
    private BookRecyclerAdapter bookRecyclerAdapter;
    final String parcelable_book_id = "books_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        books = new ArrayList<>();
        Timber.plant(new Timber.DebugTree());

        recyclerView = (RecyclerView) findViewById(R.id.bookRecycler);
        bookRecyclerAdapter = new BookRecyclerAdapter(LayoutInflater.from(this), books);
        recyclerView.setAdapter(bookRecyclerAdapter);

        if (savedInstanceState != null) {
            books.clear();
            books.addAll(savedInstanceState.<Book>getParcelableArrayList(parcelable_book_id));
            bookRecyclerAdapter.notifyDataSetChanged();
        }else{
            getBooksAsync();
        }

    }

    /**
     * call to get the books
     */
    private void getBooksAsync() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HenriPotierService service = retrofit.create(HenriPotierService.class);

        service.books().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                    books.addAll(response.body());
                    bookRecyclerAdapter.notifyDataSetChanged();
                Timber.i("%s",books.toString());
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Timber.i("Error %s",t.getMessage());
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                this.getSupportFragmentManager().beginTransaction().remove(this.getSupportFragmentManager().findFragmentById(R.id.detail_frame)).commit();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(parcelable_book_id, books);
    }
}
