package fr.android.mclaveau.service;

import java.util.List;

import fr.android.mclaveau.model.Book;
import retrofit2.Call;
import retrofit2.http.GET;

public interface HenriPotierService {

    // TODO Method GET books which return a List<Book>
    @GET("books")
    Call<List<Book>> books();

}
