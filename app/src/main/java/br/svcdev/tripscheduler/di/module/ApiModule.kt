package br.svcdev.tripscheduler.di.module

import br.svcdev.tripscheduler.model.api.IAutoCompleteSource
import br.svcdev.tripscheduler.model.api.IDataSource
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("autocompleteUrl")
    @Provides
    fun autocompleteUrl(): String = "http://autocomplete.travelpayouts.com"

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "http://api.travelpayouts.com"

    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IDataSource = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(IDataSource::class.java)

    @Provides
    fun autocompleteApi(@Named("autocompleteUrl") autocompleteUrl: String, gson: Gson):
            IAutoCompleteSource = Retrofit.Builder()
        .baseUrl(autocompleteUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(IAutoCompleteSource::class.java)

    @Singleton
    @Provides
    fun gson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

}