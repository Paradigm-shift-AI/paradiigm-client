package com.vedangj044.paradiigm_client

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vedangj044.paradiigm_client.models.BasicInfo
import com.vedangj044.paradiigm_client.models.Enroll
import com.vedangj044.paradiigm_client.models.QuestionTest
import com.vedangj044.paradiigm_client.models.TestReview
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
interface BasicInfoApiService {

    @Headers("Accept: application/json")
    @GET("basicinfo")
    suspend fun getBasicInfo(@Query("studntID") studentID: Int): Response<BasicInfo>

    @Headers("Accept: application/json")
    @GET("testreview")
    suspend fun getTestReview(@Query("stundentID") studentID: Int, @Query("classID") classID: Int): Response<TestReview>

    @Headers("Accept: application/json")
    @GET("getquestion")
    suspend fun getLastQuestion(@Query("classID") classID: Int, @Query("studentID") studentID: Int): Response<QuestionTest>

    @Headers("Accept: application/json")
    @GET("enrollclass")
    suspend fun getEnrollment(@Query("studntID") studentID: Int, @Query("classID") classID: Int): Response<Enroll>

    @Headers("Accept: application/json")
    @GET("submitresponse")
    suspend fun submitResponse(@Query("studentID") studentID: Int, @Query("questionID") questionID: Int, @Query("valid") valid: Boolean): Response<Enroll>

    companion object{

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fun getApiService() = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }


}