package com.example.meethere.retrofit

import android.util.Log
import com.example.meethere.retrofit.request.*
import com.example.meethere.utils.API
import com.example.meethere.utils.Constants.TAG
import com.example.meethere.utils.RESPONSE_STATE
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    //싱글톤 유지되도록, retrofit 가져올 때마다 해당 인스턴스
    companion object {
        val instance = RetrofitManager()
    }

    //http 콜 만들기
    //retrofit interface 가져오기
    private val iRetrofit: IRetrofit? =
        RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //즐겨찾기 API 호출
    fun sabeBookmark(bookmark: Bookmark, completion: (RESPONSE_STATE, String) -> Unit){
        val term = bookmark?:""
        var call = iRetrofit?.saveBookmark(bookmark = term as Bookmark)?:return

        call.enqueue(object :retrofit2.Callback<JsonElement>{
            //응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called /t:${response.raw()}")
                completion(RESPONSE_STATE.OKAY, response.body().toString())
            }
            //응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL, t.toString())
            }
        })
    }

    //회원 비밀번호 찾기 API 호출
    fun findPwService(email: String, name: String, phone: String, completion: (RESPONSE_STATE, String) -> Unit) {

        val term1 = email ?: ""
        val term2 = name ?: ""
        val term3 = phone ?: ""

        val call = iRetrofit?.findPwService(
            email = term1 as String,
            name = term2 as String,
            phone = term3 as String
        ) ?: return

        call.enqueue(object :retrofit2.Callback<JsonElement>{
            //응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "Retrofitmanager - onResponse() called /t:${response.raw()}")
                completion(RESPONSE_STATE.OKAY, response.body().toString())
            }

            //응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL, t.toString())
            }
        })
    }

    //회원 아이디 찾기 API 호출
    fun findIdService(name: String, phone: String, completion: (RESPONSE_STATE, String) -> Unit) {

        val term1 = name ?: ""
        val term2 = phone ?: ""
        val call =
            iRetrofit?.findIdService(name = term1 as String, phone = term2 as String) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {

            //응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "Retrofitmanager - onResponse() called /t:${response.raw()}")
                completion(RESPONSE_STATE.OKAY, response.body().toString())
            }

            //응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL, t.toString())
            }
        })
    }

    //회원정보 수정 API 호출
    fun updateService(update: Update, completion: (RESPONSE_STATE, String) -> Unit) {

        val term = update ?: ""
        var call = iRetrofit?.updateService(update = term as Update) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {

            //응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "Retrofitmanager - onResponse() called /t:${response.raw()}")
                completion(RESPONSE_STATE.OKAY, response.body().toString())
            }

            //응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL, t.toString())
            }
        })
    }


    //회원가입 인증코드 입력 API 호출
    fun verifyService(verify: Verify, completion: (RESPONSE_STATE, String) -> Unit) {

        val term = verify ?: ""
        var call = iRetrofit?.verifyService(verify = term as Verify) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {

            //응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "Retrofitmanager - onResponse() called /t:${response.raw()}")
                completion(RESPONSE_STATE.OKAY, response.body().toString())
            }

            //응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL, t.toString())
            }
        })
    }

    //회원가입 API 호출
    fun registerService(register: Register, completion: (RESPONSE_STATE, String) -> Unit) {

        val term = register ?: ""
        var call = iRetrofit?.registerService(register = term as Register) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {

            //응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "Retrofitmanager - onResponse() called /t:${response.raw()}")
                completion(RESPONSE_STATE.OKAY, response.body().toString())
            }

            //응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL, t.toString())
            }

        })
    }


    //즐겨찾기 리스트 API 호출
    fun bookmarkListService(memberId: Long?, completion: (RESPONSE_STATE, String) -> Unit) {

        //null이면 "", 아니면 해당 값 설정
        val term = memberId ?: ""
        var call = iRetrofit?.bookmarkListService(memberId = term as Long) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {

            //응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "Retrofitmanager - onResponse() called /t:${response.raw()}")
                completion(RESPONSE_STATE.OKAY, response.body().toString())
            }

            //응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONSE_STATE.FAIL, t.toString())
            }
        })

    }

    //로그인 API 호출
    fun loginService(login: Login?, completion: (RESPONSE_STATE, String) -> Unit) {

        //null이면 "", 아니면 해당 값 설정
        val term = login ?: ""
        val call = iRetrofit?.loginService(login = term as Login) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {

            //응답 성공
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called / t: ${response.raw()}")
                completion(RESPONSE_STATE.OKAY, response.body().toString())
            }

            //응답 실패
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL, t.toString())
            }
        })
    }
}