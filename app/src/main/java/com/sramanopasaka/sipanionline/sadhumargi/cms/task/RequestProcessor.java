package com.sramanopasaka.sipanionline.sadhumargi.cms.task;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonObject;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddScocialRoleResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteSocialRoleResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.PasswordChangeResponse;
import com.sramanopasaka.sipanionline.sadhumargi.PasswordRecoverResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AchievementListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddAchievementResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddAddressResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddBusinessResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddEducationResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddExamResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.AddressListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.BasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.BusinessListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.CityListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.StateListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteAchievementResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteAddressResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteBusinessResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteEducationResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DeleteExamResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.DharmikDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.EducationListResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.LoginResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.RegisterResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.SanghDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateBasicDetailsResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateHobbiesResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateKnowledgeResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdatePromiseResponse;
import com.sramanopasaka.sipanionline.sadhumargi.cms.response.UpdateServiceResponse;
import com.sramanopasaka.sipanionline.sadhumargi.listener.EndPointApi;
import com.sramanopasaka.sipanionline.sadhumargi.listener.GUICallback;
import com.sramanopasaka.sipanionline.sadhumargi.model.Achievements;
import com.sramanopasaka.sipanionline.sadhumargi.model.Address;
import com.sramanopasaka.sipanionline.sadhumargi.model.BasicDetailsData;
import com.sramanopasaka.sipanionline.sadhumargi.model.Business;
import com.sramanopasaka.sipanionline.sadhumargi.model.City;
import com.sramanopasaka.sipanionline.sadhumargi.model.Education;
import com.sramanopasaka.sipanionline.sadhumargi.model.Exams;
import com.sramanopasaka.sipanionline.sadhumargi.model.LoginModel;
import com.sramanopasaka.sipanionline.sadhumargi.model.SocialRole;
import com.sramanopasaka.sipanionline.sadhumargi.model.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Name    :   pranavjdev
 * Date   : 8/9/17
 * Email : pranavjaydev@gmail.com
 */

public class RequestProcessor {
    private GUICallback guiCallback = null;

    public RequestProcessor(GUICallback guiCallback) {
        this.guiCallback = guiCallback;

    }

