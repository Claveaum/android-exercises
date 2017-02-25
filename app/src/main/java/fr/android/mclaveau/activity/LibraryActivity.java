package fr.android.mclaveau.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class LibraryActivity extends AppCompatActivity {

    private List<Book> books;
    private RecyclerView recyclerView;
    private BookRecyclerAdapter bookRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        books = new ArrayList<>();

        // Plant logger cf. Android Timber
        Timber.plant(new Timber.DebugTree());

        getBooksAsync();

        // Create RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.bookRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.nb_columns)));
        bookRecyclerAdapter = new BookRecyclerAdapter(LayoutInflater.from(this), books);
        recyclerView.setAdapter(bookRecyclerAdapter);
    }

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
                Timber.i("Data: %s",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Timber.i("Error %s",t.getMessage());
            }
        });
    }

}
