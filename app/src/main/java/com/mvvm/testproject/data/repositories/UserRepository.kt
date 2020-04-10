package com.mvvm.testproject.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.testproject.data.db.AppDatabase
import com.mvvm.testproject.data.db.entities.User
import com.mvvm.testproject.data.network.MyAPI
import com.mvvm.testproject.data.network.SafeApiRequest
import com.mvvm.testproject.data.network.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

 class UserRepository (
    private val api: MyAPI,
    private val db : AppDatabase
)
    : SafeApiRequest() {

    suspend fun userLogin(email : String, password : String) : AuthResponse { //LiveData<String> //Response<AuthResponse>
        return apiRequest {
            api.userLogin(email, password)
        }
    }

     suspend fun userSignup(name: String,email: String,password: String) : AuthResponse {
         return apiRequest{
             api.userSignup(name, email, password)
         }
     }
     suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

     fun getUser() = db.getUserDao().getUser()
        //return MyAPI().userLogin(email,password)
        /*val loginResponse = MutableLiveData<String>()


        MyAPI.userLogin(email, password)
            (object: retrofit2.Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>) {
                    if(response.isSuccessful){
                        loginResponse.value = response.body()?.string()
                    }else
                        loginResponse.value = response.errorBody()?.string()


                }

            })

        return loginResponse*/


}