    public void doLogin(JsonObject jsonObject) {



        EndPointApi endPointApi = RetrofitClient.getAuthClient().create(EndPointApi.class);

        endPointApi.doLogin(jsonObject).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                Log.e("Response message=", "" + response.message());

               /* LoginResponse response1 = new LoginResponse();
                if (response.body() != null) {
                    response1.setLoginModel(response.body());

                    response1.setStatus(true);

                } else {
                    response1.setStatus(false);
                    if (!TextUtils.isEmpty(response.errorBody().toString())) {
                        try {
                            JSONObject jObjError = new JSONObject(convertStreamToString(response.errorBody().byteStream()));
                            response1.setMessage(jObjError.getString("message"));
                        } catch (Exception e) {
                            response1.setMessage("Invalid Username or Password");
                        }
                    } else {
                        response1.setMessage("Invalid Username or Password");

                    }


                }*/

                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void doRegister(JsonObject jsonObject) {



        EndPointApi endPointApi = RetrofitClient.getAuthClient().create(EndPointApi.class);

        endPointApi.doRegister(jsonObject).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {


                Log.e("Response message=", "" + response.message());

                LoginModel response1 = response.body();
                RegisterResponse registerResponse = new RegisterResponse();
                registerResponse.setData(response1);
                if(response1!=null){
                    if(!TextUtils.isEmpty(response1.getStatus()))
                        registerResponse.setStatus(response1.getStatus());
                    if(!TextUtils.isEmpty(response1.getMessage()))
                        registerResponse.setMessage(response1.getMessage());
                }

                if (response.body() != null)
                    guiCallback.onRequestProcessed(registerResponse, GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void getBasicDetails(String memberId,String token) {



        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);

        endPointApi.getBasicDetails(memberId,token).enqueue(new Callback<BasicDetailsResponse>() {
            @Override
            public void onResponse(Call<BasicDetailsResponse> call, Response<BasicDetailsResponse> response) {


                Log.e("Response message=", "" + response.message());

               /* LoginResponse response1 = new LoginResponse();
                if (response.body() != null) {
                    response1.setLoginModel(response.body());

                    response1.setStatus(true);

                } else {
                    response1.setStatus(false);
                    if (!TextUtils.isEmpty(response.errorBody().toString())) {
                        try {
                            JSONObject jObjError = new JSONObject(convertStreamToString(response.errorBody().byteStream()));
                            response1.setMessage(jObjError.getString("message"));
                        } catch (Exception e) {
                            response1.setMessage("Invalid Username or Password");
                        }
                    } else {
                        response1.setMessage("Invalid Username or Password");

                    }


                }*/

                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<BasicDetailsResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void getAddressList(String memberId,String token) {



        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);

        endPointApi.getAddressList(memberId,token).enqueue(new Callback<AddressListResponse>() {
            @Override
            public void onResponse(Call<AddressListResponse> call, Response<AddressListResponse> response) {


                Log.e("Response message=", "" + response.message());
                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<AddressListResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void getAchievementList(String memberId,String token) {



        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);

        endPointApi.getAchievementList(memberId,token).enqueue(new Callback<AchievementListResponse>() {
            @Override
            public void onResponse(Call<AchievementListResponse> call, Response<AchievementListResponse> response) {


                Log.e("Response message=", "" + response.message());
                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<AchievementListResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void getBusnessList(String memberId,String token) {



        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);

        endPointApi.getBusiness(memberId,token).enqueue(new Callback<BusinessListResponse>() {
            @Override
            public void onResponse(Call<BusinessListResponse> call, Response<BusinessListResponse> response) {


                Log.e("Response message=", "" + response.message());
                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<BusinessListResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void getEducationList(String memberId, String token) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);

        endPointApi.getEducation(memberId, token).enqueue(new Callback<EducationListResponse>() {
            @Override
            public void onResponse(Call<EducationListResponse> call, Response<EducationListResponse> response) {


                Log.e("Response message=", "" + response.message());
                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<EducationListResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void getDharmikDetails(String memberId, String token) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);

        endPointApi.getDharmikDetails(memberId, token).enqueue(new Callback<DharmikDetailsResponse>() {
            @Override
            public void onResponse(Call<DharmikDetailsResponse> call, Response<DharmikDetailsResponse> response) {


                Log.e("Response message=", "" + response.message());
                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<DharmikDetailsResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void getSanghDetails(String memberId, String token) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);

        endPointApi.getSanghDetails(memberId, token).enqueue(new Callback<SanghDetailsResponse>() {
            @Override
            public void onResponse(Call<SanghDetailsResponse> call, Response<SanghDetailsResponse> response) {


                Log.e("Response message=", "" + response.message());
                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<SanghDetailsResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void updateBasicDetails(String memberId, String token, BasicDetailsData data) {



        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);



        endPointApi.updateBasicDetails(memberId,token,"update",data.getSalution(),data.getFirstName(),data.getMiddleName(),data.getLastName(),
        data.getGuardianType(),data.getGuardianName(),data.getMotherName(),data.getAddress(),data.getCity(),data.getPincode(),data.getState(),data.getCountry(),data.getMobile(),data.getAlternateNumber(),
                data.getWhatsappNumber(),data.getBirthDay(),data.getGender(),data.getBloodGroup(),data.getMaritalStatus(),data.getMarriageDate(),data.getChildCount(),data.getEmailAddress(),data.getIsHeadOfFamily()).enqueue(new Callback<UpdateBasicDetailsResponse>() {
            @Override
            public void onResponse(Call<UpdateBasicDetailsResponse> call, Response<UpdateBasicDetailsResponse> response) {


                Log.e("Response message=", "" + response.message());

               /* LoginResponse response1 = new LoginResponse();
                if (response.body() != null) {
                    response1.setLoginModel(response.body());

                    response1.setStatus(true);

                } else {
                    response1.setStatus(false);
                    if (!TextUtils.isEmpty(response.errorBody().toString())) {
                        try {
                            JSONObject jObjError = new JSONObject(convertStreamToString(response.errorBody().byteStream()));
                            response1.setMessage(jObjError.getString("message"));
                        } catch (Exception e) {
                            response1.setMessage("Invalid Username or Password");
                        }
                    } else {
                        response1.setMessage("Invalid Username or Password");

                    }


                }*/

                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<UpdateBasicDetailsResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void addAddress(String memberId, String token, Address data) {



        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);



        endPointApi.addAddress(memberId,token,"add",data.getAddress1(),data.getAddress2(),data.getPost(),data.getDistrict(),
                data.getCity(),data.getPincode(),data.getState_name(),data.getCountry(),data.getAddress_type()).enqueue(new Callback<AddAddressResponse>() {
            @Override
            public void onResponse(Call<AddAddressResponse> call, Response<AddAddressResponse> response) {


                Log.e("Response message=", "" + response.message());



                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<AddAddressResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void updatePromises(String memberId, String token, String navkar_mantra,
                               String swadhyay, String sant_darshan,
                               String samayik, String navkarsi,
                               String pratikraman, String chovihar,
                               String others, String special) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.updatePromises(memberId, token, "update", navkar_mantra, swadhyay, sant_darshan, samayik,
                navkarsi, pratikraman, chovihar, others, special).enqueue(new Callback<UpdatePromiseResponse>() {
            @Override
            public void onResponse(Call<UpdatePromiseResponse> call, Response<UpdatePromiseResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<UpdatePromiseResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void updateKnowledge(String memberId, String token, String navkar_mantra,
                                String samayik, String pratikraman,
                                String bol_thokde, String shastra_gyan,
                                String vishesh_gyan) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.updateKnowledge(memberId, token, "update", navkar_mantra, samayik, pratikraman, bol_thokde,
                shastra_gyan, vishesh_gyan).enqueue(new Callback<UpdateKnowledgeResponse>() {
            @Override
            public void onResponse(Call<UpdateKnowledgeResponse> call, Response<UpdateKnowledgeResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<UpdateKnowledgeResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }


    public void addAchievemetns(String memberId, String token, Achievements data) {



        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);



        endPointApi.addAchievements(memberId,token,"add",data.getAchievement_sector(),data.getAchievement_level(),data.getAchievement_type(),data.getAchievement_detail(),
                data.getAchievement_year()).enqueue(new Callback<AddAchievementResponse>() {
            @Override
            public void onResponse(Call<AddAchievementResponse> call, Response<AddAchievementResponse> response) {


                Log.e("Response message=", "" + response.message());



                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<AddAchievementResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void addExams(String memberId, String token, Exams data) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.addExams(memberId, token, "add", data.getExam_name(), data.getExam_institute_name(), data.getExam_year()).enqueue(new Callback<AddExamResponse>() {
            @Override
            public void onResponse(Call<AddExamResponse> call, Response<AddExamResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<AddExamResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void addEducation(String memberId, String token, Education data) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.addEducation(memberId, token, "add", data.getEducationName(), data.getDescription(), data.getScore(), data.getIstitute(),
                data.getYear()).enqueue(new Callback<AddEducationResponse>() {
            @Override
            public void onResponse(Call<AddEducationResponse> call, Response<AddEducationResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<AddEducationResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void addBusiness(String memberId, String token, Business data) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.addBusiness(memberId, token, "add", data.getBusiness_type(), data.getBusiness_name(), data.getBusiness_role(), data.getBusiness_start_year()).enqueue(new Callback<AddBusinessResponse>() {
            @Override
            public void onResponse(Call<AddBusinessResponse> call, Response<AddBusinessResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<AddBusinessResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void removeAddress(String memberId, String token, Address data) {



        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);



        endPointApi.removeAddress(memberId,token,"delete",data.getId()).enqueue(new Callback<DeleteAddressResponse>() {
            @Override
            public void onResponse(Call<DeleteAddressResponse> call, Response<DeleteAddressResponse> response) {


                Log.e("Response message=", "" + response.message());



                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<DeleteAddressResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void removeAchievements(String memberId, String token, Achievements data) {



        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);



        endPointApi.deleteAchievement(memberId,token,"delete",data.getId()).enqueue(new Callback<DeleteAchievementResponse>() {
            @Override
            public void onResponse(Call<DeleteAchievementResponse> call, Response<DeleteAchievementResponse> response) {


                Log.e("Response message=", "" + response.message());



                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<DeleteAchievementResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }
    public void removeBusiness(String memberId, String token, Business data) {



        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);



        endPointApi.deleteBusiness(memberId,token,"delete",data.getId()).enqueue(new Callback<DeleteBusinessResponse>() {
            @Override
            public void onResponse(Call<DeleteBusinessResponse> call, Response<DeleteBusinessResponse> response) {


                Log.e("Response message=", "" + response.message());



                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<DeleteBusinessResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });

    }

    public void removeEducation(String memberId, String token, Education data) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.deleteEducation(memberId, token, "delete", data.getId()).enqueue(new Callback<DeleteEducationResponse>() {
            @Override
            public void onResponse(Call<DeleteEducationResponse> call, Response<DeleteEducationResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<DeleteEducationResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });

    }

    public void removeExam(String memberId, String token, Exams data) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.deleteExams(memberId, token, "delete", data.getId()).enqueue(new Callback<DeleteExamResponse>() {
            @Override
            public void onResponse(Call<DeleteExamResponse> call, Response<DeleteExamResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<DeleteExamResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });

    }

    public void removeSocialRole(String memberId, String token, SocialRole data) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.deleteSocialRole(memberId, token, "delete", data.getId()).enqueue(new Callback<DeleteSocialRoleResponse>() {
            @Override
            public void onResponse(Call<DeleteSocialRoleResponse> call, Response<DeleteSocialRoleResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<DeleteSocialRoleResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });

    }

    public void passwordChange(String memberId, String token, String currentPassword,String newPassword) {



        EndPointApi valYouAPI = RetrofitClient.getMemberClient().create(EndPointApi.class);



        valYouAPI.passwordChange(memberId,token,currentPassword,newPassword).enqueue(new Callback<PasswordChangeResponse>() {
            @Override
            public void onResponse(Call<PasswordChangeResponse> call, Response<PasswordChangeResponse> response) {


                Log.e("Response message=", "" + response.message());



                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<PasswordChangeResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });
    }

    public void passwordRecovery(String emaiId, String mobileNo, String fName,String lName) {



        EndPointApi valYouAPI = RetrofitClient.getMemberClient().create(EndPointApi.class);



        valYouAPI.passwordRecover(emaiId,mobileNo,fName,lName).enqueue(new Callback<PasswordRecoverResponse>() {
            @Override
            public void onResponse(Call<PasswordRecoverResponse> call, Response<PasswordRecoverResponse> response) {


                Log.e("Response message=", "" + response.message());



                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<PasswordRecoverResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void getStateList() {



        EndPointApi valYouAPI = RetrofitClient.getMemberClient().create(EndPointApi.class);



        valYouAPI.getStateList().enqueue(new Callback<List<State>>() {
            @Override
            public void onResponse(Call<List<State>> call, Response<List<State>> response) {


                Log.e("Response message=", "" + response.message());


                StateListResponse stateListResponse = new StateListResponse();

                if (response.body() != null) {
                    stateListResponse.setStateList(response.body());
                    guiCallback.onRequestProcessed(stateListResponse, GUICallback.RequestStatus.SUCCESS);
                }else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<List<State>> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void getCityList() {



        EndPointApi valYouAPI = RetrofitClient.getMemberClient().create(EndPointApi.class);



        valYouAPI.getCityList().enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {


                Log.e("Response message=", "" + response.message());


                CityListResponse cityListResponse = new CityListResponse();

                if (response.body() != null) {
                    cityListResponse.setCityList(response.body());
                    guiCallback.onRequestProcessed(cityListResponse, GUICallback.RequestStatus.SUCCESS);
                }else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }



    public void updateServices(String memberId, String token, String service_time,
                               String service_money, String service_office,
                               String service_experience, String service_thoughts,
                               String service_others) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.updateServices(memberId, token, "update", service_time, service_money, service_office, service_experience,
                service_thoughts, service_others).enqueue(new Callback<UpdateServiceResponse>() {
            @Override
            public void onResponse(Call<UpdateServiceResponse> call, Response<UpdateServiceResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<UpdateServiceResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void addSocialRole(String memberId, String token, String start_date,
                              String end_date, String social_org_name,
                              String social_org_active, String social_org_role,
                              String social_org_role_level) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.addSocilaRole(memberId, token, "add", start_date, end_date, social_org_name, social_org_active,
                social_org_role, social_org_role_level).enqueue(new Callback<AddScocialRoleResponse>() {
            @Override
            public void onResponse(Call<AddScocialRoleResponse> call, Response<AddScocialRoleResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<AddScocialRoleResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void updateHobbies(String memberId, String token, String org_planning,
                              String org_comp_operator, String org_project_operation,
                              String org_fund_raise, String org_migration_prg, String org_oration_trainer,
                              String org_management_phones, String rel_medical_service,String rel_vihar_service, String rel_gochary_service,
                              String rel_jap_tap_cordination,String rel_swadhyai_service,String rel_kar_sewa,String rel_shivir_management, String rel_writeup,String rel_drawing,
                              String rel_self_learning,String rel_teaching,String rel_branch, String social_human_service,String social_education_service
    ,String social_medical_service,String social_veg_publicity,String social_lit_service,String social_water_kiosk_service,String social_web_handling
    ,String social_speech,String social_drug_rehab) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.updateHobbies(memberId, token, "add", org_planning, org_comp_operator,
                org_project_operation, org_fund_raise, org_migration_prg,org_oration_trainer,org_management_phones,
                rel_medical_service,rel_vihar_service,rel_gochary_service,rel_jap_tap_cordination,rel_swadhyai_service,rel_kar_sewa,
                rel_shivir_management,rel_writeup,rel_drawing,rel_self_learning,rel_teaching,rel_branch,social_human_service,
                social_education_service,social_medical_service,social_veg_publicity,social_lit_service,social_water_kiosk_service,social_web_handling
        ,social_speech,social_drug_rehab).enqueue(new Callback<UpdateHobbiesResponse>() {
            @Override
            public void onResponse(Call<UpdateHobbiesResponse> call, Response<UpdateHobbiesResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<UpdateHobbiesResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }


    public void selectFamily(String lacalSanghID, String firstName, String middleName,
                              String lastName, String city) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.selectFamily(lacalSanghID, firstName, middleName, lastName, city).enqueue(new Callback<FamiliesResponse>() {
            @Override
            public void onResponse(Call<FamiliesResponse> call, Response<FamiliesResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<FamiliesResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void selectNativeFamily(String lacalSanghID,String city) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.selectNativeFamily(lacalSanghID, city).enqueue(new Callback<NativeFamilyResponse>() {
            @Override
            public void onResponse(Call<NativeFamilyResponse> call, Response<NativeFamilyResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<NativeFamilyResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }

    public void familyMembers(String memberId, String token) {


        EndPointApi endPointApi = RetrofitClient.getMemberClient().create(EndPointApi.class);


        endPointApi.familyMembers(memberId, token).enqueue(new Callback<FamilyMembersResponse>() {
            @Override
            public void onResponse(Call<FamilyMembersResponse> call, Response<FamilyMembersResponse> response) {


                Log.e("Response message=", "" + response.message());


                if (response.body() != null)
                    guiCallback.onRequestProcessed(response.body(), GUICallback.RequestStatus.SUCCESS);
                else
                    guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);

            }

            @Override
            public void onFailure(Call<FamilyMembersResponse> call, Throwable t) {


                guiCallback.onRequestProcessed(null, GUICallback.RequestStatus.FAILED);
            }
        });


    }



    public String convertStreamToString(InputStream inputStream) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
        }

        String finallyError = sb.toString();
        Log.e("finallyError=", "" + finallyError);
        return finallyError;
    }
}
